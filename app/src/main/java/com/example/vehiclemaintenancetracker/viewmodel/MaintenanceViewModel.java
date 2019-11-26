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

    public MutableLiveData<List<VehicleMaintenance>> getAllMaintenanceRecords() {
        return allMaintenanceItems;
    }

    public MutableLiveData<VehicleMaintenance> getVehicleMaintenance(int id) {
        return maintenanceRepository.getVehicleMaintenance(id);
    }

    public MutableLiveData<String> insert(VehicleMaintenance maintenance) {
        return maintenanceRepository.insert(maintenance);
    }

    public void update(VehicleMaintenance maintenance) {
        maintenanceRepository.update(maintenance);
    }

    public void delete(VehicleMaintenance maintenance) {
        maintenanceRepository.delete(maintenance);
    }
}
