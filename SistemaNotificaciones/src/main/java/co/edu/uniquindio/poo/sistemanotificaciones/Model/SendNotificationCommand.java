package co.edu.uniquindio.poo.sistemanotificaciones.Model;

public class SendNotificationCommand implements NotificationCommand {

    private Notificacion notification;

    public SendNotificationCommand(Notificacion notification) {
        this.notification = notification;
    }

    public void execute() {
        notification.send();
    }

}
