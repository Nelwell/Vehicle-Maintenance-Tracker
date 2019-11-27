package com.example.vehiclemaintenancetracker.service;

import android.util.Log;

import com.example.vehiclemaintenancetracker.BuildConfig;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;

public class AuthorizationHeaderInterceptor implements Interceptor {

    private static final String TAG = "authorization header";

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        Request requestWithHeaders = request.newBuilder()
                .addHeader("Authorization", BuildConfig.VEHICLE_API_KEY)
                .build();
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BASIC);

        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(new AuthorizationHeaderInterceptor())
                .addInterceptor(logging)
                .build();
//        Log.d(TAG, "header built");
        return chain.proceed(requestWithHeaders);
    }
}
