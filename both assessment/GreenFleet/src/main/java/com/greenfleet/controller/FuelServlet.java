package com.greenfleet.controller;

import com.greenfleet.dao.DriverDAO;
import com.greenfleet.dao.FuelLogDAO;
import com.greenfleet.dao.VehicleDAO;
import com.greenfleet.model.FuelLog;
import java.io.IOException;
import java.sql.Timestamp;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/fuel")
public class FuelServlet extends HttpServlet {
    private FuelLogDAO fuelLogDAO;
    private VehicleDAO vehicleDAO;
    private DriverDAO driverDAO;

    public void init() {
        fuelLogDAO = new FuelLogDAO();
        vehicleDAO = new VehicleDAO();
        driverDAO = new DriverDAO();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if ("export".equals(action)) {
            exportToCSV(response);
            return;
        }
        
        request.setAttribute("logs", fuelLogDAO.getAllLogs());
        request.setAttribute("vehicles", vehicleDAO.getAllVehicles());
        request.setAttribute("drivers", driverDAO.getAllDrivers());
        request.getRequestDispatcher("fuel.jsp").forward(request, response);
    }

    private void exportToCSV(HttpServletResponse response) throws IOException {
        response.setContentType("text/csv");
        response.setHeader("Content-Disposition", "attachment; filename=\"fuel_logs.csv\"");
        
        StringBuilder sb = new StringBuilder();
        sb.append("LogID,Date,Vehicle,Driver,Litres,Cost Per Litre,Total Cost,Odometer,Location\n");
        
        for (FuelLog log : fuelLogDAO.getAllLogs()) {
            sb.append(log.getLogId()).append(",");
            sb.append(log.getFillUpDate()).append(",");
            sb.append(log.getVehicleReg()).append(",");
            sb.append(log.getDriverName()).append(",");
            sb.append(log.getAmountLitres()).append(",");
            sb.append(log.getCostPerLitre()).append(",");
            sb.append(log.getTotalCost()).append(",");
            sb.append(log.getOdometerReading()).append(",");
            sb.append(log.getLocation() != null ? log.getLocation().replace(",", " ") : "").append("\n");
        }
        
        response.getOutputStream().write(sb.toString().getBytes());
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int vehicleId = Integer.parseInt(request.getParameter("vehicleId"));
        int driverId = Integer.parseInt(request.getParameter("driverId"));
        double amount = Double.parseDouble(request.getParameter("amountLitres"));
        double costPer = Double.parseDouble(request.getParameter("costPerLitre"));
        double totalCost = amount * costPer;
        double odometer = Double.parseDouble(request.getParameter("odometerReading"));
        String location = request.getParameter("location");
        String dateStr = request.getParameter("fillUpDate");
        
        Timestamp fillDate = Timestamp.valueOf(dateStr.replace("T", " ") + ":00");
        
        FuelLog log = new FuelLog(0, vehicleId, driverId, fillDate, amount, costPer, totalCost, odometer, location);
        fuelLogDAO.addLog(log);
        
        response.sendRedirect("fuel");
    }
}
