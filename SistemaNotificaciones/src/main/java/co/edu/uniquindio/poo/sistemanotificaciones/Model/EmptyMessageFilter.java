package co.edu.uniquindio.poo.sistemanotificaciones.Model;

import javax.management.Notification;

public class EmptyMessageFilter extends NotificationFilter{

    public boolean handle(Notificacion notification) {
        if (notification.getMessage() == null || notification.getMessage().isEmpty()) {
            System.out.println("Mensaje vacío. Notificación cancelada.");
            return false;
        }
        return super.handle(notification);
    }

}
