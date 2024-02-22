package com.example.fitpal20.retrofit;

import android.util.JsonReader;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class APIClient {

    //gestiona retrofit
//patron singleton
    private static APIClient instance = null;
    private static Retrofit retrofitClient = null;
    private static APIService apiService = null;
    public void ApiClient(){
        OkHttpClient.Builder okHttpBuilder = new OkHttpClient.Builder();
        OkHttpClient okHttpClient = okHttpBuilder.build();

        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        retrofitClient =  new Retrofit.Builder()
                .baseUrl("http://192.168.56.1:8086")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(okHttpClient)
                .build();
        apiService =   retrofitClient.create(APIService.class);
    }

    //instancia del metodo de creacion de la apiClient
    static public APIClient getInstance(){
        if(instance==null){
            instance = new APIClient();
        }
        return instance;
    }

    public APIService getApiService(){
        Log.d("SERVIE", ":" + apiService);
        return apiService;
    }

}

