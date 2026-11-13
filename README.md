#  Andes Airlines - Check-in API

API RESTful desarrollada con Spring Boot para gestionar el proceso de Check-in automático de pasajeros, asignando asientos de manera inteligente priorizando a menores de edad y grupos familiares.

##  Despliegue (Demo en Vivo)

La API se encuentra desplegada en **Render** y conectada a la base de datos de producción provista para el desafío:

* **Motor:** MySQL
* **Host:** `mdb-test.c6vunyturrl6.us-west-1.rds.amazonaws.com`
* **Usuario:** `postulaciones`
* **Contraseña:** `post123456`
* **Base de datos:** `airline`

**URL Base:** [https://ejercicio-andes-airlines.onrender.com](https://ejercicio-andes-airlines.onrender.com)

 **Importante:** El servicio está alojado en el plan gratuito de Render. 

### Endpoint de Prueba Requerido
Puedes probar la asignación de asientos del Vuelo 1 aquí:
- [GET /flights/1/passengers](https://ejercicio-andes-airlines.onrender.com/flights/1/passengers)

---

##  Tecnologías Utilizadas

* **Lenguaje:** Java 21 (JDK 21)
* **Framework:** Spring Boot 3.x
* **Base de Datos:** MySQL 8 (Producción en AWS / Local con Docker)
* **ORM:** Spring Data JPA / Hibernate
* **Gestión de Dependencias:** Maven
* **Infraestructura:** Render (Deploy automático desde GitHub)

---

##  Ejecución Local

Para ejecutar la API en tu entorno local conectándote directamente a la base de datos de prueba, sigue estos pasos:

### Prerrequisitos
* **Java 21** (JDK)
* **Maven** instalado y configurado en el PATH.

### Pasos

1.  **Clonar el repositorio:**
    ```bash
    git clone [https://github.com/Camila-guti/Ejercicio-Andes-Airlines.git](https://github.com/Camila-guti/Ejercicio-Andes-Airlines.git)
    cd Ejercicio-Andes-Airlines
    ```

2. 
3.  **Ejecutar la aplicación:**
    ```bash
    mvn spring-boot:run
    ```

---

## Pruebas y Testing

Las pruebas manuales durante el desarrollo se realizaron utilizando la extensión **Thunder Client** en Visual Studio Code.

Si estás ejecutando el proyecto localmente, puedes probar el endpoint principal en:

 `http://localhost:8080/flights/1/passengers`



