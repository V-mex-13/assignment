# ArtistHub

ArtistHub is a Spring Boot web application for booking artists for events.

## Quick Start

1. **Prerequisites:** Java 17+, Maven, MySQL.
2. **Database:** Create a database named `artisthub`.
   ```sql
   CREATE DATABASE artisthub;
   ```
   Credentials: `root` / `root` (Update `src/main/resources/application.properties` if needed).
3. **Run:**
   ```bash
   mvn spring-boot:run
   ```
4. **Access:** [http://localhost:8080/](http://localhost:8080/)

## Features
- **Admin:** Approve artists, manage bookings.
- **Artist:** Create profile, manage bookings.
- **Customer:** Search and book artists.
- **Security:** Hybrid implementation using JWT (API) and Sessions (Web).
- **Frontend:** JSP with Bootstrap.

## Eclipse Setup
1. Install Lombok: [https://projectlombok.org/download](https://projectlombok.org/download).
2. Import as **Existing Maven Project**.
3. Right-click project > Maven > **Update Project**.
4. Run as **Spring Boot App**.

## Default Admin
- **Email:** `admin@artisthub.com`
- **Password:** `admin123`

## Documentation
See `documentation.md` for full implementation details, API usage, and troubleshooting.
