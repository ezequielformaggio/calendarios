package calendarios;

public class CalnedariosApp {

  public static void main(String[] args) {
    // este metodo se ejecuta corriendo el programa main con un crontab cada cierto tiempo
    RepositorioDeEventos.getInstance().repetirEventos();
  }
}
