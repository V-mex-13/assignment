package com.greenfleet.model;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "maintenance_logs")
public class MaintenanceLog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "log_id")
    private int logId;

    @Column(name = "vehicle_id")
    private int vehicleId;

    @Column(name = "description")
    private String description;

    @Column(name = "log_type", nullable = false)
    private String logType; // MAINTENANCE, ACCIDENT, BREAKDOWN

    @Column(name = "cost")
    private double cost;

    @Column(name = "log_date")
    private Date logDate;

    @Column(name = "downtime_hours")
    private int downtimeHours;

    @Column(name = "status")
    private String status;

    // Helper
    @Transient
    private String vehicleReg;

    public MaintenanceLog() {}

    public MaintenanceLog(int logId, int vehicleId, String description, String logType, double cost, Date logDate, int downtimeHours, String status) {
        this.logId = logId;
        this.vehicleId = vehicleId;
        this.description = description;
        this.logType = logType;
        this.cost = cost;
        this.logDate = logDate;
        this.downtimeHours = downtimeHours;
        this.status = status;
    }

    public int getLogId() { return logId; }
    public void setLogId(int logId) { this.logId = logId; }

    public int getVehicleId() { return vehicleId; }
    public void setVehicleId(int vehicleId) { this.vehicleId = vehicleId; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public String getLogType() { return logType; }
    public void setLogType(String logType) { this.logType = logType; }

    public double getCost() { return cost; }
    public void setCost(double cost) { this.cost = cost; }

    public Date getLogDate() { return logDate; }
    public void setLogDate(Date logDate) { this.logDate = logDate; }

    public int getDowntimeHours() { return downtimeHours; }
    public void setDowntimeHours(int downtimeHours) { this.downtimeHours = downtimeHours; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public String getVehicleReg() { return vehicleReg; }
    public void setVehicleReg(String vehicleReg) { this.vehicleReg = vehicleReg; }
}
