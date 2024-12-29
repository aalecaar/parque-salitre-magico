
# Parque Salitre Mágico

Hecho por Alejandro Cardona

## Requisitos Previos
- Java Development Kit (JDK) 21
- Maven 3.x
- Un IDE de tu preferencia (IntelliJ IDEA, Eclipse, VS Code, etc.)

## Configuración del Entorno
1. Clona el repositorio : `https://github.com/aalecaar/parque-salitre-magico.git`
2. Asegúrate de tener configurado JDK 21 en tu sistema
3. Importa el proyecto como un proyecto Maven en tu IDE

## Ejecución del Proyecto
1. Ejecuta la clase principal ubicada en el siguiente path: `src/main/java/dev/alecar/parquesalitremagico/ParqueSalitreMagicoApplication.java`
2. El servidor se iniciará en `http://localhost:8080`

## Ejecución de Tests
1. Ejecuta la clase ubicada en el siguiente path: `src/test/java/dev/alecar/parquesalitremagico/ParqueSalitreMagicoApplicationTests.java`

## Base de Datos H2
El proyecto utiliza una base de datos H2 en memoria. Puedes acceder a la consola de H2 en:
- URL: `http://localhost:8080/h2-console`
- Configuración de conexión:
  - JDBC URL: `jdbc:h2:mem:testdb`
- Usuario: `sa`
- Contraseña: `password`
- Driver Class: `org.h2.Driver`

## Características Principales  del Proyecto
- Gestión de clientes y empleados
- Control de atracciones y estaciones
- Sistema de visitas y promociones
- Monitoreo de ocupación del parque

## Estructura del Proyecto
- `/src/main/java/dev/alecar/parquesalitremagico`: Código fuente Java
- `/src/main/resources/templates`: Plantillas HTML (Thymeleaf)
- `/src/main/resources/static`: Recursos estáticos (CSS)

## Datos de Prueba
Al iniciar la aplicación, se cargan automáticamente datos de prueba que incluyen:
- 5 estaciones
- 5 empleados con diferentes roles
- 3 atracciones
- 10 clientes de prueba

## Endpoints Principales
- `/`: Página principal
- `/clientes`: Gestión de clientes
- `/empleados`: Gestión de empleados
- `/atracciones`: Gestión de atracciones
- `/estaciones`: Gestión de estaciones
- `/parque/ocupacion`: Vista de ocupación del parque
- `/visitas/verificar`: Verificación de acceso a atracciones

## Tecnologías Utilizadas
- Java 21
- Spring Boot 3.4.1
- Thymeleaf
- H2 Database
- JPA/Hibernate
- Lombok
- Maven
