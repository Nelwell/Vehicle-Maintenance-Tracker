package com.example.vehiclemaintenancetracker.service;

import com.example.vehiclemaintenancetracker.BuildConfig;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class AuthorizationHeaderInterceptor implements Interceptor {

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        Request requestWithHeaders = request.newBuilder()
                .addHeader("Authorization", BuildConfig.VEHICLE_API_KEY)
                .build();
        return chain.proceed(requestWithHeaders);
    }
}
