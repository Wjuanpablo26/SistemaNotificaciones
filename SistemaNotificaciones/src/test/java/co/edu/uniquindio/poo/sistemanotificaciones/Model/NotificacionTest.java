package co.edu.uniquindio.poo.sistemanotificaciones.Model;

import org.junit.jupiter.api.Test;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import static org.junit.jupiter.api.Assertions.*;

public class NotificacionTest {

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    @Test
    public void testNotificacionSend() {
        System.setOut(new PrintStream(outContent));

        User user = new AdminUser("admin@test.com", false);
        Notificacion notificacion = new Notificacion(user, "Mensaje admin");
        notificacion.setStrategy(new PushNotification());

        SendNotificationCommand command = new SendNotificationCommand(notificacion);
        command.execute();

        String expected = "▲ Push enviado a admin@test.com\nContenido:\n[ADMINISTRADOR]\nAdministrador: Mensaje admin\n- Sistema -";
        assertTrue(outContent.toString().contains(expected));

        System.setOut(originalOut);
    }

    @Test
    public void testNotificacionInvoker() {
        System.setOut(new PrintStream(outContent));

        NotificationInvoker invoker = new NotificationInvoker();
        User user = new ClientUser("client@test.com", false);

        Notificacion emailNotif = new Notificacion(user, "Email");
        emailNotif.setStrategy(new EmailNotification());

        Notificacion smsNotif = new Notificacion(user, "SMS");
        smsNotif.setStrategy(new SMSNotification());

        invoker.addCommand(new SendNotificationCommand(emailNotif));
        invoker.addCommand(new SendNotificationCommand(smsNotif));
        invoker.executeCommands();

        String output = outContent.toString();
        assertTrue(output.contains("Email enviado a client@test.com"));
        assertTrue(output.contains("SMS enviado a client@test.com"));

        System.setOut(originalOut);
    }
}