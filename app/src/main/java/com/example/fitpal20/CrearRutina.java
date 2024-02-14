package com.example.fitpal20;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.os.Bundle;

public class CrearRutina extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear_rutina);

        getWindow().setStatusBarColor(ContextCompat.getColor(this, R.color.dark_gray));
        getWindow().setNavigationBarColor(ContextCompat.getColor(this, R.color.dark_gray));


    }
}