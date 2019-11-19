package com.example.vehiclemaintenancetracker.view;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.vehiclemaintenancetracker.R;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
//        logging.setLevel(HttpLoggingInterceptor.Level.BASIC);
//        OkHttpClient client = new OkHttpClient.Builder()
//                .addInterceptor(logging)
//                .build();
    }
}
