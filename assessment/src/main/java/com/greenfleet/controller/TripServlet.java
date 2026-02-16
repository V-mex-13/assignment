package com.greenfleet.controller;

import com.greenfleet.dao.DriverDAO;
import com.greenfleet.dao.TripDAO;
import com.greenfleet.dao.VehicleDAO;
import com.greenfleet.model.Trip;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/trips")
public class TripServlet extends HttpServlet {
    private TripDAO tripDAO;
    private VehicleDAO vehicleDAO;
    private DriverDAO driverDAO;

    public void init() {
        tripDAO = new TripDAO();
        vehicleDAO = new VehicleDAO();
        driverDAO = new DriverDAO();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("trips", tripDAO.getAllTrips());
        request.setAttribute("vehicles", vehicleDAO.getAllVehicles());
        request.setAttribute("drivers", driverDAO.getAllDrivers());
        request.getRequestDispatcher("trips.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int vehicleId = Integer.parseInt(request.getParameter("vehicleId"));
        int driverId = Integer.parseInt(request.getParameter("driverId"));
        String startLoc = request.getParameter("startLocation");
        String endLoc = request.getParameter("endLocation");
        String startTimeStr = request.getParameter("startTime"); // Format: yyyy-MM-ddTHH:mm
        
        Timestamp startTime = Timestamp.valueOf(startTimeStr.replace("T", " ") + ":00");
        
        Trip trip = new Trip(0, vehicleId, driverId, startLoc, endLoc, startTime, null, 0.0, "SCHEDULED");
        tripDAO.addTrip(trip);
        
        response.sendRedirect("trips");
    }
}
