package com.example.fitpal20.retrofit;

import android.util.Log;

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
        Log.d("Aver", "sd" + apiService);
        OkHttpClient okHttpClient = okHttpBuilder.build();
        Log.d("a", "sd" + apiService);
        retrofitClient =  new Retrofit.Builder()
                .baseUrl("http://192.168.56.1:8086")
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build();
        apiService =   retrofitClient.create(APIService.class);
        Log.d("Aver", "sd" + apiService);
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

