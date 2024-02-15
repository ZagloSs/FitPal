package com.example.fitpal20.models;

public class ExerciseModel {
    private String name;
    private String base64ImgExercise;

    public ExerciseModel(String name, String base64ImgExercise) {
        this.name = name;
        this.base64ImgExercise = base64ImgExercise;
    }

    public String getName() {
        return name;
    }

    public String getBase64ImgExercise() {
        return base64ImgExercise;
    }
}
