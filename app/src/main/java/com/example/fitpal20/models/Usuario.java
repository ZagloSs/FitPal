package com.example.fitpal20.models;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Usuario implements Serializable {
    @SerializedName("id")
    private int id;

    @SerializedName("nombreUsuario")
    private String nombre;

    @SerializedName("alturaUsuario")
    private float altura;

    @SerializedName("pesoUsuario")
    private float peso;

    @SerializedName("generoUsuario")
    private String genero;

    @SerializedName("rutinaActual")
    private int rutina_actual;

    @SerializedName("apellidoUsuario")
    private String apellido;

    @SerializedName("correo")
    private String correo;

    @SerializedName("contraseña")
    private String contraseña;

    public Usuario(String nombre, float altura, float peso, String genero, int rutina_actual, String apellido, String correo, String contraseña) {
        this.nombre = nombre;
        this.altura = altura;
        this.peso = peso;
        this.genero = genero;
        this.rutina_actual = rutina_actual;
        this.apellido = apellido;
        this.correo = correo;
        this.contraseña = contraseña;
    }



    public Usuario(Serializable user) {
    }



    public int getId() {
        return id;
    }


    public String getNombre() {
        return nombre;
    }


    public float getAltura() {
        return altura;
    }


    public float getPeso() {
        return peso;
    }


    public String getGenero() {
        return genero;
    }


    public int getRutina_actual() {
        return rutina_actual;
    }


    public String getApellido() {
        return apellido;
    }


    public String getCorreo() {
        return correo;
    }


    public String getContraseña() {
        return contraseña;
    }


    public void setRutina_actual(int rutina_actual) {
        this.rutina_actual = rutina_actual;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setAltura(float altura) {
        this.altura = altura;
    }

    public void setPeso(float peso) {
        this.peso = peso;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }
}
