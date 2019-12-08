package com.example.vehiclemaintenancetracker.view;

import android.app.Activity;
import android.content.Intent;
import android.os.Handler;
import android.os.Bundle;

import com.example.vehiclemaintenancetracker.R;


public class SplashActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);


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

    protected void viewVehicleMaintenanceList() {
        Intent launchApp = new Intent(this, MainActivity.class);
        startActivity(launchApp);
    }
}


