package com.example.vehiclemaintenancetracker.service;

import com.example.vehiclemaintenancetracker.model.VehicleMaintenance;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface VehicleService {

    // Get all maintenance records
    @GET("VehicleRecords/")
    Call<List<VehicleMaintenance>> getAllMaintenanceRecords();


    // Example - get maintenance record by ID
    @GET("VehicleRecords/{id}/")
    Call<VehicleMaintenance> get(@Path("id") int id);


    // Insert maintenance record
    @POST("VehicleRecords/")
    Call<Void> insert(@Body VehicleMaintenance vehicle);


    // Update maintenance record
    @PATCH("VehicleRecords/{id}/")
    Call<Void> update(@Body VehicleMaintenance vehicle, @Path("id") int id);


    // Delete maintenance record
    @DELETE("VehicleRecords/{id}/")
    Call<Void> delete(@Path("id") int id);

}
