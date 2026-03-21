<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.smilecare.dao.AppointmentDAO, com.smilecare.model.Appointment, java.sql.Timestamp" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>SmileCare - Book Appointment</title>
    <!-- Modern web app design - Inter Font and clean aesthetics -->
    <style>
        @import url('https://fonts.googleapis.com/css2?family=Inter:wght@400;600&display=swap');
        body { margin: 0; font-family: 'Inter', sans-serif; background: linear-gradient(135deg, #f3f4f6, #e5e7eb); display: flex; justify-content: center; align-items: center; min-height: 100vh; }
        .form-container { background: rgba(255, 255, 255, 0.95); backdrop-filter: blur(10px); padding: 40px; border-radius: 16px; box-shadow: 0 10px 30px rgba(0,0,0,0.1); width: 100%; max-width: 450px; }
        h2 { margin-top: 0; color: #1f2937; text-align: center; font-weight: 600; margin-bottom: 25px; }
        .form-group { margin-bottom: 20px; }
        label { display: block; margin-bottom: 8px; color: #4b5563; font-size: 0.9em; font-weight: 600; }
        input, select { width: 100%; padding: 12px; border: 1px solid #d1d5db; border-radius: 8px; box-sizing: border-box; font-family: 'Inter', sans-serif; transition: border-color 0.3s; background: #f9fafb; }
        input:focus, select:focus { outline: none; border-color: #3b82f6; box-shadow: 0 0 0 3px rgba(59, 130, 246, 0.2); background: #ffffff; }
        button { width: 100%; padding: 14px; background: #3b82f6; color: white; border: none; border-radius: 8px; font-weight: 600; font-size: 1em; cursor: pointer; transition: background 0.3s, transform 0.1s; margin-top: 10px; }
        button:hover { background: #2563eb; }
        button:active { transform: scale(0.98); }
        .message.error { color: #dc2626; background: #fee2e2; padding: 12px; border-radius: 6px; margin-bottom: 20px; text-align: center; font-size: 0.9em; }
        .message.success { color: #16a34a; background: #dcfce7; padding: 12px; border-radius: 6px; margin-bottom: 20px; text-align: center; font-size: 0.9em; }
    </style>
</head>
<body>
    <div class="form-container">
        <h2>Book Appointment</h2>
        <%
            String msg = "";
            String msgType = "";
            
            if ("POST".equalsIgnoreCase(request.getMethod())) {
                try {
                    String patientName = request.getParameter("patientName");
                    String serviceType = request.getParameter("serviceType");
                    String appointmentDateStr = request.getParameter("appointmentDate");
                    String estimatedCostStr = request.getParameter("estimatedCost");
                    
                    if (patientName == null || patientName.trim().isEmpty() || serviceType == null || appointmentDateStr == null) {
                        throw new IllegalArgumentException("All fields are required.");
                    }
                    
                    Appointment app = new Appointment();
                    app.setPatientName(patientName);
                    app.setServiceType(serviceType);
                    // Standardize string formatted date from form to Timestamp (adds :00 seconds)
                    app.setAppointmentDate(Timestamp.valueOf(appointmentDateStr.replace("T", " ") + ":00"));
                    app.setEstimatedCost(Double.parseDouble(estimatedCostStr));
                    app.setBranchId(1); // Explicit branch allocation allows enterprise-level scalability
                    
                    AppointmentDAO dao = new AppointmentDAO();
                    boolean success = dao.bookAppointment(app);
                    
                    if (success) {
                        msg = "Appointment successfully booked by reception! <br><a href='generate_bill.jsp?appointmentId=" + app.getAppointmentId() + "' style='display:inline-block; margin-top:10px; padding:8px 15px; background:#15803d; color:white; text-decoration:none; border-radius:6px; font-weight:600;'>Generate Bill for APT-" + app.getAppointmentId() + "</a>";
                        msgType = "success";
                    }
                } catch (Exception e) {
                    msg = "Error: " + e.getMessage();
                    msgType = "error";
                }
            }
        %>
        
        <% if (!msg.isEmpty()) { %>
            <div class="message <%= msgType %>"><%= msg %></div>
        <% } %>

        <form method="POST" action="book_appointment.jsp">
            <div class="form-group">
                <label for="patientName">Patient Name</label>
                <input type="text" id="patientName" name="patientName" required placeholder="John Doe">
            </div>
            <div class="form-group">
                <label for="serviceType">Service Type</label>
                <select id="serviceType" name="serviceType" required>
                    <option value="">Select Service...</option>
                    <option value="Cleaning">Teeth Cleaning</option>
                    <option value="Filling">Cavity Filling</option>
                    <option value="Root Canal">Root Canal</option>
                    <option value="Whitening">Teeth Whitening</option>
                </select>
            </div>
            <div class="form-group">
                <label for="appointmentDate">Preferred Date & Time</label>
                <input type="datetime-local" id="appointmentDate" name="appointmentDate" required>
            </div>
            <div class="form-group">
                <label for="estimatedCost">Estimated Cost ($)</label>
                <input type="number" step="0.01" id="estimatedCost" name="estimatedCost" required placeholder="150.00">
            </div>
            <button type="submit">Book Slot</button>
        </form>
    </div>
</body>
</html>
