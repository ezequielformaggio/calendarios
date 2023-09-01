package calendarios;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

public class Evento {

  private final String nombre;
  private LocalDateTime inicio;
  private LocalDateTime fin;
  private final Ubicacion ubicacion;
  private final List<Usuario> invitados;

  public Evento(String nombre, LocalDateTime inicio,
                LocalDateTime fin, Ubicacion ubicacion, List<Usuario> invitados) {
    this.nombre = nombre;
    this.inicio = inicio;
    this.fin = fin;
    this.ubicacion = ubicacion;
    this.invitados = invitados;
  }

  public LocalDateTime getInicio() {
    return inicio;
  }

  public LocalDateTime getFin() {
    return fin;
  }

  public void reporgramar(Duration intervalo) {
    this.inicio = inicio.plus(intervalo);
    this.fin = fin.plus(intervalo);
  }

  public Ubicacion getUbicacion() {
    return ubicacion;
  }

  public List<Usuario> getInvitados() {
    return invitados;
  }

  public void agregarInvitado(Usuario usuario) {
    this.invitados.add(usuario);
  }

  public Duration cuantoFalta() {
    Duration tiempoFaltante = Duration.ofMinutes(LocalDateTime.now()
            .until(this.inicio, ChronoUnit.MINUTES));
    if (tiempoFaltante.isNegative()) {
      return Duration.ZERO;
    } else {
      return tiempoFaltante;
    }
  }

  public boolean estaSolapadoCon(Evento otroEvento) {
    return (this.getInicio().compareTo(otroEvento.getInicio()) >= 0
            && this.getInicio().compareTo(otroEvento.getFin()) <= 0)
            || (otroEvento.getInicio().compareTo(this.getInicio()) >= 0
            && otroEvento.getInicio().compareTo(this.getFin()) <= 0);
  }

  public void repetirEvento() {
  }

  public List<Evento> estaEntreFechas(LocalDateTime initio, LocalDateTime finEvento) {
    List<Evento> ocurrencias = new ArrayList<>();
    if(inicio.isAfter(initio) && fin.isBefore(finEvento)) {
      ocurrencias.add(this);
     }
    return ocurrencias;
  }

}
