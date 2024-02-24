package com.example.fitpal20.retrofit.respuestas;

public class ResupuestaDiasAsist {

    public static ResupuestaDiasAsist instancia;

    public int diasAsist;

    public ResupuestaDiasAsist() {
    }

    public static synchronized ResupuestaDiasAsist getInstance(){
        if(instancia == null){
            instancia = new ResupuestaDiasAsist();
        }
        return instancia;
    }

    public int getDiasAsist() {
        return diasAsist;
    }

    public void setDiasAsist(int diasAsist) {
        this.diasAsist = diasAsist;
    }
}
