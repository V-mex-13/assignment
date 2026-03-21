package com.smilecare.dao;

import com.smilecare.model.Invoice;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class InvoiceDAO {
    
    public boolean saveInvoice(Invoice invoice) throws Exception {
        String query = "INSERT INTO invoices (appointment_id, treatment_type, base_charge, medications_cost, taxes, insurance_discount, total_amount) VALUES (?, ?, ?, ?, ?, ?, ?)";
        
        // Return generated key to display instantly on the view page
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS)) {
             
            stmt.setInt(1, invoice.getAppointmentId());
            stmt.setString(2, invoice.getTreatmentType());
            stmt.setDouble(3, invoice.getBaseCharge());
            stmt.setDouble(4, invoice.getMedicationsCost());
            stmt.setDouble(5, invoice.getTaxes());
            stmt.setDouble(6, invoice.getInsuranceDiscount());
            stmt.setDouble(7, invoice.getTotalAmount());
            
            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected > 0) {
                try (ResultSet rs = stmt.getGeneratedKeys()) {
                    if(rs.next()) {
                        invoice.setInvoiceId(rs.getInt(1));
                    }
                }
                return true;
            }
            return false;
            
        } catch (SQLException e) {
            throw new Exception("Error persisting invoice.", e);
        }
    }
    
    public Invoice getInvoiceById(int id) throws Exception {
        String query = "SELECT * FROM invoices WHERE invoice_id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
             
            stmt.setInt(1, id);
            
            try (ResultSet rs = stmt.executeQuery()) {
                if(rs.next()) {
                    Invoice inv = new Invoice();
                    inv.setInvoiceId(rs.getInt("invoice_id"));
                    inv.setAppointmentId(rs.getInt("appointment_id"));
                    inv.setTreatmentType(rs.getString("treatment_type"));
                    inv.setBaseCharge(rs.getDouble("base_charge"));
                    inv.setMedicationsCost(rs.getDouble("medications_cost"));
                    inv.setTaxes(rs.getDouble("taxes"));
                    inv.setInsuranceDiscount(rs.getDouble("insurance_discount"));
                    inv.setTotalAmount(rs.getDouble("total_amount"));
                    return inv;
                }
            }
        } catch(SQLException e) {
            throw new Exception("Database lookup failed for invoice.", e);
        }
        return null; // Not found
    }
}
