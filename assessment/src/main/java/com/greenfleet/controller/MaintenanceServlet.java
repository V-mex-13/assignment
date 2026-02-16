package com.greenfleet.controller;

import com.greenfleet.dao.MaintenanceLogDAO;
import com.greenfleet.dao.VehicleDAO;
import com.greenfleet.model.MaintenanceLog;
import java.io.IOException;
import java.sql.Date;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/maintenance")
public class MaintenanceServlet extends HttpServlet {
    private MaintenanceLogDAO maintenanceDAO;
    private VehicleDAO vehicleDAO;

    public void init() {
        maintenanceDAO = new MaintenanceLogDAO();
        vehicleDAO = new VehicleDAO();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("logs", maintenanceDAO.getAllLogs());
        request.setAttribute("vehicles", vehicleDAO.getAllVehicles());
        request.getRequestDispatcher("maintenance.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int vehicleId = Integer.parseInt(request.getParameter("vehicleId"));
        String description = request.getParameter("description");
        String logType = request.getParameter("logType");
        double cost = Double.parseDouble(request.getParameter("cost"));
        Date logDate = Date.valueOf(request.getParameter("logDate"));
        int downtime = Integer.parseInt(request.getParameter("downtimeHours"));
        String status = request.getParameter("status");

        MaintenanceLog log = new MaintenanceLog(0, vehicleId, description, logType, cost, logDate, downtime, status);
        maintenanceDAO.addLog(log);
        
        response.sendRedirect("maintenance");
    }
}
