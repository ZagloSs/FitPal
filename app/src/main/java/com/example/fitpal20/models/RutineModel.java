package com.example.fitpal20.models;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class RutineModel implements Serializable {


    @SerializedName("id")
    public int id;

    @SerializedName("name")
    public String rutineName;

    public RutineModel(String rutineName){
        this.rutineName = rutineName;
    }

    public String getRutineName() {
        return rutineName;
    }

}
