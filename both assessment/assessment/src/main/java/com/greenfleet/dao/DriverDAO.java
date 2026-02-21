package com.greenfleet.dao;

import com.greenfleet.model.Driver;
import com.greenfleet.utils.DBConnection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DriverDAO {
    private Connection connection;

    public DriverDAO() {
        this.connection = DBConnection.getInstance().getConnection();
    }

    public boolean addDriver(Driver driver) {
        String query = "INSERT INTO drivers (user_id, license_number, license_expiry, phone) VALUES (?, ?, ?, ?)";
        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setInt(1, driver.getUserId());
            pstmt.setString(2, driver.getLicenseNumber());
            pstmt.setDate(3, driver.getLicenseExpiry());
            pstmt.setString(4, driver.getPhone());
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public List<Driver> getAllDrivers() {
        List<Driver> drivers = new ArrayList<>();
        String query = "SELECT d.*, u.full_name FROM drivers d JOIN users u ON d.user_id = u.user_id";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                Driver driver = mapDriver(rs);
                driver.setDriverName(rs.getString("full_name"));
                drivers.add(driver);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return drivers;
    }
    
    public Driver getDriverById(int id) {
        String query = "SELECT d.*, u.full_name FROM drivers d JOIN users u ON d.user_id = u.user_id WHERE driver_id = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                Driver driver = mapDriver(rs);
                driver.setDriverName(rs.getString("full_name"));
                return driver;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    private Driver mapDriver(ResultSet rs) throws SQLException {
        return new Driver(
            rs.getInt("driver_id"),
            rs.getInt("user_id"),
            rs.getString("license_number"),
            rs.getDate("license_expiry"),
            rs.getString("phone")
        );
    }
}
