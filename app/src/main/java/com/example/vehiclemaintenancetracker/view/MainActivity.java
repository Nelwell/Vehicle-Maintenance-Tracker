package com.example.vehiclemaintenancetracker.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.Path;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.widget.Toast;

import com.example.vehiclemaintenancetracker.R;
import com.example.vehiclemaintenancetracker.model.VehicleMaintenance;
import com.example.vehiclemaintenancetracker.viewmodel.MaintenanceViewModel;

import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;

public class MainActivity extends AppCompatActivity implements
        MaintenanceListFragment.AfterTitleScreenListener,
        NewMaintenanceItemFragment.OnNewMaintenanceAddedListener,
        MaintenanceListFragment.StartNewMaintenanceItemListener {

    private static final String TAG = "MAIN_ACTIVITY";
    private static final String TAG_OPENING_TITLE = "OpeningTitleFragment";
    private static final String TAG_VIEW_MAINTENANCE_LIST = "MaintenanceListFragment";
    private static final String TAG_ADD_NEW_MAINTENANCE = "NewMaintenanceItemFragment";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        viewVehicleMaintenanceList();
    }


    @Override
    public void viewVehicleMaintenanceList() {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        MaintenanceListFragment maintenanceListFragment = MaintenanceListFragment.newInstance();
        ft.replace(android.R.id.content, maintenanceListFragment, TAG_VIEW_MAINTENANCE_LIST);
        ft.addToBackStack(null);
        ft.commit();
    }

    @Override
    public void startNewMaintenanceItem() {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        NewMaintenanceItemFragment newMaintenanceItemFragment = NewMaintenanceItemFragment.newInstance();
        ft.replace(android.R.id.content, newMaintenanceItemFragment, TAG_ADD_NEW_MAINTENANCE);
        ft.addToBackStack(null);
        ft.commit();
    }

    @Override
    public void onNewMaintenanceAdded(VehicleMaintenance newMaintenanceItem) {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        MaintenanceListFragment maintenanceListFragment = (MaintenanceListFragment) fm.findFragmentByTag(TAG_VIEW_MAINTENANCE_LIST);
        if (maintenanceListFragment != null) {
            ft.replace(android.R.id.content, maintenanceListFragment);
            ft.commit();
        } else {
            Log.w(TAG, "MaintenanceListFragment not found");
        }
    }
}