package calendarios;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class Calendario {

  List<Evento> eventos = new ArrayList<>();

  public void agendar(Evento evento) {
    this.eventos.add(evento);
  }

  public boolean estaAgendado(Evento evento) {
    return this.eventos.contains(evento);
  }

  public List<Evento> eventosEntreFechas(LocalDateTime initio, LocalDateTime fin) {
    return eventos.stream().map(e -> e.estaEntreFechas(initio, fin))
            .flatMap(Collection::stream).collect(Collectors.toList());
  }

  public List<Evento> eventosSolapadosCon(Evento unEvento) {
    return this.eventos.stream()
            .filter(evento -> evento.estaSolapadoCon(evento))
            .collect(Collectors.toList());
  }

  public List<Evento> getEventos() {
    return eventos;
  }
}
