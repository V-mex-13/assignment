CREATE DATABASE IF NOT EXISTS greenfleet_db;
USE greenfleet_db;

-- Users (Admin, Driver, Maintenance)
CREATE TABLE IF NOT EXISTS users (
    user_id INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(50) UNIQUE NOT NULL,
    password VARCHAR(255) NOT NULL, -- In real app, store hashed password
    role ENUM('ADMIN', 'DRIVER', 'MAINTENANCE') NOT NULL,
    full_name VARCHAR(100),
    email VARCHAR(100),
    status ENUM('ACTIVE', 'INACTIVE') DEFAULT 'ACTIVE',
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Vehicles
CREATE TABLE IF NOT EXISTS vehicles (
    vehicle_id INT AUTO_INCREMENT PRIMARY KEY,
    registration_number VARCHAR(20) UNIQUE NOT NULL,
    model VARCHAR(50),
    type VARCHAR(50), -- Truck, Van, etc.
    capacity_tons DOUBLE,
    fuel_type VARCHAR(20),
    status ENUM('AVAILABLE', 'ON_TRIP', 'MAINTENANCE', 'RETIRED') DEFAULT 'AVAILABLE',
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Drivers (More specific details linked to users)
CREATE TABLE IF NOT EXISTS drivers (
    driver_id INT AUTO_INCREMENT PRIMARY KEY,
    user_id INT UNIQUE,
    license_number VARCHAR(50) UNIQUE NOT NULL,
    license_expiry DATE NOT NULL,
    phone VARCHAR(20),
    FOREIGN KEY (user_id) REFERENCES users(user_id) ON DELETE CASCADE
);

-- Vehicle Assignments
CREATE TABLE IF NOT EXISTS vehicle_assignments (
    assignment_id INT AUTO_INCREMENT PRIMARY KEY,
    vehicle_id INT,
    driver_id INT,
    assigned_date DATE,
    status ENUM('ACTIVE', 'COMPLETED') DEFAULT 'ACTIVE',
    FOREIGN KEY (vehicle_id) REFERENCES vehicles(vehicle_id),
    FOREIGN KEY (driver_id) REFERENCES drivers(driver_id)
);

-- Trips
CREATE TABLE IF NOT EXISTS trips (
    trip_id INT AUTO_INCREMENT PRIMARY KEY,
    vehicle_id INT,
    driver_id INT,
    start_location VARCHAR(255) NOT NULL,
    end_location VARCHAR(255) NOT NULL,
    start_time DATETIME,
    end_time DATETIME,
    distance_km DOUBLE,
    status ENUM('SCHEDULED', 'IN_PROGRESS', 'COMPLETED', 'CANCELLED') DEFAULT 'SCHEDULED',
    FOREIGN KEY (vehicle_id) REFERENCES vehicles(vehicle_id),
    FOREIGN KEY (driver_id) REFERENCES drivers(driver_id)
);

-- Fuel Logs
CREATE TABLE IF NOT EXISTS fuel_logs (
    log_id INT AUTO_INCREMENT PRIMARY KEY,
    vehicle_id INT,
    driver_id INT,
    fillUP_date DATETIME DEFAULT CURRENT_TIMESTAMP,
    amount_litres DOUBLE NOT NULL,
    cost_per_litre DOUBLE NOT NULL,
    total_cost DOUBLE NOT NULL,
    odometer_reading DOUBLE NOT NULL,
    location VARCHAR(255),
    FOREIGN KEY (vehicle_id) REFERENCES vehicles(vehicle_id),
    FOREIGN KEY (driver_id) REFERENCES drivers(driver_id)
);

-- Maintenance/Incidents
CREATE TABLE IF NOT EXISTS maintenance_logs (
    log_id INT AUTO_INCREMENT PRIMARY KEY,
    vehicle_id INT,
    description TEXT,
    log_type ENUM('MAINTENANCE', 'ACCIDENT', 'BREAKDOWN') NOT NULL,
    cost DOUBLE,
    log_date DATE,
    downtime_hours INT,
    status ENUM('PENDING', 'COMPLETED') DEFAULT 'PENDING',
    FOREIGN KEY (vehicle_id) REFERENCES vehicles(vehicle_id)
);

-- Insert Default Admin
INSERT INTO users (username, password, role, full_name, email) 
VALUES ('admin', 'admin123', 'ADMIN', 'System Admin', 'admin@greenfleet.com')
ON DUPLICATE KEY UPDATE username=username;
