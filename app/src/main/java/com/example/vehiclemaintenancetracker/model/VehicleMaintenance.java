package com.example.vehiclemaintenancetracker.model;

import java.util.Date;

public class VehicleMaintenance {

    private int id;
    private String vehicle;
    private String service;
    private int mileage;
    private String location;
    private String notes;
    private Date dateCreated;

    public VehicleMaintenance(String vehicle, String service, int mileage, String location, String notes, Date dateCreated) {
        this.vehicle = vehicle;
        this.service = service;
        this.mileage = mileage;
        this.location = location;
        this.notes = notes;
        this.dateCreated = dateCreated;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getVehicle() {
        return vehicle;
    }

    public void setVehicle(String vehicle) {
        this.vehicle = vehicle;
    }

    public String getService() {
        return service;
    }

    public void setService(String service) {
        this.service = service;
    }

    public int getMileage() {
        return mileage;
    }

    public void setMileage(int mileage) {
        this.mileage = mileage;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    @Override
    public String toString() {
        return "VehicleMaintenance{" +
                "id=" + id +
                ", vehicle='" + vehicle + '\'' +
                ", service='" + service + '\'' +
                ", mileage=" + mileage +
                ", location='" + location + '\'' +
                ", notes='" + notes + '\'' +
                ", dateCreated=" + dateCreated +
                '}';
    }
}
