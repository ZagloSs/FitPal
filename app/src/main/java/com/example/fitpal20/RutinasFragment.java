package com.example.fitpal20;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.fitpal20.models.RutineModel;
import com.example.fitpal20.retrofit.APIClient;
import com.example.fitpal20.retrofit.APIService;
import com.example.fitpal20.rvadapters.RutineRecylerViewAdapter;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RutinasFragment extends Fragment {


    ArrayList<RutineModel> RutineModels = new ArrayList<>();
    RutineRecylerViewAdapter adapter;

    APIService apiService;
    APIClient apiClient = new APIClient();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_rutinas, container, false);
        RecyclerView rv =view.findViewById(R.id.recylcerViewRutine);



        //Instancia de la api
        apiClient = APIClient.getInstance();
        apiClient.ApiClient();
        apiService = apiClient.getApiService();

        adapter = new RutineRecylerViewAdapter(view.getContext(), RutineModels);
        rv.setAdapter(adapter);
        rv.setLayoutManager(new LinearLayoutManager(view.getContext()));


        if (apiService != null) {
            Call<List<RutineModel>> callAllRutines = apiService.allRutines();
            callAllRutines.enqueue(new Callback<List<RutineModel>>() {
                @Override
                public void onResponse(Call<List<RutineModel>> call, Response<List<RutineModel>> response) {
                    if(response.isSuccessful()){
                        ArrayList<String> rNames = new ArrayList<>();
                        List<RutineModel> rutines = response.body();
                        for(RutineModel rutine: rutines){
                            rNames.add(rutine.getRutineName());
                            Log.d("Name", rutine.getRutineName());
                        }
                        ArrayList<RutineModel> RM = new ArrayList<>();
                        for(int i = 0;  i < rNames.size(); i++){
                            RM.add(new RutineModel(rNames.get(i)));
                        }
                        adapter.updateAdapter(RM);

                    }
                }

                @Override
                public void onFailure(Call<List<RutineModel>> call, Throwable t) {

                }
            });
        }



        return view;
    }

    private void setRutines(APIService apiService){

    }
}