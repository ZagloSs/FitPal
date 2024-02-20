package com.example.fitpal20.retrofit;

import com.example.fitpal20.models.ExerciseModel;
import com.example.fitpal20.models.Usuario;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface APIService {

    @GET("/ejercicio/all")
    Call<List<ExerciseModel>> allEx();

    @GET("/usuario/findbyid/{id}")
    Call<Usuario> getUserId(@Body int id);

    @GET("/usuario/findUserpass/{correo}")
    Call<String> getUserPassWEmail(@Body String correo);


}
