package com.example.fitpal20.models;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Usuarios_dias implements Serializable {

    @SerializedName("idUsuarios_dias")
    private int id;

    @SerializedName("idDia")
    private int idDia;

    @SerializedName("idUsuario")
    private int idUsuario;

    public Usuarios_dias(int idDia, int idUsuario) {
        this.idDia = idDia;
        this.idUsuario = idUsuario;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdDia() {
        return idDia;
    }

    public void setIdDia(int idDia) {
        this.idDia = idDia;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }
}
