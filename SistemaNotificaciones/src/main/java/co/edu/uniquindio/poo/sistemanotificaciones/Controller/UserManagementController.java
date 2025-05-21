package co.edu.uniquindio.poo.sistemanotificaciones.Controller;

import co.edu.uniquindio.poo.sistemanotificaciones.Model.AdminUser;
import co.edu.uniquindio.poo.sistemanotificaciones.Model.ClientUser;
import co.edu.uniquindio.poo.sistemanotificaciones.Model.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;


public class UserManagementController {

    @FXML private TableView<User> usersTable;

    private ObservableList<User> users = FXCollections.observableArrayList();

    @FXML
    private void initialize() {
        // Configurar columnas de la tabla (si no están en el FXML)
        setupTableColumns();

        // Datos de ejemplo
        loadSampleData();
    }

    private void setupTableColumns() {
        // Solo si no has definido las columnas en el FXML
        TableColumn<User, String> emailColumn = new TableColumn<>("Email");
        emailColumn.setCellValueFactory(cellData -> cellData.getValue().emailProperty());

        TableColumn<User, String> typeColumn = new TableColumn<>("Tipo");
        typeColumn.setCellValueFactory(cellData -> cellData.getValue().userTypeProperty());

        TableColumn<User, Boolean> statusColumn = new TableColumn<>("Estado");
        statusColumn.setCellValueFactory(cellData -> cellData.getValue().blockedProperty());

        usersTable.getColumns().setAll(emailColumn, typeColumn, statusColumn);
    }

    private void loadSampleData() {
        users.add(new AdminUser("admin@example.com", false));
        users.add(new ClientUser("client1@example.com", false));
        users.add(new ClientUser("client2@example.com", true));

        usersTable.setItems(users);
    }

    @FXML
    private void handleToggleBlock() {
        User selected = usersTable.getSelectionModel().getSelectedItem();
        if (selected != null) {
            selected.setBlocked(!selected.isBlocked());
            usersTable.refresh();
            showInfo("Estado cambiado para " + selected.getEmail() +
                    "\nNuevo estado: " + (selected.isBlocked() ? "BLOQUEADO" : "DESBLOQUEADO"));
        } else {
            showError("Por favor seleccione un usuario");
        }
    }

    @FXML
    private void handleCreateUser() {
        // Implementación básica - puedes expandir esto
        showCreateUserDialog();
    }

    private void showCreateUserDialog() {
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Crear Nuevo Usuario");
        dialog.setHeaderText("Ingrese los datos del usuario");
        dialog.setContentText("Email:");

        dialog.showAndWait().ifPresent(email -> {
            if (!email.isEmpty()) {
                // Por defecto crea un cliente no bloqueado
                users.add(new ClientUser(email, false));
                usersTable.refresh();
                showInfo("Usuario creado: " + email);
            }
        });
    }

    @FXML
    private void handleClose() {
        usersTable.getScene().getWindow().hide();
    }

    private void showError(String message) {
        showAlert("Error", message, Alert.AlertType.ERROR);
    }

    private void showInfo(String message) {
        showAlert("Información", message, Alert.AlertType.INFORMATION);
    }

    private void showAlert(String title, String message, Alert.AlertType type) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
