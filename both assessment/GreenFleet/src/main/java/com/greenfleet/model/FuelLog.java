package com.greenfleet.model;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "fuel_logs")
public class FuelLog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "log_id")
    private int logId;

    @Column(name = "vehicle_id")
    private int vehicleId;

    @Column(name = "driver_id")
    private int driverId;

    @Column(name = "fillUP_date")
    private Timestamp fillUpDate;

    @Column(name = "amount_litres", nullable = false)
    private double amountLitres;

    @Column(name = "cost_per_litre", nullable = false)
    private double costPerLitre;

    @Column(name = "total_cost", nullable = false)
    private double totalCost;

    @Column(name = "odometer_reading", nullable = false)
    private double odometerReading;

    @Column(name = "location")
    private String location;

    // Helper fields
    @Transient
    private String vehicleReg;
    @Transient
    private String driverName;

    public FuelLog() {}

    public FuelLog(int logId, int vehicleId, int driverId, Timestamp fillUpDate, double amountLitres, double costPerLitre, double totalCost, double odometerReading, String location) {
        this.logId = logId;
        this.vehicleId = vehicleId;
        this.driverId = driverId;
        this.fillUpDate = fillUpDate;
        this.amountLitres = amountLitres;
        this.costPerLitre = costPerLitre;
        this.totalCost = totalCost;
        this.odometerReading = odometerReading;
        this.location = location;
    }

    public int getLogId() { return logId; }
    public void setLogId(int logId) { this.logId = logId; }

    public int getVehicleId() { return vehicleId; }
    public void setVehicleId(int vehicleId) { this.vehicleId = vehicleId; }

    public int getDriverId() { return driverId; }
    public void setDriverId(int driverId) { this.driverId = driverId; }

    public Timestamp getFillUpDate() { return fillUpDate; }
    public void setFillUpDate(Timestamp fillUpDate) { this.fillUpDate = fillUpDate; }

    public double getAmountLitres() { return amountLitres; }
    public void setAmountLitres(double amountLitres) { this.amountLitres = amountLitres; }

    public double getCostPerLitre() { return costPerLitre; }
    public void setCostPerLitre(double costPerLitre) { this.costPerLitre = costPerLitre; }

    public double getTotalCost() { return totalCost; }
    public void setTotalCost(double totalCost) { this.totalCost = totalCost; }

    public double getOdometerReading() { return odometerReading; }
    public void setOdometerReading(double odometerReading) { this.odometerReading = odometerReading; }

    public String getLocation() { return location; }
    public void setLocation(String location) { this.location = location; }

    public String getVehicleReg() { return vehicleReg; }
    public void setVehicleReg(String vehicleReg) { this.vehicleReg = vehicleReg; }

    public String getDriverName() { return driverName; }
    public void setDriverName(String driverName) { this.driverName = driverName; }
}
