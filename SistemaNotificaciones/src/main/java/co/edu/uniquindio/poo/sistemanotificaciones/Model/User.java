package co.edu.uniquindio.poo.sistemanotificaciones.Model;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public abstract class User {

    protected String email;
    protected boolean blocked;

    public User(String email, boolean blocked) {
        this.email = email;
        this.blocked = blocked;
    }

    public String getEmail() { return email; }
    public boolean isBlocked() { return blocked; }

    // Añade este nuevo método
    public void setBlocked(boolean blocked) {
        this.blocked = blocked;
    }

    // Método plantilla
    public final String formatMessage(String rawMessage) {
        return getHeader() + "\n" + personalizeMessage(rawMessage) + "\n" + getFooter();
    }

    protected abstract String getHeader();
    protected abstract String personalizeMessage(String message);
    protected abstract String getFooter();

    public StringProperty emailProperty() {
        return new SimpleStringProperty(email);
    }

    public BooleanProperty blockedProperty() {
        return new SimpleBooleanProperty(blocked);
    }

    public StringProperty userTypeProperty() {
        return new SimpleStringProperty(this instanceof AdminUser ? "Administrador" : "Cliente");
    }


}