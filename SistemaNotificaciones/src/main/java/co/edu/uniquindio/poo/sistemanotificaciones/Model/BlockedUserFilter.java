package co.edu.uniquindio.poo.sistemanotificaciones.Model;


public class BlockedUserFilter extends NotificationFilter {

    public boolean handle(Notificacion notification) {
        if (notification.getUser().isBlocked()) {
            System.out.println("Usuario bloqueado. Notificación cancelada.");
            return false;
        }
        return super.handle(notification);
    }

}
