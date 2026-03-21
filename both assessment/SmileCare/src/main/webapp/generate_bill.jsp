<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.smilecare.dao.InvoiceDAO, com.smilecare.model.Invoice" %>
<%
    String errorMsg = "";
    if ("POST".equalsIgnoreCase(request.getMethod())) {
        try {
            int appointmentId = Integer.parseInt(request.getParameter("appointmentId"));
            String treatmentType = request.getParameter("treatmentType");
            double baseCharge = Double.parseDouble(request.getParameter("baseCharge"));
            double medicationsCost = Double.parseDouble(request.getParameter("medicationsCost"));
            double taxesStr = Double.parseDouble(request.getParameter("taxes"));
            double insuranceDiscountStr = Double.parseDouble(request.getParameter("insuranceDiscount"));
            
            // Utilize OOP: model encapsulates arithmetic algorithms 
            Invoice inv = new Invoice();
            inv.setAppointmentId(appointmentId);
            inv.setTreatmentType(treatmentType);
            inv.setBaseCharge(baseCharge);
            inv.setMedicationsCost(medicationsCost);
            inv.setTaxes(taxesStr);
            inv.setInsuranceDiscount(insuranceDiscountStr);
            inv.calculateTotal(); 
            
            InvoiceDAO dao = new InvoiceDAO();
            if (dao.saveInvoice(inv)) {
                // Move to export to PDF/display flow automatically
                response.sendRedirect("invoice_view.jsp?id=" + inv.getInvoiceId());
                return;
            } else {
                 errorMsg = "Database error, unable to complete invoice save.";
            }
        } catch (NumberFormatException e) {
            errorMsg = "Input error: Please enter valid numbers for charge fields.";
        } catch (Exception e) {
            errorMsg = "System fault: " + e.getMessage();
        }
    }
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>SmileCare - Generate Patient Bill</title>
    <!-- Dark Mode Form Design For Billing Department -->
    <style>
        @import url('https://fonts.googleapis.com/css2?family=Inter:wght@400;600&display=swap');
        body { margin: 0; font-family: 'Inter', sans-serif; background: linear-gradient(135deg, #1e293b, #0f172a); display: flex; justify-content: center; align-items: center; min-height: 100vh; color: #f1f5f9; }
        .form-container { background: rgba(30, 41, 59, 0.85); backdrop-filter: blur(12px); border: 1px solid rgba(255,255,255,0.1); padding: 40px; border-radius: 16px; box-shadow: 0 10px 40px rgba(0,0,0,0.5); width: 100%; max-width: 500px; }
        h2 { margin-top: 0; text-align: center; font-weight: 600; color: #38bdf8; margin-bottom: 25px;}
        .form-row { display: flex; gap: 15px; }
        .form-group { margin-bottom: 20px; flex: 1; }
        label { display: block; margin-bottom: 8px; color: #94a3b8; font-size: 0.9em; font-weight: 600; }
        input, select { background: rgba(15, 23, 42, 0.7); color: #f8fafc; border: 1px solid #475569; width: 100%; padding: 12px; border-radius: 8px; box-sizing: border-box; font-family: 'Inter', sans-serif; transition: border-color 0.3s; }
        input:focus, select:focus { outline: none; border-color: #38bdf8; }
        button { width: 100%; padding: 14px; background: #38bdf8; color: #0f172a; border: none; border-radius: 8px; font-weight: 600; font-size: 1em; cursor: pointer; transition: background 0.3s, transform 0.1s; margin-top: 10px; }
        button:hover { background: #0ea5e9; }
        button:active { transform: scale(0.98); }
        .error { color: #fca5a5; background: rgba(220, 38, 38, 0.2); border: 1px solid #f87171; padding: 12px; border-radius: 6px; margin-bottom: 20px; text-align: center; font-size: 0.9em; }
    </style>
</head>
<body>
    <div class="form-container">
        <h2>Generate Bill</h2>

        
        <% if (!errorMsg.isEmpty()) { %>
            <div class="error"><%= errorMsg %></div>
        <% } %>

        <form method="POST" action="generate_bill.jsp">
            <div class="form-group">
                <label for="appointmentId">Appointment ID</label>
                <input type="number" id="appointmentId" name="appointmentId" value="<%= request.getParameter("appointmentId") != null ? request.getParameter("appointmentId") : "" %>" required placeholder="e.g. 1024">
            </div>
            <div class="form-group">
                <label for="treatmentType">Treatment Performed</label>
                <input type="text" id="treatmentType" name="treatmentType" required placeholder="Root Canal">
            </div>
            <div class="form-row">
                <div class="form-group">
                    <label for="baseCharge">Base Charge ($)</label>
                    <input type="number" step="0.01" id="baseCharge" name="baseCharge" required>
                </div>
                <div class="form-group">
                    <label for="medicationsCost">Medications ($)</label>
                    <input type="number" step="0.01" id="medicationsCost" name="medicationsCost" value="0.00" required>
                </div>
            </div>
            <div class="form-row">
                <div class="form-group">
                    <label for="taxes">Taxes ($)</label>
                    <input type="number" step="0.01" id="taxes" name="taxes" value="0.00" required>
                </div>
                <div class="form-group">
                    <label for="insuranceDiscount">Insurance Discount ($)</label>
                    <input type="number" step="0.01" id="insuranceDiscount" name="insuranceDiscount" value="0.00" required>
                </div>
            </div>
            <button type="submit">Calculate Total & Invoice</button>
        </form>
    </div>
</body>
</html>
