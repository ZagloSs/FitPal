package com.example.fitpal20.Calendario;

import android.graphics.drawable.Drawable;
import android.widget.ImageView;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Events implements Serializable {

    @SerializedName("id")
    private int id;

    @SerializedName("nombre")
    private String EVENT;

    @SerializedName("time")
    private String TIME;
    @SerializedName("date")
    private String DATE;

    @SerializedName("month")
    private String MONTH;

    @SerializedName("year")
    private String YEAR;


    public Events(String EVENT, String TIME, String DATE, String MONTH, String YEAR) {
        this.EVENT = EVENT;
        this.TIME = TIME;
        this.DATE = DATE;
        this.MONTH = MONTH;
        this.YEAR = YEAR;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEVENT() {
        return EVENT;
    }

    public void setEVENT(String EVENT) {
        this.EVENT = EVENT;
    }

    public String getTIME() {
        return TIME;
    }

    public void setTIME(String TIME) {
        this.TIME = TIME;
    }

    public String getDATE() {
        return DATE;
    }

    public void setDATE(String DATE) {
        this.DATE = DATE;
    }

    public String getMONTH() {
        return MONTH;
    }

    public void setMONTH(String MONTH) {
        this.MONTH = MONTH;
    }

    public String getYEAR() {
        return YEAR;
    }

    public void setYEAR(String YEAR) {
        this.YEAR = YEAR;
    }
}
