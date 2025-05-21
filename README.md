# Sistema de Gestión de Estudiantes

Este proyecto es una aplicación de escritorio moderna y robusta diseñada para la gestión eficiente de información de estudiantes. Combina la potencia del backend de **Spring Boot** con la riqueza visual de **JavaFX** para ofrecer una experiencia de usuario fluida e intuitiva. Su objetivo principal es proporcionar una solución completa para las operaciones CRUD (Crear, Leer, Actualizar, Eliminar) de los datos de los estudiantes.

---

## ✨ Características Destacadas

- **Gestión Integral de Estudiantes (CRUD):** Permite añadir nuevos estudiantes, visualizar la lista completa y organizada de todos los estudiantes, modificar la información existente y eliminar registros cuando sea necesario.
- **Interfaz de Usuario Envolvente con JavaFX:** Desarrollada con FXML y CSS para un diseño visual atractivo e intuitivo. Incluye un dashboard principal y una barra lateral que facilita el acceso a las diferentes secciones.
- **Persistencia de Datos Robusta:** Integración con Microsoft SQL Server usando Hibernate y Spring Data JPA para un mapeo objeto-relacional (ORM) eficiente.
- **Arquitectura Sólida y Mantenible:** Separación en capas (UI, Servicio, Repositorio, Modelo) que promueve la modularidad, facilita el mantenimiento y la escalabilidad.
- **Inyección de Dependencias con Spring:** Aprovecha el contenedor de Inversión de Control (IoC) de Spring para gestionar componentes de forma automática, agilizando el desarrollo y las pruebas unitarias.

---

## 🔧 Tecnologías Utilizadas

- **Backend:** Spring Boot, Spring Data JPA, Hibernate
- **Base de Datos:** Microsoft SQL Server
- **Driver JDBC:** Microsoft JDBC Driver para SQL Server (`mssql-jdbc`)
- **Frontend:** JavaFX (FXML + CSS)
- **Construcción:** Maven

---

## 🗂 Estructura del Proyecto

```
src/
└── main/
    ├── java/
    │   └── gm/Estudiantes/
    │       ├── modelo/
    │       ├── repositorio/
    │       ├── servicio/
    │       └── ui/
    │           └── panels/
    │               ├── StudentsManagementPanelController.java
    │               └── EstudiantesDashboardUIController.java
    │           ├── EstudiantesApplication.java
    │           └── JavaFxApplication.java
    ├── resources/
    │   ├── icons/
    │   ├── application.properties
    │   ├── dashboard.fxml
    │   ├── dashboard_summary.fxml
    │   ├── students_management.fxml
    │   ├── logback-spring.xml
    │   └── style.css
    └── test/

.gitattributes  
.gitignore  
HELP.md  
pom.xml  
README.md  
```

**Descripciones:**
- `modelo/`: Entidades de la base de datos (`Estudiante.java`, etc.)
- `repositorio/`: Interfaces de acceso a datos (por ejemplo `EstudianteRepositorio.java`)
- `servicio/`: Lógica de negocio (`EstudianteServicio.java`, etc.)
- `ui/`: Controladores de la interfaz JavaFX
- `resources/`: Archivos de configuración, estilos, FXML y recursos visuales
- `application.properties`: Configuración de Spring Boot y base de datos
- `pom.xml`: Archivo de configuración de Maven

---

## 🗃 Base de Datos

**Microsoft SQL Server**: Sistema de gestión de bases de datos relacional utilizado para almacenar los datos de los estudiantes.

---

## ✅ Estado

Proyecto en desarrollo 🚧. Próximas versiones incluirán autenticación de usuarios y funcionalidades avanzadas de filtrado y búsqueda.
