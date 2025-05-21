package co.edu.uniquindio.poo.sistemanotificaciones.Model;

public class SMSNotification implements NotificacionStrategy{

    @Override
    public void send(User user, String message) {
        System.out.println("SMS enviado a " + user.getEmail());
        System.out.println("Contenido:\n" + message);
    }

}
