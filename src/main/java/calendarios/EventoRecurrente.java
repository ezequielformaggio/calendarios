package calendarios;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

public class EventoRecurrente extends Evento {

  private  final LocalDateTime inicioOriginal;
  private  final LocalDateTime finOriginal;
  private final Duration intervalo;
  private final LocalDateTime fechaFinRepeticion;
  private final boolean infinito;

  public EventoRecurrente(String nombre, LocalDateTime inicio,
                          LocalDateTime fin, Ubicacion ubicacion, List<Usuario> invitados,
                          Duration intervalo, LocalDateTime fechaFinRepeticion) {
    super(nombre, inicio, fin, ubicacion, invitados);
    this.inicioOriginal = inicio;
    this.finOriginal = fin;
    this.intervalo = intervalo;
    this.fechaFinRepeticion = fechaFinRepeticion;
    this.infinito = false;
  }

  public EventoRecurrente(String nombre, LocalDateTime inicio,
                          LocalDateTime fin, Ubicacion ubicacion, List<Usuario> invitados,
                          Duration intervalo) {
    super(nombre, inicio, fin, ubicacion, invitados);
    this.inicioOriginal = inicio;
    this.finOriginal = fin;
    this.intervalo = intervalo;
    this.fechaFinRepeticion = null;
    this.infinito = true;
  }

  @Override
  public void repetirEvento() {
    if(tieneProximaOcurrencia() && LocalDateTime.now().compareTo(fechaFinRepeticion) <= 0
            && LocalDateTime.now().compareTo(finOriginal) >= 0) {
      this.reporgramar(intervalo);
    }
  }

  @Override
  public Duration cuantoFalta() {
    Duration tiempoFaltante = Duration.ofMinutes(LocalDateTime.now()
            .until(this.getInicio(), ChronoUnit.MINUTES));
    if (tiempoFaltante.isNegative()) {
      return tiempoFaltante.plus(intervalo);
    } else {
      return tiempoFaltante;
    }
  }

  public boolean tieneProximaOcurrencia() {
    if(infinito) {
      return true;
    } else {
      return this.getInicio().plus(this.intervalo).isBefore(this.fechaFinRepeticion);
    }
  }

  @Override
  public List<Evento> estaEntreFechas(LocalDateTime initio, LocalDateTime finEvento) {

    List<Evento> eventoRepetidoNVeces = new ArrayList<>();
    LocalDateTime inicioAux = this.inicioOriginal;
    LocalDateTime finAux = this.finOriginal;

    while(inicioAux.isBefore(finEvento)) {
      if(!infinito && inicioAux.isAfter(this.fechaFinRepeticion)) {
        break;
      } else if (initio.isBefore(finAux)) {
        eventoRepetidoNVeces.add(this);
      }
      inicioAux = inicioAux.plus(intervalo);
      finAux = finAux.plus(intervalo);
    }

    return eventoRepetidoNVeces;
  }
}
