package co.edu.uniquindio.poo.sistemanotificaciones;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import java.io.PrintWriter;
import java.io.StringWriter;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;

public class HelloApplication extends Application {
    @Override
    public void start(Stage primaryStage) {
        try {
            // 1. Cargar el archivo FXML principal
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/co/edu/uniquindio/poo/sistemanotificaciones/Main.fxml"));
            Parent root = loader.load();

            // 2. Configurar la escena principal
            Scene scene = new Scene(root, 1000, 700);

            // 3. Configurar la ventana principal
            primaryStage.setTitle("Sistema de Notificaciones - UNIQUINDIO");
            primaryStage.setScene(scene);
            primaryStage.setMinWidth(800);
            primaryStage.setMinHeight(600);

            // 4. Mostrar la ventana
            primaryStage.show();

        } catch (Exception e) {
            // Manejo de errores durante la inicialización
            showErrorAlert("Error al iniciar la aplicación", e);
            System.exit(1);
        }
    }

    public static void main(String[] args) {
        try {
            // Iniciar la aplicación JavaFX
            launch(args);
        } catch (Exception e) {
            // Manejo de errores generales
            System.err.println("Error crítico en la aplicación: " + e.getMessage());
            e.printStackTrace();
            System.exit(1);
        }
    }

    /**
     * Muestra una alerta de error con detalles de la excepción
     * @param header Mensaje principal del error
     * @param e Excepción ocurrida
     */
    private void showErrorAlert(String header, Exception e) {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("Error Inesperado");
        alert.setHeaderText(header);
        alert.setContentText(e.getMessage());

        // Crear área de texto expandible para los detalles
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        e.printStackTrace(pw);
        String exceptionText = sw.toString();

        TextArea textArea = new TextArea(exceptionText);
        textArea.setEditable(false);
        textArea.setWrapText(true);
        textArea.setMaxWidth(Double.MAX_VALUE);
        textArea.setMaxHeight(Double.MAX_VALUE);
        GridPane.setVgrow(textArea, Priority.ALWAYS);
        GridPane.setHgrow(textArea, Priority.ALWAYS);

        GridPane expContent = new GridPane();
        expContent.setMaxWidth(Double.MAX_VALUE);
        expContent.add(new Label("Stack Trace:"), 0, 0);
        expContent.add(textArea, 0, 1);

        // Establecer contenido expandible
        alert.getDialogPane().setExpandableContent(expContent);
        alert.getDialogPane().setExpanded(true); // Mostrar detalles por defecto

        alert.showAndWait();
    }
}