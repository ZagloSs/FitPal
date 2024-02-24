package com.example.fitpal20.retrofit;

import com.example.fitpal20.Calendario.Events;
import com.example.fitpal20.models.ExerciseModel;
import com.example.fitpal20.models.RutinaEjercicioModel;
import com.example.fitpal20.models.RutineModel;
import com.example.fitpal20.models.Usuario;
import com.example.fitpal20.models.Usuarios_dias;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface APIService {

    @Headers("Accept: application/json")
    @GET("/ejercicio/all")
    Call<List<ExerciseModel>> allEx();

    @GET("ejercicio/id/{id}")
    Call<ExerciseModel> exById(@Path("id") int id);

    @Headers("Accept: application/json")
    @GET("/rutinas/all")
    Call<List<RutineModel>> allRutines();

    @Headers("Accept: application/json")
    @GET("/usuario/findbyid/{id}")
    Call<Usuario> getUserId(@Path("id") int id);

    @Headers("Accept: application/json")
    @GET("/usuario/findUserpass/{correo}")
    Call<Usuario> getUserWEmail(@Path("correo")String correo);

    @Headers("Accept: application/json")
    @GET("/dias/all")
    Call<List<Events>> getAllDays();

    @Headers("Accept: application/json")
    @GET("/dias/byid/{id}")
    Call<Events> getDaysById(@Path("id")int id);

    @Headers("Accept: application/json")
    @GET("/rutinas_ejercicios/getejerciciosfromrutina/{id}")
    Call<List<RutinaEjercicioModel>> getRutineExerciseWRid(@Path("id") int id);

    @Headers("Accept: application/json")
    @GET("/usuario_dias/userid/{id}")
    Call<List<Usuarios_dias>> getDiasByUserId(@Path("id") int id);


    @Headers("Accept: application/json")
    @POST("/usuario/postUser")
    Call<Usuario> postUser(@Body Usuario user);

    @Headers("Accept: application/json")
    @POST("/dias/saveDia")
    Call<Events> postDia(@Body Events events);

    @Headers("Accept: application/json")
    @POST("/usuario_dias/save")
    Call<Usuarios_dias> postUsuarioDia(@Body Usuarios_dias usuarios_dias);

    @Headers("Accept: application/json")
    @PUT("/usuario/updateUser")
    Call<Usuario> updateUsuario(@Body Usuario user);




}
