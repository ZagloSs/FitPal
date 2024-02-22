package com.example.fitpal20.retrofit.respuestas;

import com.example.fitpal20.models.Usuario;

public class RespuestaUsuario {
    private static RespuestaUsuario instancia;
    private Usuario usuario;

    private RespuestaUsuario() {
    }
    public static synchronized RespuestaUsuario getInstance() {
        if (instancia == null) {
            instancia = new RespuestaUsuario();
        }
        return instancia;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
}