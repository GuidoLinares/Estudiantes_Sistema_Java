package gm.Estudiantes;

import gm.Estudiantes.ui.EstudiantesDashboardUIController; // Importar el controlador de tu UI
import javafx.application.Application;
import javafx.application.Platform;
import javafx.stage.Stage;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

public class JavaFxApplication extends Application {

    private ConfigurableApplicationContext applicationContext;

    @Override
    public void init() {
        // Este método se llama antes de start().
        // Aquí inicializamos el contexto de Spring Boot.
        // SpringApplicationBuilder nos permite construir y ejecutar el contexto.
        applicationContext = new SpringApplicationBuilder(EstudiantesApplication.class).run();
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        // Este método es el punto de entrada de JavaFX.
        // Aquí obtenemos el controlador de nuestra UI desde el contexto de Spring
        // y le pedimos que muestre la ventana principal.

        // Obtener el bean del controlador de JavaFX desde el contexto de Spring
        EstudiantesDashboardUIController controller = applicationContext.getBean(EstudiantesDashboardUIController.class);

        // Pedir al controlador que muestre la ventana principal
        controller.show(primaryStage); // Le pasamos el Stage principal de JavaFX

        // Configurar el cierre de la aplicación
        primaryStage.setOnCloseRequest(event -> {
            Platform.exit(); // Cierra la plataforma JavaFX
            applicationContext.close(); // Cierra el contexto de Spring Boot
        });
    }

    @Override
    public void stop() {
        // Este método se llama cuando la aplicación JavaFX se detiene.
        // Aseguramos que el contexto de Spring Boot también se cierre correctamente.
        applicationContext.close();
    }
}