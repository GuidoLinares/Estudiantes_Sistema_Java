package gm.Estudiantes.ui.panels; // ¡Asegúrate de que el paquete sea correcto!

import gm.Estudiantes.modelo.Estudiante;
import gm.Estudiantes.servicio.EstudianteServicio;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image; // Necesario para los iconos de los botones de la tabla
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox; // Necesario para los botones de la tabla
import javafx.util.Callback;

import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ObservableValue;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException; // Aunque no se use directamente aquí, si necesitas IO en un futuro
import java.util.List;
import java.util.Optional; // Necesario para Alert.showAndWait()

@Component // ¡Es importante que este controlador también sea un componente de Spring!
public class StudentsManagementPanelController {

    private final EstudianteServicio estudianteServicio;

    // AÑADE EL CONSTRUCTOR CON @Autowired
    @Autowired
    public StudentsManagementPanelController(EstudianteServicio estudianteServicio) {
        this.estudianteServicio = estudianteServicio;
    }

    // Elementos del panel de Estudiantes (se mapearán desde students_management.fxml)
    @FXML private TextField txtNombre;
    @FXML private TextField txtApellido;
    @FXML private TextField txtTelefono;
    @FXML private TextField txtEmail;
    @FXML private TableView<Estudiante> estudiantesTable;
    @FXML private TableColumn<Estudiante, Integer> colId;
    @FXML private TableColumn<Estudiante, String> colNombre;
    @FXML private TableColumn<Estudiante, String> colApellido;
    @FXML private TableColumn<Estudiante, String> colTelefono;
    @FXML private TableColumn<Estudiante, String> colEmail;
    @FXML private TextField txtBuscarId;

