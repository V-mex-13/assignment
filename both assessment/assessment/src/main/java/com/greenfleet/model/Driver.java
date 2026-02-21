package com.greenfleet.model;

import java.sql.Date;

public class Driver {
    private int driverId;
    private int userId;
    private String licenseNumber;
    private Date licenseExpiry;
    private String phone;
    
    // Helper fields for display
    private String driverName;

    public Driver() {}

    public Driver(int driverId, int userId, String licenseNumber, Date licenseExpiry, String phone) {
        this.driverId = driverId;
        this.userId = userId;
        this.licenseNumber = licenseNumber;
        this.licenseExpiry = licenseExpiry;
        this.phone = phone;
    }

    public int getDriverId() { return driverId; }
    public void setDriverId(int driverId) { this.driverId = driverId; }

    public int getUserId() { return userId; }
    public void setUserId(int userId) { this.userId = userId; }

    public String getLicenseNumber() { return licenseNumber; }
    public void setLicenseNumber(String licenseNumber) { this.licenseNumber = licenseNumber; }

    public Date getLicenseExpiry() { return licenseExpiry; }
    public void setLicenseExpiry(Date licenseExpiry) { this.licenseExpiry = licenseExpiry; }

    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }

    public String getDriverName() { return driverName; }
    public void setDriverName(String driverName) { this.driverName = driverName; }
}
