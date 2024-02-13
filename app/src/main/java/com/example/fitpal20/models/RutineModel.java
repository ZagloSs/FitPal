package com.example.fitpal20.models;

public class RutineModel {
    public String rutineName;
    public String days;

    public RutineModel(String rutineName, String days){
        this.rutineName = rutineName;
        this.days = days;
    }

    public String getRutineName() {
        return rutineName;
    }

    public String getDays() {
        return days;
    }
}
