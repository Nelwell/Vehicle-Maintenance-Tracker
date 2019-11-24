package com.example.vehiclemaintenancetracker.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.example.vehiclemaintenancetracker.model.VehicleMaintenance;
import com.example.vehiclemaintenancetracker.repository.VehicleRepository;

import java.util.List;

public class MaintenanceViewModel extends AndroidViewModel {

    private VehicleRepository maintenanceRepository;
    private MutableLiveData<List<VehicleMaintenance>> allMaintenanceItems;

    public MaintenanceViewModel (@NonNull Application application) {
        super(application);
        maintenanceRepository = new VehicleRepository();
        allMaintenanceItems = maintenanceRepository.getAllMaintenanceRecords();
    }

    public MutableLiveData<List<VehicleMaintenance>> getAllMaintenanceItems() {
        return allMaintenanceItems;
    }

    public MutableLiveData<VehicleMaintenance> getMovie(int id) {
        return maintenanceRepository.getVehicleMaintenance(id);
    }

    public MutableLiveData<String> insert(VehicleMaintenance movie) {
        return maintenanceRepository.insert(movie);
    }

    public void update(VehicleMaintenance movie) {
        maintenanceRepository.update(movie);
    }

    public void delete(VehicleMaintenance movie) {
        maintenanceRepository.delete(movie);
    }
}
