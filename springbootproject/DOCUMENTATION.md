# ArtistHub - Artist Booking Application

## Project Overview
ArtistHub is a comprehensive web application built with Spring Boot for connecting artists with customers. It provides a platform where artists can showcase their profiles, receive bookings, and manage their schedules, while customers can search for artists, make bookings, and leave reviews. The application also includes an administrative dashboard for managing users and platform content.

## Technology Stack
- **Backend:** Java 17, Spring Boot 3.2.2
- **Database:** MySQL 8.0
- **ORM:** Hibernate / Spring Data JPA
- **Security:** Spring Security 6 (Hybrid: JWT for API + Session for Web)
- **Frontend:** JSP (JavaServer Pages), Bootstrap 5
- **Build Tool:** Maven
- **Packaging:** WAR (Web Application Archive)

## Prerequisites
Before running the application, ensure you have the following installed:
- **Java Development Kit (JDK) 17** or higher
- **Maven 3.6+**
- **MySQL Server** running on port 3306

## Database Configuration
The application is configured to automatically create the database schema.
1. Create a MySQL database named `artisthub`:
   ```sql
   CREATE DATABASE artisthub;
   ```
2. Verify the configuration in `src/main/resources/application.properties`:
   ```properties
   spring.datasource.url=jdbc:mysql://localhost:3306/artisthub?createDatabaseIfNotExist=true&useSSL=false&allowPublicKeyRetrieval=true
   spring.datasource.username=root
   spring.datasource.password=root
   ```
   **Note:** Update the username and password if your local MySQL configuration differs.

## Installation & Running

1. **Clone/Navigate to the project directory:**
   ```bash
   cd c:\Users\HP\Desktop\springbootproject
   ```

2. **Build the project:**
   This step downloads dependencies and packages the application as a WAR file.
   ```bash
   mvn clean package
   ```

3. **Run the application:**
   You can run it directly using the Spring Boot Maven plugin:
   ```bash
   mvn spring-boot:run
   ```
   Alternatively, run the packaged WAR file:
   ```bash
   java -jar target/ArtistHub-0.0.1-SNAPSHOT.war
   ```

4. **Access the application:**
   Open your browser and navigate to: [http://localhost:8080/](http://localhost:8080/)

## Eclipse Setup
To work on this project using Eclipse IDE:

1. **Install Lombok:**
   - Download `lombok.jar` from [https://projectlombok.org/download](https://projectlombok.org/download).
   - Double-click `lombok.jar`. It should find your Eclipse installation.
   - Click "Install/Update" and restart Eclipse.
   - *This is required to avoid getters/setters errors.*

2. **Import Project:**
   - Open Eclipse.
   - Go to `File` > `Import` > `Maven` > `Existing Maven Projects`.
   - Browse to the project folder (`c:\Users\HP\Desktop\springbootproject`).
   - Click `Finish`.

3. **Update Dependencies:**
   - Right-click the project folder in "Package Explorer".
   - Go to `Maven` > `Update Project...`.
   - Check "Force Update of Snapshots/Releases" and click `OK`.

4. **Run Server:**
   - Right-click `ArtistHubApplication.java` (in `src/main/java/com/artisthub`).
   - Select `Run As` > `Java Application` (or `Spring Boot App`).

5. **Stop Server:**
   - Click the red square icon in the Console view.

## User Roles & Credentials

### Default Administrator
- **Email:** `admin@artisthub.com`
- **Password:** `admin123`
- **Access:** Full control over artists and bookings. URL: `/admin/dashboard`

### Artist
- **Registration:** Go to `/register`, select "Artist", fill in details.
- **Capabilities:** Update profile, view bookings, manage availability.
- **Access:** URL: `/artist/dashboard`

### Customer
- **Registration:** Go to `/register`, select "Customer".
- **Capabilities:** Browse artists, book events, view booking history.
- **Access:** URL: `/customer/dashboard`

## Architecture & Design

### Layered Architecture
- **Controller Layer:** Handles HTTP requests (REST API & Web Views).
- **Service Layer:** Business logic implementation.
- **Repository Layer:** Data access using Spring Data JPA.
- **Entity Layer:** Domain objects mapped to database tables.

### Security Implementation
- **Hybrid Approach:**
  - **API (`/api/**`):** Stateless JWT (JSON Web Token) authentication.
  - **Web (`/**`):** Session-based authentication with Form Login.
- **Role-Based Access Control (RBAC):**
  - `@PreAuthorize` annotations secure endpoints based on roles (ADMIN, ARTIST, CUSTOMER).

### Internal Structure
- `src/main/java/com/artisthub`
    - `config`: Configuration classes (DataInitializer).
    - `controller`: Web and REST controllers.
    - `dto`: Data Transfer Objects for API communication.
    - `entity`: JPA Entities (`User`, `Artist`, `Booking`, etc.).
    - `repository`: Data access interfaces.
    - `security`: Security configuration, JWT utilities, UserDetails service.
    - `service`: Business logic services.
- `src/main/webapp/WEB-INF/jsp`
    - `admin`, `artist`, `customer`: Dashboard views.
    - `common`: Shared fragments (header, footer).

## Troubleshooting

### `ERR_TOO_MANY_REDIRECTS`
- **Cause:** Spring Security interception loop on internal forwarded requests.
- **Solution:** Ensure `DispatcherType.FORWARD` and `DispatcherType.ERROR` are permitted in `SecurityConfig.java`. (Fixed in current version).

### `404 Not Found` on Login/Pages
- **Cause:** JSP files not being found or processed.
- **Solution:** Ensure `pom.xml` has `<packaging>war</packaging>` and the `tomcat-embed-jasper` dependency. Run via `mvn spring-boot:run` or as a WAR file.

### `500 Internal Server Error` (sec:authorize)
- **Cause:** Missing `spring-security-taglibs` library.
- **Solution:** Ensure the dependency is present in `pom.xml`. (Fixed in current version).

## REST API Documentation
The application exposes a RESTful API for external clients or mobile apps.
- **Base URL:** `http://localhost:8080/api`
- **Authentication:** POST `/api/auth/signin` returns a Bearer Token.

| Method | Endpoint | Description | Role |
|--------|----------|-------------|------|
| POST | `/api/auth/signup` | Register new user | Public |
| GET | `/api/customer/artists` | Get approved artists | Public |
| POST | `/api/customer/book` | Book an artist | Customer |
| GET | `/api/artist/bookings/{id}` | Get artist bookings | Artist |
| GET | `/api/admin/artists` | Get all artists | Admin |
| PUT | `/api/admin/approve-artist/{id}` | Approve artist | Admin |
