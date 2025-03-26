# Backend Evaluación

Este es el backend para la gestión de solicitudes, diseñado para interactuar con una base de datos relacional. El backend permite realizar operaciones sobre las solicitudes, como listar, buscar, guardar y exportar datos en formato CSV.

## Tecnologías utilizadas

- **Spring Boot**: Framework para el desarrollo de aplicaciones backend en Java.
- **Spring WebFlux**: Soporte para aplicaciones reactivas.
- **Spring Data**: Integración con bases de datos relacionales usando repositorios.
- **Swagger/OpenAPI**: Generación de documentación interactiva para la API.
- **Reactive Programming**: Uso de `Mono` y `Flux` de Project Reactor para programación reactiva.
- **Apache Commons CSV**: Biblioteca para generar archivos CSV.
- **PostgreSQL**: Base de datos relacional para almacenar solicitudes y contactos.

## Instalación

1. Clona el repositorio en tu máquina local:

    ```bash
    git clone <URL del repositorio>
    ```

2. Abre el proyecto en tu IDE preferido (por ejemplo, IntelliJ IDEA o Eclipse).

3. Asegúrate de tener configurada una base de datos PostgreSQL en tu entorno local.

4. Configura los parámetros de conexión a la base de datos en `src/main/resources/application.properties`:

    ```properties
    spring.datasource.url=jdbc:postgresql://localhost:5432/tu_base_de_datos
    spring.datasource.username=tu_usuario
    spring.datasource.password=tu_contraseña
    ```

5. Compila y ejecuta el proyecto:

    ```bash
    ./mvnw spring-boot:run
    ```

    Esto levantará el servidor en el puerto `8080` por defecto.

## Endpoints disponibles

### Solicitudes

- **`GET /api/solicitud/listar`**: Lista todas las solicitudes.
- **`GET /api/solicitud/buscar/{id}`**: Busca una solicitud por ID.
- **`POST /api/solicitud/guardar`**: Guarda una nueva solicitud (con los contactos relacionados).
- **`GET /api/solicitud/exportar`**: Exporta todas las solicitudes a un archivo CSV.

### Contactos

Los contactos están asociados a una solicitud, por lo tanto, los endpoints específicos para contactos se gestionan a través de las solicitudes.

## Estructura de la Base de Datos

### Tablas

#### `solicitud`

| Campo              | Tipo           | Descripción                          |
|--------------------|----------------|--------------------------------------|
| `id_solicitud`     | SERIAL         | ID único de la solicitud (Primary Key) |
| `marca`            | VARCHAR(255)    | Marca asociada a la solicitud         |
| `tipo_solicitud`   | VARCHAR(255)    | Tipo de solicitud                    |
| `fecha_envio`      | DATE           | Fecha de envío de la solicitud       |
| `numero_contacto`  | VARCHAR(20)     | Número de contacto principal         |
| `nombre_contacto`  | VARCHAR(255)    | Nombre del contacto principal        |

#### `contacto`

| Campo             | Tipo           | Descripción                          |
|-------------------|----------------|--------------------------------------|
| `id_contacto`     | SERIAL         | ID único del contacto (Primary Key)  |
| `id_solicitud`    | INT            | ID de la solicitud relacionada       |
| `nombre`          | VARCHAR(255)    | Nombre del contacto                  |
| `numero`          | VARCHAR(20)     | Número de contacto                   |

### Relaciones

- Una solicitud puede tener múltiples contactos, lo que se representa en la tabla `contacto` con la clave foránea `id_solicitud`.

## Swagger/OpenAPI

La documentación de la API está disponible en:

- **`/swagger-ui.html`**: Para acceder a la interfaz interactiva de Swagger.

## Ejemplo de Peticiones

### Crear Solicitud

**POST** `/api/solicitud/guardar`

```json
{
  "marca": "A",
  "tipoSolicitud": "Solicitud 1",
  "fechaEnvio": "2025-03-25",
  "numeroContacto": "123456789",
  "nombreContacto": "Juan Calderon",
  "contactos": [
    {
      "nombre": "Carlos Martínez",
      "numero": "555123456"
    },
    {
      "nombre": "Lucía Fernández",
      "numero": "555654321"
    }
  ]
}
