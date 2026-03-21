package com.smilecare.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    // Modify these parameters directly to match the live server or environment configuration
    private static final String URL = "jdbc:mysql://localhost:3306/smilecare_db?createDatabaseIfNotExist=true";
    private static final String USER = "root";
    private static final String PASS = ""; 

    static {
        try {
            // Explicitly load class for compatibility with standard JSP/Servlet configurations
            Class.forName("com.mysql.cj.jdbc.Driver");
            
            // Auto-create necessary tables if they don't exist
            try (Connection conn = DriverManager.getConnection(URL, USER, PASS);
                 java.sql.Statement stmt = conn.createStatement()) {
                 
                stmt.execute("CREATE TABLE IF NOT EXISTS appointments (" +
                    "appointment_id INT AUTO_INCREMENT PRIMARY KEY, " +
                    "patient_name VARCHAR(255) NOT NULL, " +
                    "service_type VARCHAR(100) NOT NULL, " +
                    "appointment_date DATETIME NOT NULL, " +
                    "estimated_cost DECIMAL(10,2) NOT NULL, " +
                    "branch_id INT NOT NULL)");
                    
                stmt.execute("CREATE TABLE IF NOT EXISTS invoices (" +
                    "invoice_id INT AUTO_INCREMENT PRIMARY KEY, " +
                    "appointment_id INT NOT NULL, " +
                    "treatment_type VARCHAR(255) NOT NULL, " +
                    "base_charge DECIMAL(10,2) NOT NULL, " +
                    "medications_cost DECIMAL(10,2) NOT NULL, " +
                    "taxes DECIMAL(10,2) NOT NULL, " +
                    "insurance_discount DECIMAL(10,2) NOT NULL, " +
                    "total_amount DECIMAL(10,2) NOT NULL, " +
                    "FOREIGN KEY (appointment_id) REFERENCES appointments(appointment_id) ON DELETE CASCADE)");
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Fatal Error: Could not auto-initialize the SmileCare databases/tables.");
        }
    }

    public static Connection getConnection() throws SQLException, ClassNotFoundException {
        return DriverManager.getConnection(URL, USER, PASS);
    }
}
