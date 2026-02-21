package com.greenfleet.controller;

import com.greenfleet.dao.VehicleDAO;
import com.greenfleet.model.Vehicle;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/vehicles")
public class VehicleServlet extends HttpServlet {
    private VehicleDAO vehicleDAO;

    public void init() {
        vehicleDAO = new VehicleDAO();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        
        // Simple delete optional logic could go here or separate method
        
        List<Vehicle> vehicleList = vehicleDAO.getAllVehicles();
        request.setAttribute("vehicles", vehicleList);
        request.getRequestDispatcher("vehicles.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String reg = request.getParameter("registrationNumber");
        String model = request.getParameter("model");
        String type = request.getParameter("type");
        double capacity = Double.parseDouble(request.getParameter("capacityTons"));
        String fuelType = request.getParameter("fuelType");
        
        Vehicle vehicle = new Vehicle(0, reg, model, type, capacity, fuelType, "AVAILABLE");
        vehicleDAO.addVehicle(vehicle);
        
        response.sendRedirect("vehicles");
    }
}
