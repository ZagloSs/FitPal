package com.example.fitpal20;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.fitpal20.models.ExerciseModel;
import com.example.fitpal20.rvadapters.ExerciseRecylcerViewAdapter;
import com.example.fitpal20.rvadapters.HistoryRecyclerViewAdapter;

import java.util.ArrayList;

public class AddExerciseToDay extends AppCompatActivity {

    String dayToAdd;
    TextView title;
    String ttl;
    Button submit;

    SearchView searchBar;

     ExerciseRecylcerViewAdapter adapter;
     ArrayList<ExerciseModel> ExerciseModels = new ArrayList<>();

    ImageView goBackRutine;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_exercise_to_day);

        getWindow().setStatusBarColor(ContextCompat.getColor(this, R.color.dark_gray));
        getWindow().setNavigationBarColor(ContextCompat.getColor(this, R.color.dark_gray));

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            dayToAdd= extras.getString("day");
        }
        title = findViewById(R.id.addDayTitle);
        title.setText(dayToAdd);

        goBackRutine = findViewById(R.id.gobackRutine);

        submit = findViewById(R.id.btnAceptarAddDay);

        RecyclerView rv = findViewById(R.id.rv_exercises);
        setExercises();

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

    public void setExercises(){
        String[] names = {"Silla", "Mesa"};
        String[] base64 ={"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"};

        for (int i = 0; i < names.length; i++){
            ExerciseModels.add(new ExerciseModel(names[i], base64[i]));
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