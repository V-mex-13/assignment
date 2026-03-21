<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.smilecare.dao.InvoiceDAO, com.smilecare.model.Invoice, java.text.DecimalFormat" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>SmileCare - Invoice Details</title>
    <style>
        /* Printable and clean formatting layout */
        @import url('https://fonts.googleapis.com/css2?family=Roboto+Mono&family=Inter:wght@400;600;700&display=swap');
        body { margin: 0; font-family: 'Inter', sans-serif; background: #f8fafc; display: flex; justify-content: center; padding: 40px 20px; }
        .invoice-card { background: white; padding: 50px; border-radius: 12px; box-shadow: 0 10px 15px -3px rgba(0, 0, 0, 0.1), 0 4px 6px -2px rgba(0, 0, 0, 0.05); width: 100%; max-width: 650px; border: 1px solid #e2e8f0; }
        .header { display: flex; justify-content: space-between; align-items: flex-start; border-bottom: 2px solid #e2e8f0; padding-bottom: 25px; margin-bottom: 30px; }
        .header h1 { margin: 0; color: #0f172a; font-size: 26px; font-weight: 700; letter-spacing: -0.5px; }
        .header p { margin: 5px 0 0 0; color: #64748b; font-size: 14px; line-height: 1.5; }
        .invoice-title { color: #3b82f6; font-size: 20px; font-weight: 700; text-align: right; }
        .invoice-details { margin-bottom: 35px; font-family: 'Roboto Mono', monospace; font-size: 14px; color: #334155; display: flex; justify-content: space-between; }
        table { width: 100%; border-collapse: collapse; margin-bottom: 40px; }
        th, td { padding: 15px; text-align: left; }
        th { background: #f1f5f9; color: #475569; font-weight: 600; font-size: 12px; text-transform: uppercase; letter-spacing: 0.1em; border-bottom: 2px solid #cbd5e1; }
        td { border-bottom: 1px solid #e2e8f0; color: #1e293b; font-size: 15px; }
        .amount { text-align: right; font-family: 'Roboto Mono', monospace; }
        .total-row td { font-weight: 700; font-size: 18px; color: #0f172a; border-bottom: none; border-top: 3px solid #cbd5e1; background: #f8fafc; }
        .total-amount { font-size: 22px !important; color: #3b82f6 !important; }
        .discount { color: #16a34a; }
        button.print-btn { display: block; width: 100%; padding: 15px; background: #0f172a; color: white; border: none; border-radius: 8px; font-weight: 600; cursor: pointer; transition: background 0.3s; margin-top: 20px; font-size: 16px; box-shadow: 0 4px 6px -1px rgba(0,0,0,0.1); }
        button.print-btn:hover { background: #334155; }
        .thank-you { text-align: center; color: #64748b; font-size: 14px; line-height: 1.6; padding-top: 20px; border-top: 1px solid #e2e8f0; }
        @media print {
            body { background: white; padding: 0; }
            .invoice-card { box-shadow: none; border: none; padding: 0; }
            button.print-btn { display: none; }
        }
    </style>
</head>
<body>
    <%
        Invoice inv = null;
        String error = null;
        try {
            int invoiceId = Integer.parseInt(request.getParameter("id"));
            InvoiceDAO dao = new InvoiceDAO();
            inv = dao.getInvoiceById(invoiceId);
            if (inv == null) {
                error = "Invoice record could not be found via that reference ID.";
            }
        } catch (NumberFormatException e) {
            error = "Invalid invoice ID format provided.";
        } catch (Exception e) {
            error = "Data exception: " + e.getMessage();
        }
        
        DecimalFormat df = new DecimalFormat("$#,##0.00");
    %>

    <div class="invoice-card">
        <% if (error != null) { %>
            <div style="color: #dc2626; text-align: center; padding: 40px; background: #fee2e2; border-radius: 8px;">
                <h2 style="margin:0;">Error</h2>
                <p style="margin-top:10px;"><%= error %></p>
            </div>
        <% } else if (inv != null) { %>
            <div class="header">
                <div>
                    <h1>SmileCare Dental</h1>
                    <p>123 Health Ave, Medical District<br>
                    contact@smilecare.com | (555) 123-4567<br>
                    License Number: #MED-88402A</p>
                </div>
                <div class="invoice-title">
                    <div>INVOICE</div>
                    <div style="font-size: 14px; color: #64748b; margin-top: 8px;">#INV-<%= String.format("%05d", inv.getInvoiceId()) %></div>
                </div>
            </div>
            
            <div class="invoice-details">
                <div>
                    <strong>Ref. Appointment:</strong> APT-<%= inv.getAppointmentId() %><br>
                    <strong>Issued By:</strong> Billing Dept
                </div>
                <div style="text-align: right;">
                    <strong>Date:</strong> <%= new java.text.SimpleDateFormat("MMM dd, yyyy").format(new java.util.Date()) %><br>
                    <strong>Status:</strong> DUE
                </div>
            </div>
            
            <table>
                <thead>
                    <tr>
                        <th>Line Item Description</th>
                        <th class="amount">Calculation</th>
                    </tr>
                </thead>
                <tbody>
                    <tr>
                        <td><strong>Core Procedure:</strong> <%= inv.getTreatmentType() %></td>
                        <td class="amount"><%= df.format(inv.getBaseCharge()) %></td>
                    </tr>
                    <tr>
                        <td>Anesthesia, Medications & Clinical Supplies</td>
                        <td class="amount"><%= df.format(inv.getMedicationsCost()) %></td>
                    </tr>
                    <tr>
                        <td>Applicable State & Local Service Taxes</td>
                        <td class="amount"><%= df.format(inv.getTaxes()) %></td>
                    </tr>
                    <tr>
                        <td>Provider Insurance Discount Applied</td>
                        <td class="amount discount">-<%= df.format(inv.getInsuranceDiscount()) %></td>
                    </tr>
                    <tr class="total-row">
                        <td>TOTAL REMITTANCE</td>
                        <td class="amount total-amount"><%= df.format(inv.getTotalAmount()) %></td>
                    </tr>
                </tbody>
            </table>
            
            <div class="thank-you">
                <strong>Thank you for choosing SmileCare Dental Services.</strong><br>
                Please preserve this official invoice copy for tax and medical insurance claim purposes.<br>
                For any billing disputes, contact us within 14 days of the date issued.
            </div>
            
            <button class="print-btn" onclick="window.print()">Print Official Invoice</button>
        <% } %>
    </div>
</body>
</html>
