Sistema de Gestión de Estudiantes
Este proyecto es una aplicación de escritorio moderna y robusta diseñada para la gestión eficiente de información de estudiantes. Combina la potencia del backend de Spring Boot con la riqueza visual de JavaFX para ofrecer una experiencia de usuario fluida e intuitiva. Su objetivo principal es proporcionar una solución completa para las operaciones CRUD (Crear, Leer, Actualizar, Eliminar) de los datos de los estudiantes.

✨ Características Destacadas:
Gestión Integral de Estudiantes (CRUD): Permite añadir nuevos estudiantes, visualizar la lista completa y organizada de todos los estudiantes, modificar la información existente de cualquier estudiante y eliminar registros de estudiantes cuando sea necesario.
Interfaz de Usuario Envolvente con JavaFX: Desarrollada con FXML y CSS para un diseño visual atractivo y una navegación intuitiva. Incluye un dashboard principal y una barra lateral que facilita el acceso a las diferentes secciones de la aplicación.
Persistencia de Datos Robusta: Integración perfecta con Microsoft SQL Server como base de datos. Utiliza Hibernate y Spring Data JPA para un mapeo objeto-relacional (ORM) eficiente y una interacción simplificada con la base de datos.
Arquitectura Sólida y Mantenible: Implementa una arquitectura en capas bien definida (UI, Servicio, Repositorio, Modelo) para promover la modularidad, facilitar el mantenimiento y asegurar la escalabilidad del código.
Inyección de Dependencias con Spring: Aprovecha el contenedor de Inversión de Control (IoC) de Spring para gestionar automáticamente las dependencias entre los componentes, lo que agiliza el desarrollo y simplifica las pruebas unitarias.
🛠️ Tecnologías Utilizadas:
Backend:
Spring Boot: Framework líder para el desarrollo rápido de aplicaciones Java empresariales.
Spring Data JPA: Simplifica enormemente el acceso a datos y la implementación de repositorios, reduciendo el código boilerplate.
Hibernate: La implementación de JPA por excelencia para el mapeo objeto-relacional.
JDBC Driver para SQL Server (mssql-jdbc): Conexión eficiente y fiable con bases de datos Microsoft SQL Server.
Frontend (Interfaz de Usuario):
JavaFX: Toolkit moderno y potente para construir interfaces gráficas de usuario en Java.
FXML: Lenguaje declarativo basado en XML para definir la estructura de la UI.
CSS: Utilizado para estilizar y personalizar la apariencia de la interfaz de usuario.
Herramienta de Construcción:
Apache Maven: Gestiona las dependencias del proyecto y automatiza el ciclo de vida de la construcción.
Base de Datos:
Microsoft SQL Server: Sistema de gestión de bases de datos relacionales utilizado para el almacenamiento de datos.
📂 Estructura del Proyecto:
El proyecto sigue una estructura de directorios lógica para una fácil comprensión y navegación:

├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── gm/Estudiantes/
│   │   │       ├── modelo/                 # Entidades de la base de datos (Ej: Estudiante.java)
│   │   │       ├── repositorio/            # Interfaces de repositorio (Ej: EstudianteRepositorio.java)
│   │   │       ├── servicio/               # Lógica de negocio (Ej: EstudianteServicio.java, IEstudianteServicio.java)
│   │   │       ├── ui/                     # Controladores de la interfaz de usuario (Ej: EstudiantesDashboardUIController.java)
│   │   │       └── EstudiantesApplication.java # Clase principal de la aplicación Spring Boot y JavaFX
│   │   └── resources/
│   │       ├── icons/                  # Iconos utilizados en la UI
│   │       ├── application.properties  # Configuración de Spring Boot y la base de datos
│   │       ├── dashboard.fxml          # Diseño FXML del panel principal
│   │       ├── dashboard_summary.fxml  # Diseño FXML del resumen del dashboard
│   │       ├── logback-spring.xml      # Configuración de logging
│   │       ├── students_management.fxml # Diseño FXML para la gestión de estudiantes
│   │       └── style.css               # Hoja de estilos CSS para la aplicación
│   └── test/                       # Pruebas unitarias e de integración
├── pom.xml                         # Archivo de configuración de Maven
└── README.md                       # Este archivo
