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
import android.widget.Toast;

import com.example.vehiclemaintenancetracker.R;
import com.example.vehiclemaintenancetracker.model.VehicleMaintenance;
import com.example.vehiclemaintenancetracker.viewmodel.MaintenanceViewModel;

import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;

public class MainActivity extends AppCompatActivity implements
        OpeningFragment.OpeningFragmentListener,
        MaintenanceListFragment.AfterTitleScreenListener {

    private static final String TAG_OPENING_TITLE = "OpeningTitleFragment";
    private static final String TAG_VIEW_MAINTENANCE_LIST = "MaintenanceListFragment";

    private OpeningFragment mOpeningFragment;
    private MaintenanceListFragment maintenanceListFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        openingTitleScreen();
    }

    @Override
    public void openingTitleScreen() {

        mOpeningFragment = OpeningFragment.newInstance();

        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();

        ft.add(android.R.id.content, mOpeningFragment, TAG_OPENING_TITLE);
        ft.addToBackStack(null);
        ft.commit();

        // Taken from StackOverflow @ https://stackoverflow.com/questions/3072173/how-to-call-a-method-after-a-delay-in-android
        // Delays the call to viewVehicleMaintenanceList by the specified amount of time
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                viewVehicleMaintenanceList();
            }
        }, 2500);
    }

    @Override
    public void viewVehicleMaintenanceList() {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        maintenanceListFragment = MaintenanceListFragment.newInstance();
        ft.replace(android.R.id.content, maintenanceListFragment, TAG_VIEW_MAINTENANCE_LIST);
        ft.addToBackStack(null);
        ft.commit();
    }

//    @Override
//    public void addNewMaintenanceFabPressed() {
//
//    }
}