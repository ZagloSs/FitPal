package com.example.fitpal20.models;

public class Usuario {
    private int id;
    private String nombre;
    private float altura;
    private float peso;
    private String genero;
    private int rutina_actual;
    private String apellido;
    private String correo;
    private String contraseña;

    public Usuario(int id, String nombre, float altura, float peso, String genero, int rutina_actual, String apellido, String correo, String contraseña) {
        this.id = id;
        this.nombre = nombre;
        this.altura = altura;
        this.peso = peso;
        this.genero = genero;
        this.rutina_actual = rutina_actual;
        this.apellido = apellido;
        this.correo = correo;
        this.contraseña = contraseña;
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
}
