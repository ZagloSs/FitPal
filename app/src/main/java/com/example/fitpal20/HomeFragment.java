package com.example.fitpal20;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.fitpal20.Calendario.CustomCalendarView;
import com.example.fitpal20.models.Usuario;
import com.example.fitpal20.retrofit.APIClient;
import com.example.fitpal20.retrofit.APIService;
import com.example.fitpal20.retrofit.respuestas.RespuestaUsuario;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeFragment extends Fragment {
    CustomCalendarView customCalendarView;

    private TextView pesoTxt;

    APIClient apiClient;
    APIService apiService;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //Calendario
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        //Instancia de la api
        apiClient = APIClient.getInstance();
        apiClient.ApiClient();
        apiService = apiClient.getApiService();

        pesoTxt = view.findViewById(R.id.tv_peso);
        pesoTxt.setText(RespuestaUsuario.getInstance().getUsuario().getPeso() + "KG");


        customCalendarView = view.findViewById(R.id.custom_calendar_view);

        // Inflate the layout for this fragment
        return view;


    }




}