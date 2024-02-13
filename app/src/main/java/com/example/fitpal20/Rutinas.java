package com.example.fitpal20;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fitpal20.models.RutineModel;
import com.example.fitpal20.rvadapters.RutineRecylerViewAdapter;

import java.util.ArrayList;


public class Rutinas extends AppCompatActivity {

    ArrayList<RutineModel> RutineModels = new ArrayList<>();
    RutineRecylerViewAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_rutinas);

        RecyclerView rv = findViewById(R.id.recylcerViewRutine);
        setRutines();


        adapter = new RutineRecylerViewAdapter(this, RutineModels);
        rv.setAdapter(adapter);
        rv.setLayoutManager(new LinearLayoutManager(this));

    }

    private void setRutines(){
        String[] rNames = {"Carga Alta", "PushPullLegs", "Sam's"};
        String[] rDays = {"L,M,X", "J,V,S", "L,J,D"};

        for(int i = 0;  i < rNames.length; i++){
            RutineModels.add(new RutineModel(rNames[i], rDays[i]));
        }
    }

}
