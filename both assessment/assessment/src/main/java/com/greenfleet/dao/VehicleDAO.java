package com.greenfleet.dao;

import com.greenfleet.model.Vehicle;
import com.greenfleet.utils.DBConnection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class VehicleDAO {
    private Connection connection;

    public VehicleDAO() {
        this.connection = DBConnection.getInstance().getConnection();
    }

    public boolean addVehicle(Vehicle vehicle) {
        String query = "INSERT INTO vehicles (registration_number, model, type, capacity_tons, fuel_type, status) VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setString(1, vehicle.getRegistrationNumber());
            pstmt.setString(2, vehicle.getModel());
            pstmt.setString(3, vehicle.getType());
            pstmt.setDouble(4, vehicle.getCapacityTons());
            pstmt.setString(5, vehicle.getFuelType());
            pstmt.setString(6, vehicle.getStatus());
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public List<Vehicle> getAllVehicles() {
        List<Vehicle> vehicles = new ArrayList<>();
        String query = "SELECT * FROM vehicles";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                vehicles.add(mapVehicle(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return vehicles;
    }
    
    public Vehicle getVehicleById(int id) {
        String query = "SELECT * FROM vehicles WHERE vehicle_id = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) return mapVehicle(rs);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    private Vehicle mapVehicle(ResultSet rs) throws SQLException {
        return new Vehicle(
            rs.getInt("vehicle_id"),
            rs.getString("registration_number"),
            rs.getString("model"),
            rs.getString("type"),
            rs.getDouble("capacity_tons"),
            rs.getString("fuel_type"),
            rs.getString("status")
        );
    }
}
