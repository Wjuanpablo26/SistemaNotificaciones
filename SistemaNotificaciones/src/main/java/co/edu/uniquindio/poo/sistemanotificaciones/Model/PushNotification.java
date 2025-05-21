package co.edu.uniquindio.poo.sistemanotificaciones.Model;

//Strategy
public class PushNotification implements NotificacionStrategy {

    @Override
    public void send(User user, String message) {
        System.out.println("🔔 Push enviado a " + user.getEmail());
        System.out.println("Contenido:\n" + message);
    }

}
