package com.example.vehiclemaintenancetracker.repository;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.example.vehiclemaintenancetracker.model.VehicleMaintenance;
import com.example.vehiclemaintenancetracker.service.AuthorizationHeaderInterceptor;
import com.example.vehiclemaintenancetracker.service.VehicleService;

import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class VehicleRepository {

    private static final String TAG = "VEHICLE_REPOSITORY";

    private VehicleService mVehicleService;
    private final String baseURL = "https://carrecords.herokuapp.com/api/";
    private MutableLiveData<List<VehicleMaintenance>> mAllMaintenanceRecords;

    public VehicleRepository() {

        // Create client with interceptor to set header on each request
//        OkHttpClient client = new OkHttpClient.Builder()
//                .addInterceptor(new AuthorizationHeaderInterceptor())
//                .build();

        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BASIC);
        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(logging)
                .build();

        // Create and configure Retrofit instance
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseURL)
//                .addConverterFactory(JacksonConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();

        // Create implementation of VehicleService interface
        mVehicleService = retrofit.create(VehicleService.class);

        // Initialize mAllMaintenanceRecords
        mAllMaintenanceRecords = new MutableLiveData<>();
    }

    public MutableLiveData<List<VehicleMaintenance>> getAllMaintenanceRecords() {

        mVehicleService.getAllMaintenanceRecords().enqueue(new Callback<List<VehicleMaintenance>>() {
            @Override
            public void onResponse(Call<List<VehicleMaintenance>> call, Response<List<VehicleMaintenance>> response) {
                if (response.isSuccessful()) {
                    Log.d(TAG, "getAllMaintenanceRecords response body: " + response.body());
                    mAllMaintenanceRecords.setValue(response.body());
                } else {
                    Log.e(TAG, "Error getting all records, message from server: " + response.message());
                }
            }

            @Override
            public void onFailure(Call<List<VehicleMaintenance>> call, Throwable t) {
                Log.e(TAG, "Error fetching all records", t);
            }
        });
        return mAllMaintenanceRecords;

    }


    public MutableLiveData<VehicleMaintenance> getVehicleMaintenance(final int id) {

        /* Fetch one VehicleMaintenance by its ID. The value is available by observing the
        MutableLiveData object returned from this method.

        This method isn't used in the app, but fetching an item by ID is
        a very common task so I added it anyway, as an example. */

        final MutableLiveData<VehicleMaintenance> maintenance = new MutableLiveData<>();

        mVehicleService.get(id).enqueue(new Callback<VehicleMaintenance>() {
            @Override
            public void onResponse(Call<VehicleMaintenance> call, Response<VehicleMaintenance> response) {
                if (response.isSuccessful()) {
                    Log.d(TAG, "fetched record " + response.body());
                    maintenance.setValue(response.body());
                } else {
                    Log.e(TAG, "Error getting record id " + id + " because " + response.message());
                    maintenance.setValue(null);
                }
            }

            @Override
            public void onFailure(Call<VehicleMaintenance> call, Throwable t) {
                Log.e(TAG, "Error getting record id " + id, t);
            }
        });

        return maintenance;
    }

    public MutableLiveData<String> insert(final VehicleMaintenance vehicle) {

        final MutableLiveData<String> insertResult = new MutableLiveData<>();

        mVehicleService.insert(vehicle).enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful()) {
                    Log.d(TAG, "inserted " + vehicle);
                    insertResult.setValue("success");
                    getAllMaintenanceRecords();
                } else {
                    String error;
                    try {
                        error = response.errorBody().toString();
                        insertResult.setValue(error);
                        Log.e(TAG, "Error inserting record, response from server: " + error + " message " + response.message());

                    } catch (Exception e) {
                        insertResult.setValue("error");
                        Log.e(TAG, "Error inserting record, message from server: " + response.message());
                    }
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                insertResult.setValue("error");
                Log.e(TAG, "Error inserting record for " + vehicle, t);
            }
        });

        return insertResult;

    }


    public void update(final VehicleMaintenance vehicle) {

        mVehicleService.update(vehicle, vehicle.getId()).enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful()) {
                    Log.d(TAG, "updated record for " + vehicle);
                    getAllMaintenanceRecords();
                } else {
                    Log.e(TAG, "Error updating record, message from server: " + response.message());
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Log.e(TAG, "Error updating record for " + vehicle, t);
            }
        });

    }


    public void delete(final VehicleMaintenance vehicle) {

        mVehicleService.delete(vehicle.getId()).enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful()) {
                    Log.d(TAG, "deleted record for " + vehicle);
                    getAllMaintenanceRecords();
                } else {
                    Log.e(TAG, "Error deleting record, message from server: " + response.message());
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Log.e(TAG, "Error deleting record for " + vehicle, t);
            }
        });
    }
}
