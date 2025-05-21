module co.edu.uniquindio.poo.sistemanotificaciones {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.management;


    opens co.edu.uniquindio.poo.sistemanotificaciones to javafx.fxml;
    exports co.edu.uniquindio.poo.sistemanotificaciones;
    exports co.edu.uniquindio.poo.sistemanotificaciones.Controller;
    opens co.edu.uniquindio.poo.sistemanotificaciones.Controller to javafx.fxml;
}