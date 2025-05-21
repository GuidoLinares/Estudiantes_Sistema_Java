package gm.Estudiantes.ui;

import gm.Estudiantes.modelo.Estudiante; // Esto ya no es estrictamente necesario a menos que uses Estudiante en showDashboardPanel, etc.
import gm.Estudiantes.servicio.EstudianteServicio;
import javafx.collections.FXCollections; // Ya no es necesario
import javafx.collections.ObservableList; // Ya no es necesario
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*; // Necesario para Alert
import javafx.scene.control.cell.PropertyValueFactory; // Ya no es necesario
import javafx.scene.image.Image; // Ya no es necesario para user_avatar
import javafx.scene.image.ImageView; // Necesario para userAvatar (si lo tienes en dashboard.fxml)
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox; // Si usas HBox en dashboard.fxml para la barra superior
import javafx.scene.layout.VBox; // Necesario para sidebar
import javafx.stage.Stage;
import javafx.util.Callback; // Ya no es necesario

import javafx.beans.property.SimpleObjectProperty; // Ya no es necesario
import javafx.beans.value.ObservableValue; // Ya no es necesario

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List; // Ya no es necesario
import java.util.Optional; // Ya no es necesario

@Component
public class EstudiantesDashboardUIController {

    private final EstudianteServicio estudianteServicio; // Puedes mantenerlo si showDashboardPanel lo usará. Si no, quítalo.
    private final ConfigurableApplicationContext applicationContext;

    // COMPONENTES FXML DEL DASHBOARD PRINCIPAL SOLAMENTE
    @FXML private VBox sidebar;
    @FXML private AnchorPane contentArea;
    // Si tienes el ImageView userAvatar en dashboard.fxml
    // @FXML private ImageView userAvatar;

    // Elimina TODAS las declaraciones @FXML de TextField, TableView, TableColumn
    // Elimina la línea: private ObservableList<Estudiante> estudianteData;

    @Autowired
    public EstudiantesDashboardUIController(EstudianteServicio estudianteServicio, ConfigurableApplicationContext applicationContext) {
        this.estudianteServicio = estudianteServicio; // Si quitas EstudianteServicio, remueve esta línea
        this.applicationContext = applicationContext;
    }

    @FXML
    public void initialize() {
        // La inicialización de la tabla se ha movido al StudentsManagementPanelController
        // Solo carga el panel inicial aquí.
        try {
            showEstudiantesPanel();
        } catch (IOException e) {
            showAlert(Alert.AlertType.ERROR, "Error de Carga", "No se pudo cargar el panel de estudiantes: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void show(Stage primaryStage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/dashboard.fxml"));
        fxmlLoader.setControllerFactory(applicationContext::getBean);
        Parent root = fxmlLoader.load();

        Scene scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("/style.css").toExternalForm());

        primaryStage.setScene(scene);
        primaryStage.setTitle("Sistema de Gestión de Estudiantes");
        primaryStage.setMinWidth(1000); // Ancho mínimo
        primaryStage.setMinHeight(900); // Alto mínimo
        primaryStage.setWidth(1200);   // Ancho inicial preferido
        primaryStage.setHeight(900);   // Alto inicial preferido
        primaryStage.show();
    }

    // --- MÉTODOS DE ACCIÓN PARA LA NAVEGACIÓN (Botones de la barra lateral) ---
    @FXML
    private void showDashboardPanel() throws IOException {
        loadPanel("/dashboard_summary.fxml"); // Asumiendo que existe
    }

    @FXML
    private void showEstudiantesPanel() throws IOException {
        loadPanel("/students_management.fxml"); // Simplemente carga el FXML
    }

    @FXML
    private void showReportsPanel() throws IOException {
        showAlert(Alert.AlertType.INFORMATION, "Navegación", "Panel de Reportes aún no implementado.");
        // loadPanel("/reports_panel.fxml"); // Si implementas uno
    }

    // Método genérico para cargar paneles
    private void loadPanel(String fxmlPath) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlPath));
        loader.setControllerFactory(applicationContext::getBean); // Es crucial para que Spring inyecte el controlador del panel
        Parent panel = loader.load();

        contentArea.getChildren().setAll(panel);
        AnchorPane.setTopAnchor(panel, 0.0);
        AnchorPane.setBottomAnchor(panel, 0.0);
        AnchorPane.setLeftAnchor(panel, 0.0);
        AnchorPane.setRightAnchor(panel, 0.0);
    }

    // Método showAlert (Puedes mantenerlo aquí o en una clase de utilidad si lo usas en varios controladores)
    private void showAlert(Alert.AlertType type, String title, String message) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    // Elimina TODOS los métodos CRUD: cargarEstudiantes, guardarEstudiante, buscarEstudiante,
    // eliminarEstudiante, limpiarCampos, mostrarDetallesEstudianteEnFormulario
}