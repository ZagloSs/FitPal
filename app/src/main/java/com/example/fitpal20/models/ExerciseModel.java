package com.example.fitpal20.models;

import com.google.gson.annotations.SerializedName;

public class ExerciseModel {
    @SerializedName("id")
    private Integer id;

    @SerializedName("name")
    private String name;
    @SerializedName("base64")
    private String base64ImgExercise;
    @SerializedName("gmuscular")
    private String grupo_muscular;


    public ExerciseModel(Integer id,String name,String gMusc, String base64ImgExercise) {
        this.id = id;
        this.name = name;
        this.grupo_muscular = gMusc;
        this.base64ImgExercise = base64ImgExercise;
    }

    public String getName() {
        return name;
    }

    public String getBase64ImgExercise() {
        return base64ImgExercise;
    }

    public String getGrupo_muscular() {
        return grupo_muscular;
    }
}
