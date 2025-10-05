# 🛒 Cart App Backend

Una aplicación de carrito de compras desarrollada con **Spring Boot** y **MySQL** como parte del aprendizaje de desarrollo backend.

## 📋 Descripción del Proyecto

Esta es una API REST para gestión de productos de un carrito de compras. El proyecto utiliza Spring Boot con JPA/Hibernate para la persistencia de datos y MySQL como base de datos.

### 🚀 Tecnologías Utilizadas

- **Java 17+**
- **Spring Boot 3.x**
- **Spring Data JPA**
- **Hibernate**
- **MySQL 8.0**
- **Docker & Docker Compose**
- **Maven**

## 🗂 Estructura del Proyecto

``` Estructura
backend-cartapp/
├── src/
│   ├── main/
│   │   ├── java/com/backend/cartapp/backend_cartapp/
│   │   │   ├── BackendCartappApplication.java      # Clase principal
│   │   │   ├── controllers/
│   │   │   │   └── ProductController.java          # API REST endpoints
│   │   │   ├── models/entities/
│   │   │   │   └── Product.java                    # Entidad JPA
│   │   │   ├── repositories/
│   │   │   │   └── ProductRepository.java          # Acceso a datos
│   │   │   └── services/
│   │   │       ├── ProductService.java             # Interfaz de servicio
│   │   │       └── ProductServiceImpl.java         # Implementación
│   │   └── resources/
│   │       ├── application.properties               # Configuración
│   │       └── import.sql                          # Datos iniciales
│   └── test/
├── docker-compose.yml                              # Configuración MySQL
├── pom.xml                                         # Dependencias Maven
└── README.md
```

## 🛢 Base de Datos

### Esquema de la tabla `products`

| Campo | Tipo | Descripción |
|-------|------|-------------|
| `id` | BIGINT AUTO_INCREMENT | Identificador único |
| `name` | VARCHAR(100) NOT NULL | Nombre del producto |
| `description` | VARCHAR(500) | Descripción del producto |
| `price` | DECIMAL(10,2) NOT NULL | Precio con 2 decimales |

## 🐳 Configuración con Docker

### Prerrequisitos

- Docker y Docker Compose instalados
- Java 17+ (para desarrollo)
- Maven (incluido en el proyecto como wrapper)

### 1. Configurar MySQL con Docker

El proyecto incluye un archivo `docker-compose.yml` para levantar MySQL automáticamente:

```yaml
services:
  mysql:
    image: mysql:8.0
    container_name: mysql-cartapp
    environment:
      MYSQL_ROOT_PASSWORD: sasa1234
      MYSQL_DATABASE: db_cart_springboot
      MYSQL_USER: cartapp_user
      MYSQL_PASSWORD: sasa1234
    ports:
      - "3306:3306"
    volumes:
      - mysql_data:/var/lib/mysql
    restart: unless-stopped

volumes:
  mysql_data:
```

### 2. Levantar la Base de Datos

```bash
# Iniciar MySQL en Docker
docker compose up -d

# Verificar que está corriendo
docker compose ps

# Ver logs (opcional)
docker compose logs mysql
```

### 3. Configuración de Conexión

La aplicación se conecta a MySQL usando estas credenciales (definidas en `application.properties`):

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/db_cart_springboot
spring.datasource.username=cartapp_user
spring.datasource.password=sasa1234
```

## 🚀 Instalación y Ejecución

### 1. Clonar el repositorio

```bash
git clone <tu-repositorio>
cd backend-cartapp
```

### 2. Levantar MySQL

```bash
docker compose up -d
```

### 3. Ejecutar la aplicación

```bash
# Usando Maven wrapper (recomendado)
./mvnw spring-boot:run

# O con Maven instalado
mvn spring-boot:run
```

### 4. Verificar funcionamiento

```bash
# Health check
curl http://localhost:8080/actuator/health

# Listar productos (endpoint ejemplo)
curl http://localhost:8080/api/products
```

## 🔧 Configuración Detallada

### application.properties

```properties
# Configuración de la aplicación
spring.application.name=backend-cartapp

# Configuración de MySQL
spring.datasource.url=jdbc:mysql://localhost:3306/db_cart_springboot
spring.datasource.username=cartapp_user
spring.datasource.password=sasa1234
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver

# Configuración de JPA/Hibernate
spring.jpa.show-sql=true
spring.jpa.hibernate.ddl-auto=update
spring.jpa.open-in-view=false

# Logging para desarrollo
logging.level.org.hibernate.SQL=DEBUG
```

### Explicación de configuraciones importantes

- **`ddl-auto=update`**: Crea/actualiza tablas automáticamente (ideal para desarrollo)
- **`show-sql=true`**: Muestra las consultas SQL en consola
- **`open-in-view=false`**: Mejora performance deshabilitando OSIV

## 🧪 Desarrollo y Testing

### Conectar a MySQL manualmente

```bash
# Conectar desde terminal
docker exec -it mysql-cartapp mysql -u cartapp_user -psasa1234

# En MySQL:
USE db_cart_springboot;
SHOW TABLES;
DESCRIBE products;
```

### Datos de prueba

El proyecto incluye un archivo `import.sql` que se ejecuta automáticamente para insertar datos iniciales.

## 🔍 Endpoints API (Ejemplos)

```bash
# GET - Listar productos
curl http://localhost:8080/api/products

# POST - Crear producto
curl -X POST http://localhost:8080/api/products \
  -H "Content-Type: application/json" \
  -d '{"name":"Laptop","description":"Laptop gaming","price":999.99}'

# GET - Obtener producto por ID
curl http://localhost:8080/api/products/1
```

## 🛠 Herramientas de Desarrollo Recomendadas

### Extensiones VS Code

- **Database Client**: Para conectar a MySQL desde VS Code
- **Extension Pack for Java**: Soporte completo para Java
- **Spring Boot Extension Pack**: Herramientas Spring Boot

### Configuración Database Client

``` Configuracion
Host: localhost
Port: 3306
Username: cartapp_user
Password: sasa1234
Database: db_cart_springboot
```

## 📝 Notas Importantes

### Para desarrollo

- ✅ Usar `spring.jpa.hibernate.ddl-auto=update`
- ✅ Mantener logs SQL habilitados
- ✅ Docker para aislamiento de BD

### Para producción (futuro)

- ⚠️ Cambiar a `ddl-auto=validate`
- ⚠️ Usar migraciones con Flyway/Liquibase
- ⚠️ Configurar variables de entorno para credenciales

## 🚧 Troubleshooting

### MySQL no conecta

```bash
# Verificar que Docker esté corriendo
docker compose ps

# Reiniciar contenedor
docker compose down && docker compose up -d
```

### Aplicación no inicia

```bash
# Verificar Java version
java -version

# Limpiar y recompilar
./mvnw clean compile
```

### Ver logs de MySQL

```bash
docker compose logs mysql -f
```

## 🎯 Próximos Pasos

- [ ] Implementar más endpoints CRUD
- [ ] Agregar validaciones
- [ ] Implementar tests unitarios
- [ ] Documentación con Swagger
- [ ] Seguridad con Spring Security
- [ ] Migraciones con Flyway

---

### Desarrollado como proyecto de aprendizaje Spring Boot + MySQL
