package com.greenfleet.model;

public class Vehicle {
    private int vehicleId;
    private String registrationNumber;
    private String model;
    private String type;
    private double capacityTons;
    private String fuelType;
    private String status;

    public Vehicle() {}

    public Vehicle(int vehicleId, String registrationNumber, String model, String type, double capacityTons, String fuelType, String status) {
        this.vehicleId = vehicleId;
        this.registrationNumber = registrationNumber;
        this.model = model;
        this.type = type;
        this.capacityTons = capacityTons;
        this.fuelType = fuelType;
        this.status = status;
    }

    public int getVehicleId() { return vehicleId; }
    public void setVehicleId(int vehicleId) { this.vehicleId = vehicleId; }

    public String getRegistrationNumber() { return registrationNumber; }
    public void setRegistrationNumber(String registrationNumber) { this.registrationNumber = registrationNumber; }

    public String getModel() { return model; }
    public void setModel(String model) { this.model = model; }

    public String getType() { return type; }
    public void setType(String type) { this.type = type; }

    public double getCapacityTons() { return capacityTons; }
    public void setCapacityTons(double capacityTons) { this.capacityTons = capacityTons; }

    public String getFuelType() { return fuelType; }
    public void setFuelType(String fuelType) { this.fuelType = fuelType; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}
