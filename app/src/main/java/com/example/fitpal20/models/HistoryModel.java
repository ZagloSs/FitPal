package com.example.fitpal20.models;

public class HistoryModel {
    public String exName;
    public int sets;
    public int reps;
    public float kg;

    public HistoryModel(String exName, int sets, int reps, float kg) {
        this.exName = exName;
        this.sets = sets;
        this.reps = reps;
        this.kg = kg;
    }

    public String getExName() {
        return exName;
    }

    public int getSets() {
        return sets;
    }

    public int getReps() {
        return reps;
    }

    public float getKg() {
        return kg;
    }
}
