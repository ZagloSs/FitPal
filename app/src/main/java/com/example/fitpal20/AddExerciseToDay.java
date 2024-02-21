package com.example.fitpal20;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.fitpal20.models.ExerciseModel;
import com.example.fitpal20.retrofit.APIClient;
import com.example.fitpal20.retrofit.APIService;
import com.example.fitpal20.rvadapters.ExerciseRecylcerViewAdapter;
import com.example.fitpal20.rvadapters.HistoryRecyclerViewAdapter;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddExerciseToDay extends AppCompatActivity {

    String dayToAdd;
    TextView title;
    String ttl;
    Button submit;
    List<ExerciseModel> exerciseModels = new ArrayList<ExerciseModel>();
    SearchView searchBar;


     ExerciseRecylcerViewAdapter adapter;
     ArrayList<ExerciseModel> ExerciseModels = new ArrayList<>();

    ImageView goBackRutine;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_exercise_to_day);
        APIClient apiClient = APIClient.getInstance();
        apiClient.ApiClient();
        APIService apiService = apiClient.getApiService();
        getWindow().setStatusBarColor(ContextCompat.getColor(this, R.color.dark_gray));
        getWindow().setNavigationBarColor(ContextCompat.getColor(this, R.color.dark_gray));

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            dayToAdd= extras.getString("day");
        }
        setExercises(apiService);

        title = findViewById(R.id.addDayTitle);
        title.setText(dayToAdd);

        goBackRutine = findViewById(R.id.gobackRutine);

        submit = findViewById(R.id.btnAceptarAddDay);


        RecyclerView rv = findViewById(R.id.rv_exercises);


        searchBar = findViewById(R.id.et_SearchExercise);
        searchBar.clearFocus();
        searchBar.setOnQueryTextListener(new SearchView.OnQueryTextListener(){
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                filterList(newText);
                return false;
            }
        });

        adapter = new ExerciseRecylcerViewAdapter(this, ExerciseModels);
        rv.setAdapter(adapter);
        rv.setLayoutManager(new LinearLayoutManager(this));

        ttl = title.getText().toString();
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AddExerciseToDay.this, CrearRutina.class);
                switch(ttl) {
                    case "Lunes":
                        intent.putExtra("L", "PressBanca");
                        break;
                    case "Martes":
                        intent.putExtra("M", "getEjercicios()");
                        break;
                    case "Miercoles":
                        intent.putExtra("X", "getEjercicios()");
                        break;
                    case "Jueves":
                        intent.putExtra("J", "getEjercicios()");
                    case "Viernes":
                        intent.putExtra("V", "getEjercicios()");
                        break;
                    case "Sabado":
                        intent.putExtra("S", "getEjercicios()");
                        break;
                    case "Domingo":
                        intent.putExtra("D", "getEjercicios()");
                        break;
                    default:
                        intent.putExtra("n", "null");

                }
                startActivity(intent);
                finish();

            }
        });


        goBackRutine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent( AddExerciseToDay.this, CrearRutina.class);
                startActivity(intent);
                finish();
            }
        });
    }

    public void setExercises(APIService apiService){
        ArrayList<String> names = new ArrayList<>();
        ArrayList<String> groupsMusc = new ArrayList<>();
        ArrayList<String> base64 = new ArrayList<>();

        if(apiService != null){
            Call<List<ExerciseModel>> callEjercicio = apiService.allEx();
            callEjercicio.enqueue(new Callback<List<ExerciseModel>>() {

                @Override
                public void onResponse(Call<List<ExerciseModel>> call, Response<List<ExerciseModel>> response) {
                    if(response != null){
                        exerciseModels = response.body();
                    }
                }
                @Override
                public void onFailure(Call<List<ExerciseModel>> call, Throwable t) {
                    Log.d("Failiure", "dhf");
                }
            });
        }
        for(ExerciseModel ex : exerciseModels) {
            names.add(ex.getName());
            groupsMusc.add(ex.getGrupo_muscular());
            base64.add(ex.getBase64ImgExercise());
        }

        for (int i = 0; i < names.size(); i++){
            ExerciseModels.add(new ExerciseModel(i, names.get(i), groupsMusc.get(i),base64.get(i)));
        }


    }

    private void filterList(String newText){
        ArrayList<ExerciseModel> filteredList = new ArrayList<>();

        for(ExerciseModel ex : ExerciseModels){
            if(ex.getName().toLowerCase().contains(newText.toLowerCase())){
                filteredList.add(ex);
            }
            if(filteredList.isEmpty()){
                Toast.makeText(this, "No exercise found with this name", Toast.LENGTH_SHORT).show();
            }else{
                adapter.setFilteredList(filteredList);
            }
        }
    }
}




























//    public void recuperarArmariosUsuario(ApiService apiService, RespuestaInsertarUsuario usuario) {
//
//        if(apiService != null){
//            Call<List<ArmarioModelo>> call = apiService.getArmariosUser(1);// 1, es para la prueba, en realidad hay que meter -> usuario.getId()
//            call.enqueue(new Callback<List<ArmarioModelo>>() {
//                @Override
//                public void onResponse( Call<List<ArmarioModelo>> call, Response<List<ArmarioModelo>> response) {
//                    Log.d("TAG", "Código de respuesta: " + response.code());
//
//                    if(response.isSuccessful()){
//                        armariosList = response.body();
//                        for (ArmarioModelo armarioModelo: armariosList) {
//                            Log.d("armario_" +armarioModelo.getId(),"el nombre del armario es: " + armarioModelo.getNombre_armario());
//                        }
//                        //vamos a tener que trabajar con el Viewmodel y eso desde aqui.s
//                    }else {
//                        try {
//                            Log.e("Error en la respuesta", response.errorBody().string());
//                        } catch (IOException e) {
//                            e.printStackTrace();
//                        }
//                    }
//                }
//
//                @Override
//                public void onFailure(Call<List<ArmarioModelo>> call, Throwable t) {
//
//                }
//
//            });
//        }
//    }