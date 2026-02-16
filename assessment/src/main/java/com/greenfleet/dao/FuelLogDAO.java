package com.greenfleet.dao;

import com.greenfleet.model.FuelLog;
import com.greenfleet.utils.DBConnection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FuelLogDAO {
    private Connection connection;

    public FuelLogDAO() {
        this.connection = DBConnection.getInstance().getConnection();
    }

    public boolean addLog(FuelLog log) {
        String query = "INSERT INTO fuel_logs (vehicle_id, driver_id, fillUP_date, amount_litres, cost_per_litre, total_cost, odometer_reading, location) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setInt(1, log.getVehicleId());
            pstmt.setInt(2, log.getDriverId());
            pstmt.setTimestamp(3, log.getFillUpDate());
            pstmt.setDouble(4, log.getAmountLitres());
            pstmt.setDouble(5, log.getCostPerLitre());
            pstmt.setDouble(6, log.getTotalCost());
            pstmt.setDouble(7, log.getOdometerReading());
            pstmt.setString(8, log.getLocation());
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public List<FuelLog> getAllLogs() {
        List<FuelLog> logs = new ArrayList<>();
        String query = "SELECT f.*, v.registration_number, u.full_name as driver_name " +
                       "FROM fuel_logs f " +
                       "JOIN vehicles v ON f.vehicle_id = v.vehicle_id " +
                       "JOIN drivers d ON f.driver_id = d.driver_id " +
                       "JOIN users u ON d.user_id = u.user_id " +
                       "ORDER BY f.fillUP_date DESC";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                FuelLog log = mapLog(rs);
                log.setVehicleReg(rs.getString("registration_number"));
                log.setDriverName(rs.getString("driver_name"));
                logs.add(log);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return logs;
    }

    private FuelLog mapLog(ResultSet rs) throws SQLException {
        return new FuelLog(
            rs.getInt("log_id"),
            rs.getInt("vehicle_id"),
            rs.getInt("driver_id"),
            rs.getTimestamp("fillUP_date"),
            rs.getDouble("amount_litres"),
            rs.getDouble("cost_per_litre"),
            rs.getDouble("total_cost"),
            rs.getDouble("odometer_reading"),
            rs.getString("location")
        );
    }
}
