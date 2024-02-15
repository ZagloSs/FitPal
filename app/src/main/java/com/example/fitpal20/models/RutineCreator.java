package com.example.fitpal20.models;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class RutineCreator {
    private String Name;
    private ArrayList<ArrayList<String>> Ejercicios;
    private ArrayList<String> day;

    public RutineCreator(String name, ArrayList<ArrayList<String>> ejercicios, ArrayList<String> day) {
        Name = name;
        Ejercicios = ejercicios;
        this.day = day;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public ArrayList<ArrayList<String>> getEjercicios() {
        return Ejercicios;
    }

    public void setEjercicios(ArrayList<ArrayList<String>> ejercicios) {
        Ejercicios = ejercicios;
    }

    public ArrayList<String> getDay() {
        return day;
    }

    public void setDay(ArrayList<String> day) {
        this.day = day;
    }
}
