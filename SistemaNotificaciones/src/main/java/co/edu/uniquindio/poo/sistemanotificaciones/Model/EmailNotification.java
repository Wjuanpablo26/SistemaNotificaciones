package co.edu.uniquindio.poo.sistemanotificaciones.Model;

//Clase que implementa la estrategia de notificación email
//Strategy
public class EmailNotification implements NotificacionStrategy {

    @Override
    public void send(User user, String message) {
        System.out.println("Email enviado a " + user.getEmail());
        System.out.println("Contenido:\n" + message);
    }

}
