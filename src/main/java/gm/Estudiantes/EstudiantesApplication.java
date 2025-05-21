package gm.Estudiantes;

import javafx.application.Application; // Importar la clase base de JavaFX Application
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class EstudiantesApplication {

	private static final Logger logger = LoggerFactory.getLogger(EstudiantesApplication.class);

	public static void main(String[] args) {
		logger.info("Iniciando contexto de Spring Boot para aplicación JavaFX...");
		// Este es el punto de entrada principal para tu aplicación JavaFX.
		// La clase JavaFxApplication se encargará de inicializar el contexto de Spring
		// y luego de lanzar la interfaz de usuario.
		Application.launch(JavaFxApplication.class, args);

		// Este mensaje se mostrará una vez que la aplicación JavaFX se haya cerrado
		// y el contexto de Spring Boot también se haya detenido.
		logger.info("Aplicación Spring Boot (y JavaFX) finalizada.");
	}
}