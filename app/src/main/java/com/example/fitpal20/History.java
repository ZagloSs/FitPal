package com.example.fitpal20;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fitpal20.models.HistoryModel;
import com.example.fitpal20.rvadapters.HistoryRecyclerViewAdapter;

import java.util.ArrayList;

public class History extends AppCompatActivity {
    ArrayList<HistoryModel> historyModels = new ArrayList<>();
    HistoryRecyclerViewAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_historial);

        getWindow().setStatusBarColor(ContextCompat.getColor(this, R.color.dark_gray));
        getWindow().setNavigationBarColor(ContextCompat.getColor(this, R.color.dark_gray));

        RecyclerView rv = findViewById(R.id.rv_historial);
        setHistorial();

        adapter = new HistoryRecyclerViewAdapter(this, historyModels);
        rv.setAdapter(adapter);
        rv.setLayoutManager(new LinearLayoutManager(this));


    }

    private void setHistorial(){
        String[] hNameEx = {"Press militar", "Press banca", "Elevaciones laterales", "Curl biceps"};
        int[] hSets = {3,3,3,3};
        int[] hReps = {8,7,9,10};
        float[] hKg = {24,60,12,45};

        for(int i = 0; i<hNameEx.length;i++){
            historyModels.add(new HistoryModel(hNameEx[i],hSets[i],hReps[i], hKg[i]));
        }

    }



}

