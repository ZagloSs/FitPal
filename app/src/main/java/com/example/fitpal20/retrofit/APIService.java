package com.example.fitpal20.retrofit;

import com.example.fitpal20.models.ExerciseModel;
import com.example.fitpal20.models.Usuario;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface APIService {

    @Headers("Accept: application/json")
    @GET("/ejercicio/all")
    Call<List<ExerciseModel>> allEx();

    @Headers("Accept: application/json")
    @GET("/usuario/findbyid/{id}")
    Call<Usuario> getUserId(@Path("id") int id);

    @Headers("Accept: application/json")
    @GET("/usuario/findUserpass/{correo}")
    Call<Usuario> getUserWEmail(@Path("correo")String correo);

    @Headers("Accept: application/json")
    @POST("/usuario/postUser")
    Call<Usuario> postUser(@Body Usuario user);


}
