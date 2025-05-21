Sistema de Gestión de Estudiantes

Este proyecto es una aplicación de escritorio robusta diseñada para la gestión eficiente de información de estudiantes, utilizando la potencia de Spring Boot para el backend y JavaFX para una interfaz de usuario rica y moderna. Ofrece una solución completa para operaciones CRUD (Crear, Leer, Actualizar, Eliminar) de estudiantes, facilitando la administración de sus datos.

Características Principales:
Gestión Completa de Estudiantes (CRUD): Permite añadir nuevos estudiantes, visualizar la lista completa, modificar sus datos y eliminarlos de la base de datos.
Interfaz de Usuario Intuitiva con JavaFX: Desarrollada con FXML y CSS para una experiencia de usuario limpia y navegable, incluyendo un diseño de dashboard con una barra lateral para facilitar la navegación entre diferentes secciones.
Integración con Base de Datos SQL Server: Utiliza Hibernate y Spring Data JPA para una persistencia de datos ORM eficiente y una interacción fluida con una base de datos SQL Server.
Arquitectura Orientada a Capas: El proyecto sigue una estructura de capas bien definida (UI, Servicio, Repositorio, Modelo) para asegurar la modularidad, mantenibilidad y escalabilidad del código.
Inyección de Dependencias con Spring: Aprovecha el contenedor de inversión de control (IoC) de Spring para gestionar las dependencias entre los componentes, facilitando el desarrollo y las pruebas.
Tecnologías Utilizadas:
Backend:
Spring Boot: Framework para la creación rápida y sencilla de aplicaciones Java.
Spring Data JPA: Simplifica el acceso a datos y la implementación de repositorios.
Hibernate: Implementación de JPA para mapeo objeto-relacional.
JDBC Driver para SQL Server (mssql-jdbc): Conexión con la base de datos Microsoft SQL Server.
Frontend (UI):
JavaFX: Toolkit para la creación de interfaces gráficas de usuario en Java.
FXML: Lenguaje basado en XML para definir la estructura de la UI.
CSS: Para estilizar la interfaz de usuario, ofreciendo un diseño visual atractivo.
Herramienta de Construcción:
Apache Maven: Para la gestión de dependencias y el ciclo de vida del proyecto.
Base de Datos:
Microsoft SQL Server: Sistema de gestión de bases de datos relacionales.
Estructura del Proyecto:
El proyecto está organizado de la siguiente manera:

src/main/java/gm/Estudiantes/modelo/: Contiene la clase Estudiante que representa la entidad de la base de datos.
src/main/java/gm/Estudiantes/repositorio/: Define la interfaz EstudianteRepositorio para las operaciones de persistencia.
src/main/java/gm/Estudiantes/servicio/: Implementa la lógica de negocio con EstudianteServicio e IEstudianteServicio.
src/main/java/gm/Estudiantes/EstudiantesApplication.java: Clase principal para el inicio de la aplicación Spring Boot y JavaFX.
src/main/resources/: Contiene los archivos FXML (como dashboard.fxml, students_management.fxml), hojas de estilo CSS (style.css), propiedades de la aplicación (application.properties) e iconos.
pom.xml: Archivo de configuración de Maven para dependencias y construcción del proyecto.
