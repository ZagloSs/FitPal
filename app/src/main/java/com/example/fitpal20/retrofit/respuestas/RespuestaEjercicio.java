package com.example.fitpal20.retrofit.respuestas;


import com.example.fitpal20.models.ExerciseModel;

import java.util.List;

public class RespuestaEjercicio {
    public static RespuestaEjercicio instancia;

    private List<ExerciseModel> ejercicios;

    public static synchronized RespuestaEjercicio getInstance() {
        if (instancia == null) {
            instancia = new RespuestaEjercicio();
        }
        return instancia;
    }

    public List<ExerciseModel> getEjercicios() {
        return ejercicios;
    }

    public void setEjercicios(List<ExerciseModel> ejercicios) {
        this.ejercicios = ejercicios;
    }
}
