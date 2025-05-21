package co.edu.uniquindio.poo.sistemanotificaciones.Model;

import org.junit.jupiter.api.Test;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import static org.junit.jupiter.api.Assertions.*;

public class StrategyTest {

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    @Test
    public void testEmailNotification() {
        System.setOut(new PrintStream(outContent));

        EmailNotification strategy = new EmailNotification();
        User user = new ClientUser("test@test.com", false);
        strategy.send(user, "Mensaje de prueba");

        String expected = "Email enviado a test@test.com\nContenido: \nMensaje de prueba";
        assertTrue(outContent.toString().contains(expected));

        System.setOut(originalOut);
    }

    @Test
    public void testPushNotification() {
        System.setOut(new PrintStream(outContent));

        PushNotification strategy = new PushNotification();
        User user = new AdminUser("admin@test.com", false);
        strategy.send(user, "Alerta importante");

        String expected = "▲ Push enviado a admin@test.com\nContenido:\nAlerta importante";
        assertTrue(outContent.toString().contains(expected));

        System.setOut(originalOut);
    }

    @Test
    public void testSMSNotification() {
        System.setOut(new PrintStream(outContent));

        SMSNotification strategy = new SMSNotification();
        User user = new ClientUser("client@test.com", false);
        strategy.send(user, "Recordatorio");

        String expected = "SMS enviado a client@test.com\nContenido:\nRecordatorio";
        assertTrue(outContent.toString().contains(expected));

        System.setOut(originalOut);
    }
}
