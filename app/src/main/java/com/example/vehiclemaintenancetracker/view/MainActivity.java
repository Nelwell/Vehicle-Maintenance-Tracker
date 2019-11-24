package com.example.vehiclemaintenancetracker.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.graphics.Path;
import android.os.Bundle;
import android.os.Handler;

import com.example.vehiclemaintenancetracker.R;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;

public class MainActivity extends AppCompatActivity implements
        OpeningFragment.OpeningFragmentListener,
        MaintenanceListFragment.afterTitleScreenExpires {

    private static final String TAG_OPENING_TITLE = "OpeningTitleFragment";
    private static final String TAG_VIEW_MAINTENANCE_LIST = "MaintenanceListFragment";

    private OpeningFragment mOpeningFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
//        logging.setLevel(HttpLoggingInterceptor.Level.BASIC);
//        OkHttpClient client = new OkHttpClient.Builder()
//                .addInterceptor(logging)
//                .build();

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
        MaintenanceListFragment maintenanceListFragment = MaintenanceListFragment.newInstance();
        ft.replace(android.R.id.content, maintenanceListFragment, TAG_VIEW_MAINTENANCE_LIST);
        ft.addToBackStack(null);
        ft.commit();
}
}