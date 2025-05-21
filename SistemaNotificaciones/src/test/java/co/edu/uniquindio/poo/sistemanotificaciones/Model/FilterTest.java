package co.edu.uniquindio.poo.sistemanotificaciones.Model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


public class FilterTest {

    @Test
    public void testBlockedUserFilter() {
        BlockedUserFilter filter = new BlockedUserFilter();
        User blockedUser = new ClientUser("blocked@test.com", true);
        User activeUser = new ClientUser("active@test.com", false);

        Notificacion blockedNotification = new Notificacion(blockedUser, "Test");
        Notificacion activeNotification = new Notificacion(activeUser, "Test");

        assertFalse(filter.handle(blockedNotification));
        assertTrue(filter.handle(activeNotification));

    }


    @Test
    public void testEmptyMessageFilter() {
        EmptyMessageFilter filter = new EmptyMessageFilter();
        User user = new ClientUser("test@test.com", false);

        Notificacion emptyNotification = new Notificacion(user, "");
        Notificacion nullNotification = new Notificacion(user, null);
        Notificacion validNotification = new Notificacion(user, "Mensaje válido");

        assertFalse(filter.handle(emptyNotification));
        assertFalse(filter.handle(nullNotification));
        assertTrue(filter.handle(validNotification));
    }

    @Test
    public void testFilterChain() {
        NotificationFilter chain = new BlockedUserFilter();
        chain.setNext(new EmptyMessageFilter());

        User blockedUser = new ClientUser("blocked@test.com", true);
        User activeUser = new ClientUser("active@test.com", false);

        Notificacion blockedValid = new Notificacion(blockedUser, "Mensaje");
        Notificacion activeEmpty = new Notificacion(activeUser, "");
        Notificacion activeValid = new Notificacion(activeUser, "Mensaje");

        assertFalse(chain.handle(blockedValid)); // Bloqueado
        assertFalse(chain.handle(activeEmpty));  // Mensaje vacío
        assertTrue(chain.handle(activeValid));   // Todo correcto
    }
}