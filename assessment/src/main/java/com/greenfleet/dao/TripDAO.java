package com.greenfleet.dao;

import com.greenfleet.model.Trip;
import com.greenfleet.utils.DBConnection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TripDAO {
    private Connection connection;

    public TripDAO() {
        this.connection = DBConnection.getInstance().getConnection();
    }

    public boolean addTrip(Trip trip) {
        String query = "INSERT INTO trips (vehicle_id, driver_id, start_location, end_location, start_time, end_time, distance_km, status) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setInt(1, trip.getVehicleId());
            pstmt.setInt(2, trip.getDriverId());
            pstmt.setString(3, trip.getStartLocation());
            pstmt.setString(4, trip.getEndLocation());
            pstmt.setTimestamp(5, trip.getStartTime());
            pstmt.setTimestamp(6, trip.getEndTime()); // Can be null
            pstmt.setDouble(7, trip.getDistanceKm());
            pstmt.setString(8, trip.getStatus());
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public List<Trip> getAllTrips() {
        List<Trip> trips = new ArrayList<>();
        String query = "SELECT t.*, v.registration_number, u.full_name as driver_name " +
                       "FROM trips t " +
                       "JOIN vehicles v ON t.vehicle_id = v.vehicle_id " +
                       "JOIN drivers d ON t.driver_id = d.driver_id " +
                       "JOIN users u ON d.user_id = u.user_id " +
                       "ORDER BY t.start_time DESC";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                Trip trip = mapTrip(rs);
                trip.setVehicleReg(rs.getString("registration_number"));
                trip.setDriverName(rs.getString("driver_name"));
                trips.add(trip);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return trips;
    }

    private Trip mapTrip(ResultSet rs) throws SQLException {
        return new Trip(
            rs.getInt("trip_id"),
            rs.getInt("vehicle_id"),
            rs.getInt("driver_id"),
            rs.getString("start_location"),
            rs.getString("end_location"),
            rs.getTimestamp("start_time"),
            rs.getTimestamp("end_time"),
            rs.getDouble("distance_km"),
            rs.getString("status")
        );
    }
}
