package com.greenfleet.dao;

import com.greenfleet.model.MaintenanceLog;
import com.greenfleet.utils.DBConnection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MaintenanceLogDAO {
    private Connection connection;

    public MaintenanceLogDAO() {
        this.connection = DBConnection.getInstance().getConnection();
    }

    public boolean addLog(MaintenanceLog log) {
        String query = "INSERT INTO maintenance_logs (vehicle_id, description, log_type, cost, log_date, downtime_hours, status) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setInt(1, log.getVehicleId());
            pstmt.setString(2, log.getDescription());
            pstmt.setString(3, log.getLogType());
            pstmt.setDouble(4, log.getCost());
            pstmt.setDate(5, log.getLogDate());
            pstmt.setInt(6, log.getDowntimeHours());
            pstmt.setString(7, log.getStatus());
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public List<MaintenanceLog> getAllLogs() {
        List<MaintenanceLog> logs = new ArrayList<>();
        String query = "SELECT m.*, v.registration_number " +
                       "FROM maintenance_logs m " +
                       "JOIN vehicles v ON m.vehicle_id = v.vehicle_id " +
                       "ORDER BY m.log_date DESC";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                MaintenanceLog log = mapLog(rs);
                log.setVehicleReg(rs.getString("registration_number"));
                logs.add(log);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return logs;
    }

    private MaintenanceLog mapLog(ResultSet rs) throws SQLException {
        return new MaintenanceLog(
            rs.getInt("log_id"),
            rs.getInt("vehicle_id"),
            rs.getString("description"),
            rs.getString("log_type"),
            rs.getDouble("cost"),
            rs.getDate("log_date"),
            rs.getInt("downtime_hours"),
            rs.getString("status")
        );
    }
}
