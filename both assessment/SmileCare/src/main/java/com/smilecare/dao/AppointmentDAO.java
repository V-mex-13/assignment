package com.smilecare.dao;

import com.smilecare.model.Appointment;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

public class AppointmentDAO {

    // Input validation & verification logic (preventing double booking for identical exact time)
    public boolean isTimeSlotAvailable(Timestamp appointmentDate, int branchId) throws Exception {
        String query = "SELECT COUNT(*) FROM appointments WHERE appointment_date = ? AND branch_id = ?";
        // Using try-with-resources for resource cleanup robustness
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            
            stmt.setTimestamp(1, appointmentDate);
            stmt.setInt(2, branchId);
            
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1) == 0;
                }
            }
        } catch (SQLException e) {
            // Strong exception handling strategy revealing the exact root cause
            throw new Exception("Database error while checking sequence/time slot availability: " + e.getMessage(), e);
        }
        return false;
    }

    public boolean bookAppointment(Appointment app) throws Exception {
        // Business logic validation
        if (!isTimeSlotAvailable(app.getAppointmentDate(), app.getBranchId())) {
            throw new Exception("This time slot is already booked for this branch.");
        }

        // Using PreparedStatement to prevent SQL injection vulnerabilities
        String query = "INSERT INTO appointments (patient_name, service_type, appointment_date, estimated_cost, branch_id) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS)) {
            
            stmt.setString(1, app.getPatientName());
            stmt.setString(2, app.getServiceType());
            stmt.setTimestamp(3, app.getAppointmentDate());
            stmt.setDouble(4, app.getEstimatedCost());
            stmt.setInt(5, app.getBranchId());

            int rowsAffected = stmt.executeUpdate();
            
            if (rowsAffected > 0) {
                try (ResultSet rs = stmt.getGeneratedKeys()) {
                    if (rs.next()) {
                        app.setAppointmentId(rs.getInt(1));
                    }
                }
            }
            
            /*
             * BONUS/SCALABILITY: Follow-up Alerts Architecture
             * In a fully scaled implementation, right after a successful booking
             * (rowsAffected > 0), you could queue a JMS event (ActiveMQ) or
             * push a record to a "NotificationTasks" table. A scheduled Spring Batch
             * chronjob or cron background worker can poll that table and SMS/Email
             * 24-hours before the 'appointmentDate'.
             */
             
            return rowsAffected > 0;
            
        } catch (SQLException e) {
            throw new Exception("Error saving the appointment to the database: " + e.getMessage(), e);
        }
    }
}
