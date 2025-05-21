package co.edu.uniquindio.poo.sistemanotificaciones.Model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class UserTest {

    @Test
    public void testAdminUserFormatMessage() {
        AdminUser admin = new AdminUser("admin@test.com", false);
        String result = admin.formatMessage("Mensaje importante");
        String expected = "[ADMINISTRADOR]\nAdministrador: Mensaje importante\n- Sistema -";
        assertEquals(expected, result);
    }

    @Test
    public void testClientUserFormatMessage() {
        ClientUser client = new ClientUser("client@test.com", false);
        String result = client.formatMessage("Bienvenido");
        String expected = "Hola Cliente.\nBienvenido\nGracias por usar la aplicación.";
        assertEquals(expected, result);
    }

    @Test
    public void testUserBlockedStatus() {
        User blockedUser = new ClientUser("blocked@test.com", true);
        assertTrue(blockedUser.isBlocked());

        User activeUser = new AdminUser("active@test.com", false);
        assertFalse(activeUser.isBlocked());
    }
}