    @FXML
    public void initialize() {
        estudianteData = FXCollections.observableArrayList(); // Asegúrate de inicializarla aquí

        colId.setCellValueFactory(new PropertyValueFactory<>("idEstudiante"));
        colNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        colApellido.setCellValueFactory(new PropertyValueFactory<>("apellido"));
        colTelefono.setCellValueFactory(new PropertyValueFactory<>("telefono"));
        colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));

        // Configurar el listener de selección de fila
        estudiantesTable.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> mostrarDetallesEstudianteEnFormulario(newValue));

        // Añadir columna de acciones (editar/eliminar)
        TableColumn<Estudiante, Void> colAcciones = new TableColumn<>("Acciones");
        colAcciones.setPrefWidth(100);
        colAcciones.setResizable(false);
        colAcciones.setSortable(false);
        colAcciones.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Estudiante, Void>, ObservableValue<Void>>() {
            @Override
            public ObservableValue<Void> call(TableColumn.CellDataFeatures<Estudiante, Void> param) {
                return new SimpleObjectProperty<>(null);
            }
        });
        colAcciones.setCellFactory(new Callback<TableColumn<Estudiante, Void>, TableCell<Estudiante, Void>>() {
            @Override
            public TableCell<Estudiante, Void> call(TableColumn<Estudiante, Void> param) {
                final TableCell<Estudiante, Void> cell = new TableCell<>() {
                    private final Button editButton = new Button();
                    private final Button deleteButton = new Button();

                    {
                        // Configurar iconos para los botones
                        ImageView editIcon = new ImageView(new Image(getClass().getResourceAsStream("/icons/edit_icon.png")));
                        editIcon.setFitHeight(16); editIcon.setFitWidth(16);
                        editButton.setGraphic(editIcon);
                        editButton.setTooltip(new Tooltip("Editar Estudiante"));
                        editButton.getStyleClass().add("action-button-icon");

                        ImageView deleteIcon = new ImageView(new Image(getClass().getResourceAsStream("/icons/delete_icon.png")));
                        deleteIcon.setFitHeight(16); deleteIcon.setFitWidth(16);
                        deleteButton.setGraphic(deleteIcon);
                        deleteButton.setTooltip(new Tooltip("Eliminar Estudiante"));
                        deleteButton.getStyleClass().add("action-button-icon");

                        editButton.setOnAction(event -> {
                            Estudiante data = getTableView().getItems().get(getIndex());
                            mostrarDetallesEstudianteEnFormulario(data);
                        });

                        deleteButton.setOnAction(event -> {
                            Estudiante data = getTableView().getItems().get(getIndex());
                            txtBuscarId.setText(String.valueOf(data.getIdEstudiante()));
                            eliminarEstudiante();
                        });
                    }

                    @Override
                    public void updateItem(Void item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                        } else {
                            HBox pane = new HBox(5, editButton, deleteButton);
                            setGraphic(pane);
                        }
                    }
                };
                return cell;
            }
        });
        estudiantesTable.getColumns().add(colAcciones);

        estudiantesTable.setItems(estudianteData);
        cargarEstudiantes(); // Cargar los datos al inicializar el panel
    }

    private ObservableList<Estudiante> estudianteData;

    // --- Métodos de Acción para la UI ---

    private void cargarEstudiantes() {
        List<Estudiante> estudiantes = estudianteServicio.listarEstudiantes();
        estudianteData.clear();
        estudianteData.addAll(estudiantes);
    }

    @FXML
    private void guardarEstudiante() {
        if (txtNombre.getText().isEmpty() || txtApellido.getText().isEmpty()) {
            showAlert(Alert.AlertType.WARNING, "Campos Requeridos", "Nombre y Apellido son obligatorios.");
            return;
        }

        Estudiante estudiante;
        Integer id = null;
        try {
            if (txtBuscarId != null && !txtBuscarId.getText().isEmpty()) { // Verificar si txtBuscarId no es nulo (solo disponible en students_management.fxml)
                id = Integer.parseInt(txtBuscarId.getText());
            } else if (estudiantesTable != null && estudiantesTable.getSelectionModel().getSelectedItem() != null) {
                // Si hay un estudiante seleccionado en la tabla, se usa su ID para actualizar
                id = estudiantesTable.getSelectionModel().getSelectedItem().getIdEstudiante();
            }
        } catch (NumberFormatException e) {
            showAlert(Alert.AlertType.ERROR, "ID Inválido", "El ID debe ser un número entero.");
            return;
        }

        if (id != null && id > 0) { // Es una modificación
            estudiante = estudianteServicio.buscarEstudiantePorId(id);
            if (estudiante != null) {
                estudiante.setNombre(txtNombre.getText());
                estudiante.setApellido(txtApellido.getText());
                estudiante.setTelefono(txtTelefono.getText());
                estudiante.setEmail(txtEmail.getText());
                estudianteServicio.modificarEstudiante(estudiante);
                showAlert(Alert.AlertType.INFORMATION, "Éxito", "Estudiante modificado correctamente.");
            } else {
                showAlert(Alert.AlertType.ERROR, "Error", "No se encontró estudiante con el ID: " + id + " para modificar.");
                return;
            }
        } else { // Es un nuevo estudiante
            estudiante = new Estudiante(
                    txtNombre.getText(),
                    txtApellido.getText(),
                    txtTelefono.getText(),
                    txtEmail.getText()
            );
            estudianteServicio.guardarEstudiante(estudiante);
            showAlert(Alert.AlertType.INFORMATION, "Éxito", "Estudiante agregado correctamente.");
        }

        limpiarCampos();
        cargarEstudiantes(); // Refrescar la tabla
    }

    @FXML // Este método se llama al hacer click en el botón "Buscar por ID"
    private void buscarEstudiante() {
        if (txtBuscarId == null || txtBuscarId.getText().isEmpty()) {
            showAlert(Alert.AlertType.WARNING, "Campo Vacío", "Por favor, ingresa un ID para buscar.");
            return;
        }
        try {
            Integer id = Integer.parseInt(txtBuscarId.getText());
            Estudiante estudiante = estudianteServicio.buscarEstudiantePorId(id);
            if (estudiante != null) {
                mostrarDetallesEstudianteEnFormulario(estudiante);
                // Opcional: seleccionar la fila en la tabla si se encuentra
                estudiantesTable.getSelectionModel().select(estudiante);
                showAlert(Alert.AlertType.INFORMATION, "Encontrado", "Estudiante encontrado: " + estudiante.getNombre() + " " + estudiante.getApellido());
            } else {
                showAlert(Alert.AlertType.INFORMATION, "No Encontrado", "No se encontró estudiante con ID: " + id);
                limpiarCampos();
            }
        } catch (NumberFormatException e) {
            showAlert(Alert.AlertType.ERROR, "ID Inválido", "Por favor, ingresa un ID numérico válido.");
        }
    }

    @FXML // Este método se llama al hacer click en el botón "Eliminar por ID"
    private void eliminarEstudiante() {
        if (txtBuscarId == null || txtBuscarId.getText().isEmpty()) {
            showAlert(Alert.AlertType.WARNING, "Campo Vacío", "Por favor, ingresa un ID para eliminar.");
            return;
        }
        try {
            Integer id = Integer.parseInt(txtBuscarId.getText());
            Estudiante estudiante = estudianteServicio.buscarEstudiantePorId(id);
            if (estudiante != null) {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Confirmar Eliminación");
                alert.setHeaderText("Eliminar Estudiante");
                alert.setContentText("¿Estás seguro de que quieres eliminar a " + estudiante.getNombre() + " " + estudiante.getApellido() + "?");

                Optional<ButtonType> result = alert.showAndWait();
                if (result.isPresent() && result.get() == ButtonType.OK) {
                    estudianteServicio.eliminarEstudiante(estudiante);
                    showAlert(Alert.AlertType.INFORMATION, "Éxito", "Estudiante eliminado correctamente.");
                    limpiarCampos();
                    cargarEstudiantes();
                }
            } else {
                showAlert(Alert.AlertType.INFORMATION, "No Encontrado", "No se encontró estudiante con ID: " + id);
            }
        } catch (NumberFormatException e) {
            showAlert(Alert.AlertType.ERROR, "ID Inválido", "Por favor, ingresa un ID numérico válido.");
        }
    }

    @FXML // Este método se llama al hacer click en el botón "Limpiar Campos"
    private void limpiarCampos() {
        if (txtNombre != null) txtNombre.clear();
        if (txtApellido != null) txtApellido.clear();
        if (txtTelefono != null) txtTelefono.clear();
        if (txtEmail != null) txtEmail.clear();
        if (txtBuscarId != null) txtBuscarId.clear();
        if (estudiantesTable != null) estudiantesTable.getSelectionModel().clearSelection();
    }

    private void mostrarDetallesEstudianteEnFormulario(Estudiante estudiante) {
        if (estudiante != null) {
            if (txtNombre != null) txtNombre.setText(estudiante.getNombre());
            if (txtApellido != null) txtApellido.setText(estudiante.getApellido());
            if (txtTelefono != null) txtTelefono.setText(estudiante.getTelefono());
            if (txtEmail != null) txtEmail.setText(estudiante.getEmail());
            if (txtBuscarId != null) txtBuscarId.setText(String.valueOf(estudiante.getIdEstudiante()));
        } else {
            limpiarCampos();
        }
    }

    private void showAlert(Alert.AlertType type, String title, String message) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

}