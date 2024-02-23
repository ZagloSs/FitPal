package com.example.fitpal20;

import android.os.Bundle;

import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.fitpal20.models.HistoryModel;
import com.example.fitpal20.retrofit.APIClient;
import com.example.fitpal20.retrofit.APIService;
import com.example.fitpal20.rvadapters.HistoryRecyclerViewAdapter;

import java.util.ArrayList;


public class HistoryFragment extends Fragment {

    ArrayList<HistoryModel> historyModels = new ArrayList<>();
    HistoryRecyclerViewAdapter adapter;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_historial, container, false);


        RecyclerView rv = view.findViewById(R.id.rv_historial);
        setHistorial();



        adapter = new HistoryRecyclerViewAdapter(view.getContext(), historyModels);
        rv.setAdapter(adapter);
        rv.setLayoutManager(new LinearLayoutManager(view.getContext()));

        return view;
    }

    private void setHistorial() {
        String[] hNameEx = {"Press militar", "Press banca", "Elevaciones laterales", "Curl biceps"};
        int[] hSets = {3, 3, 3, 3};
        int[] hReps = {8, 7, 9, 10};
        float[] hKg = {24, 60, 12, 45};

        for (int i = 0; i < hNameEx.length; i++) {
            historyModels.add(new HistoryModel(hNameEx[i], hSets[i], hReps[i], hKg[i]));
        }
    }
}