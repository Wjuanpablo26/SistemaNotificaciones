package co.edu.uniquindio.poo.sistemanotificaciones.Model;

public class Notificacion {

    private User user;
    private String message;
    private NotificacionStrategy strategy;

    public Notificacion(User user, String message) {
        this.user = user;
        this.message = message;
    }

    public User getUser() {
        return user;
    }

    public String getMessage() {
        return message;
    }

    public void setStrategy(NotificacionStrategy strategy) {
        this.strategy = strategy;
    }

    public void send() {
        String formatted = user.formatMessage(message);
        strategy.send(user, formatted);
    }

}
