package com.example.fitpal20.models;

public class Workout {
    private String date;
    private String[] exercise;
    private int[] weight;
    private int[] reps;

    public Workout(String date, String[] exercise, int[] weight, int[] reps) {
        this.date = date;
        this.exercise = exercise;
        this.weight = weight;
        this.reps = reps;
    }

    // Getters and setters for each property

    // Override toString to display workout info in a readable format
    @Override
    public String toString() {
        return "Date: " + date + ", Exercise: " + exercise +
                ", Weight: " + weight + " kg, Reps: " + reps;
    }
}
