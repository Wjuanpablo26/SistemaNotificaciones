package co.edu.uniquindio.poo.sistemanotificaciones.Controller;

import co.edu.uniquindio.poo.sistemanotificaciones.Model.*;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class CreateNotificationController {

    @FXML private TextField emailField;
    @FXML private RadioButton clientRadio;
    @FXML private RadioButton adminRadio;
    @FXML private CheckBox blockedCheckbox;
    @FXML private TextArea messageArea;
    @FXML private ComboBox<String> methodComboBox;

    @FXML
    private void initialize() {
        // Configurar el ComboBox
        methodComboBox.getItems().addAll("Email", "SMS", "Push Notification");
        methodComboBox.getSelectionModel().selectFirst();

        // Configurar el grupo de RadioButtons
        ToggleGroup userTypeGroup = new ToggleGroup();
        clientRadio.setToggleGroup(userTypeGroup);
        adminRadio.setToggleGroup(userTypeGroup);
        clientRadio.setSelected(true);
    }

    @FXML
    private void handleSendNotification() {
        try {
            String email = emailField.getText();
            boolean isBlocked = blockedCheckbox.isSelected();
            String message = messageArea.getText();
            String method = methodComboBox.getValue();

            // Determinar el tipo de usuario seleccionado
            String userType = clientRadio.isSelected() ? "CLIENT" : "ADMIN";

            // Crear usuario según el tipo seleccionado
            User user = userType.equals("ADMIN") ?
                    new AdminUser(email, isBlocked) :
                    new ClientUser(email, isBlocked);

            // Crear estrategia de notificación
            NotificacionStrategy strategy = switch (method) {
                case "Email" -> new EmailNotification();
                case "SMS" -> new SMSNotification();
                case "Push Notification" -> new PushNotification();
                default -> throw new IllegalArgumentException("Método no válido");
            };

            // Crear y enviar notificación
            Notificacion notification = new Notificacion(user, message);
            notification.setStrategy(strategy);

            // Configurar cadena de responsabilidad para los filtros
            NotificationFilter filterChain = new EmptyMessageFilter();
            filterChain.setNext(new BlockedUserFilter());

            if (filterChain.handle(notification)) {
                notification.send();
                showAlert("Éxito", "Notificación enviada correctamente", Alert.AlertType.INFORMATION);
            }

        } catch (Exception e) {
            showAlert("Error", "Error al enviar notificación: " + e.getMessage(), Alert.AlertType.ERROR);
        }
    }

    @FXML
    private void handleCancel() {
        emailField.getScene().getWindow().hide();
    }

    private void showAlert(String title, String message, Alert.AlertType type) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
