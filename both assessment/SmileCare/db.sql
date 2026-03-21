CREATE DATABASE IF NOT EXISTS smilecare_db;
USE smilecare_db;

CREATE TABLE IF NOT EXISTS appointments (
    appointment_id INT AUTO_INCREMENT PRIMARY KEY,
    patient_name VARCHAR(255) NOT NULL,
    service_type VARCHAR(100) NOT NULL,
    appointment_date DATETIME NOT NULL,
    estimated_cost DECIMAL(10,2) NOT NULL,
    branch_id INT NOT NULL
);

CREATE TABLE IF NOT EXISTS invoices (
    invoice_id INT AUTO_INCREMENT PRIMARY KEY,
    appointment_id INT NOT NULL,
    treatment_type VARCHAR(255) NOT NULL,
    base_charge DECIMAL(10,2) NOT NULL,
    medications_cost DECIMAL(10,2) NOT NULL,
    taxes DECIMAL(10,2) NOT NULL,
    insurance_discount DECIMAL(10,2) NOT NULL,
    total_amount DECIMAL(10,2) NOT NULL,
    FOREIGN KEY (appointment_id) REFERENCES appointments(appointment_id) ON DELETE CASCADE
);
