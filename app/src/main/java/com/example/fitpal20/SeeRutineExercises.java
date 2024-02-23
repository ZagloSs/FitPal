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
import android.widget.ImageView;
import android.widget.SearchView;
import android.widget.TextView;

import com.example.fitpal20.models.ExerciseModel;
import com.example.fitpal20.models.RutinaEjercicioModel;
import com.example.fitpal20.models.Usuario;
import com.example.fitpal20.retrofit.APIClient;
import com.example.fitpal20.retrofit.APIService;
import com.example.fitpal20.retrofit.respuestas.RespuestaUsuario;
import com.example.fitpal20.retrofit.respuestas.RutineExercisesSelected;
import com.example.fitpal20.rvadapters.ExerciseRecylcerViewAdapter;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SeeRutineExercises extends AppCompatActivity {

    String dayToAdd;
    TextView title;
    String ttl;
    Button submit;
    List<ExerciseModel> exerciseModels = new ArrayList<ExerciseModel>();
    SearchView searchBar;


     ExerciseRecylcerViewAdapter adapter;

    ImageView goBackRutine;

    APIService apiService;
    APIClient apiClient = new APIClient();

    ArrayList<ExerciseModel> ExerciseModels = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_exercise_to_day);



        getWindow().setStatusBarColor(ContextCompat.getColor(this, R.color.dark_gray));
        getWindow().setNavigationBarColor(ContextCompat.getColor(this, R.color.dark_gray));


        title = findViewById(R.id.addDayTitle);
        title.setText(dayToAdd);

        goBackRutine = findViewById(R.id.gobackRutine);

        submit = findViewById(R.id.btnAceptarAddDay);

        Usuario user = RespuestaUsuario.getInstance().getUsuario();
        RecyclerView rv = findViewById(R.id.rv_exercises);


        //Instancia de la api
        apiClient = APIClient.getInstance();
        apiClient.ApiClient();
        apiService = apiClient.getApiService();

        adapter = new ExerciseRecylcerViewAdapter(this, ExerciseModels);
        rv.setAdapter(adapter);
        rv.setLayoutManager(new LinearLayoutManager(this));

        if(apiService != null){
            Call<List<RutinaEjercicioModel>> callEjercicio = apiService.getRutineExerciseWRid(user.getRutina_actual());
            callEjercicio.enqueue(new Callback<List<RutinaEjercicioModel>>() {
                @Override
                public void onResponse(Call<List<RutinaEjercicioModel>> call, Response<List<RutinaEjercicioModel>> response) {
                    if(response != null){
                        List<RutinaEjercicioModel> rExModels = response.body();
                        List<ExerciseModel> exModels = new ArrayList<>();
                        for(RutinaEjercicioModel rutina: rExModels){
                            Call<ExerciseModel> exercise = apiService.exById(rutina.getIdejercicios());
                            exercise.enqueue(new Callback<ExerciseModel>() {
                                @Override
                                public void onResponse(Call<ExerciseModel> call, Response<ExerciseModel> response) {
                                    if (response.isSuccessful()){
                                        exModels.add(response.body());
                                        adapter.updateAdapter(exModels);
                                    }
                                }

                                @Override
                                public void onFailure(Call<ExerciseModel> call, Throwable t) {

                                }
                            });
                        }



                    }
                }
                @Override
                public void onFailure(Call<List<RutinaEjercicioModel>> call, Throwable t) {
                    Log.d("Failiure", "dhf");
                }
            });
        }

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



        ttl = "title.getText().toString();";

        goBackRutine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent( SeeRutineExercises.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }


    private void filterList(String newText){
        ArrayList<ExerciseModel> filteredList = new ArrayList<>();

        for(ExerciseModel ex : ExerciseModels){
            if(ex.getName().toLowerCase().contains(newText.toLowerCase())){
                filteredList.add(ex);
            }
            if(!filteredList.isEmpty()){
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