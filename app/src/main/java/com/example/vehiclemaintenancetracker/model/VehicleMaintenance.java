package com.example.vehiclemaintenancetracker.model;

import java.util.Date;

public class VehicleMaintenance {

    private int id;
    private String vehicle;
    private String location;
    private String service;
    private int mileage;
    private String notes;
    private Date dateCreated;

    public VehicleMaintenance(String vehicle, String location, String service, int mileage, String notes, Date dateCreated) {
        this.vehicle = vehicle;
        this.location = location;
        this.service = service;
        this.mileage = mileage;
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

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
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
                ", vehicle=" + vehicle +
                ", location='" + location + '\'' +
                ", service='" + service + '\'' +
                ", mileage=" + mileage +
                ", notes='" + notes + '\'' +
                ", dateCreated=" + dateCreated +
                '}';
    }
}
