package com.example.fitpal20.models;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class RutinaEjercicioModel implements Serializable {


    @SerializedName("idRutinasEjercicios")
    private int idrutinasejercicios;

    @SerializedName("idEjercicios")
    private int idejercicios;

    @SerializedName("idRutinass")
    private int idrutinas;

    public RutinaEjercicioModel(int idejercicios, int idrutinas) {
        this.idejercicios = idejercicios;
        this.idrutinas = idrutinas;
    }

    public int getIdrutinasejercicios() {
        return idrutinasejercicios;
    }

    public int getIdejercicios() {
        return idejercicios;
    }

    public int getIdrutinas() {
        return idrutinas;
    }
}
