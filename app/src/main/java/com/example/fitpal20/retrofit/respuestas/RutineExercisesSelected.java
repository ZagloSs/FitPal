package com.example.fitpal20.retrofit.respuestas;

import com.example.fitpal20.models.RutinaEjercicioModel;
import com.example.fitpal20.models.Usuario;

import java.util.List;

public class RutineExercisesSelected {

    public static RutineExercisesSelected instancia;

    private List<RutinaEjercicioModel> exercises;

    private RutineExercisesSelected() {
    }
    public static synchronized RutineExercisesSelected getInstance() {
        if (instancia == null) {
            instancia = new RutineExercisesSelected();
        }
        return instancia;
    }

    public List<RutinaEjercicioModel> getRutinaEjercicios() {
        return exercises;
    }

    public void setRutinaEjercicios(List<RutinaEjercicioModel> exercises) {
        this.exercises = exercises;
    }
}


