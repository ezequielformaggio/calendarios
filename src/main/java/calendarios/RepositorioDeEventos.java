package calendarios;

import java.util.ArrayList;
import java.util.List;

public class RepositorioDeEventos {

  private static final RepositorioDeEventos repositorioDeEventos = new RepositorioDeEventos();

  public static RepositorioDeEventos getInstance() {
    return repositorioDeEventos;
  }

  public final List<Evento> eventos = new ArrayList<>();

  public void repetirEventos() {
    eventos.forEach(Evento::repetirEvento);
  }
}
