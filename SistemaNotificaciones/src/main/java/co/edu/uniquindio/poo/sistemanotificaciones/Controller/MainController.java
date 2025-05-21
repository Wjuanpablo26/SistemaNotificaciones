package co.edu.uniquindio.poo.sistemanotificaciones.Controller;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.StackPane;

public class MainController {

    @FXML
    private StackPane contentPane;

    @FXML
    private void handleExit() {
        System.exit(0);
    }

    @FXML
    private void showCreateNotification() {
        loadView("/co/edu/uniquindio/poo/sistemanotificaciones/Create_Notification.fxml");
    }

    @FXML
    private void showUserManagement() {
        loadView("/co/edu/uniquindio/poo/sistemanotificaciones/user_management.fxml");
    }

    public void loadView(String fxmlPath) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlPath));
            Parent root = loader.load();

            contentPane.getChildren().clear(); // Limpia vistas anteriores
            contentPane.getChildren().add(root); // Muestra la nueva vista

        } catch (IOException e) {
            e.printStackTrace();
            showError("No se pudo cargar la vista: " + fxmlPath);
        }
    }

    private void showError(String message) {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
