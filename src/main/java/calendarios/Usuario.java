package calendarios;

import calendarios.servicios.GugleMapas;
import calendarios.servicios.PositionService;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

public class Usuario {

  String mail;
  List<Calendario> calendarios = new ArrayList<>();


  public Usuario(String mail) {
    this.mail = mail;
  }

  public void agregarCalendario(Calendario calendario) {
    calendarios.add(calendario);
  }

  public List<Evento> eventosEntreFechas(LocalDateTime inicio, LocalDateTime fin) {
    return calendarios.stream().map(calendario -> calendario.eventosEntreFechas(inicio,fin))
            .flatMap(Collection::stream).collect(Collectors.toList());
  }

  public boolean llegaATiempoAlProximoEvento(GugleMapas mapa, PositionService posicion) {
    Evento evento = getProximoEvento();
    if(evento == null) {
      return true;
    }
    Ubicacion ubicacionAcual = posicion.ubicacionActual(mail);
    Duration tiempoEstimado = mapa.tiempoEstimadoHasta(ubicacionAcual, evento.getUbicacion());
    return LocalDateTime.now().plus(tiempoEstimado).compareTo(evento.getInicio()) <= 0;
  }

  public boolean tieneCalendario(Calendario calendario) {
    return calendarios.contains(calendario);
  }

  public List<Evento> getEventosTotales() {
    return calendarios.stream().map(Calendario::getEventos)
            .flatMap(Collection::stream).collect(Collectors.toList());
  }

  public String getMail() {
    return mail;
  }

  public Evento getProximoEvento() {
    if (this.getEventosTotales().size() == 0) {
      return null;
    }
    return this
            .getEventosTotales()
            .stream()
            .sorted(Comparator.comparing(Evento::cuantoFalta))
            .collect(Collectors.toList())
            .get(0);
  }

}
