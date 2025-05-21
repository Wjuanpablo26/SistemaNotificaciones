package co.edu.uniquindio.poo.sistemanotificaciones.Model;


public class NotificationFilter {

    protected NotificationFilter next;

    public void setNext(NotificationFilter next) {
        this.next = next;
    }

    public boolean handle(Notificacion notification) {
        if (next != null) return next.handle(notification);
        return true;
    }

}





