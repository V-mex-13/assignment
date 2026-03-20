package com.smilecare.model;

public class Invoice {
    private int invoiceId;
    private int appointmentId;
    private String treatmentType;
    private double baseCharge;
    private double medicationsCost;
    private double taxes;
    private double insuranceDiscount;
    private double totalAmount;

    public Invoice() {}

    public int getInvoiceId() { return invoiceId; }
    public void setInvoiceId(int invoiceId) { this.invoiceId = invoiceId; }

    public int getAppointmentId() { return appointmentId; }
    public void setAppointmentId(int appointmentId) { this.appointmentId = appointmentId; }

    public String getTreatmentType() { return treatmentType; }
    public void setTreatmentType(String treatmentType) { this.treatmentType = treatmentType; }

    public double getBaseCharge() { return baseCharge; }
    public void setBaseCharge(double baseCharge) { this.baseCharge = baseCharge; }

    public double getMedicationsCost() { return medicationsCost; }
    public void setMedicationsCost(double medicationsCost) { this.medicationsCost = medicationsCost; }

    public double getTaxes() { return taxes; }
    public void setTaxes(double taxes) { this.taxes = taxes; }

    public double getInsuranceDiscount() { return insuranceDiscount; }
    public void setInsuranceDiscount(double insuranceDiscount) { this.insuranceDiscount = insuranceDiscount; }

    public double getTotalAmount() { return totalAmount; }
    public void setTotalAmount(double totalAmount) { this.totalAmount = totalAmount; }

    // Domain logic encapsulated within the model
    public void calculateTotal() {
        this.totalAmount = (this.baseCharge + this.medicationsCost + this.taxes) - this.insuranceDiscount;
    }
}
