package co.edu.uniquindio.poo.sistemanotificaciones.Model;

//Strategy
public interface NotificacionStrategy {
    public void send(User user, String message);
}
