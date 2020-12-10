package com.abpl.parentunetest.network;

import com.abpl.parentunetest.utils.AppConstants;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RestClient {
    private static Retrofit retrofit;
    private static HttpLoggingInterceptor logging = new HttpLoggingInterceptor();

    final static OkHttpClient.Builder okHttpClient = new OkHttpClient.Builder()
            .readTimeout(AppConstants.API_READ_TIMEOUT, TimeUnit.SECONDS)
            .connectTimeout(AppConstants.API_CONNECTION_TIMEOUT, TimeUnit.SECONDS);

    public static Retrofit getRetrofitClient(String baseUrl){
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        retrofit = new Retrofit.Builder().baseUrl(baseUrl).client(okHttpClient.build()).addConverterFactory(GsonConverterFactory.create()).build();
        return retrofit;
    }
}
