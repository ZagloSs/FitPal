package com.example.fitpal20;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class CrearRutina extends AppCompatActivity {

    EditText nameRutine;
    ImageView goBack;
    String lunes;
    Button submit, addLunes, addMartes, addMiercoles, addJueves, addViernes, addSabado, addDomingo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear_rutina);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            lunes = extras.getString("L");
        }

            System.out.println(lunes);


        getWindow().setStatusBarColor(ContextCompat.getColor(this, R.color.dark_gray));
        getWindow().setNavigationBarColor(ContextCompat.getColor(this, R.color.dark_gray));

        nameRutine = findViewById(R.id.editTextNombreRutina);
        submit = findViewById(R.id.btnCrearRutina);
        goBack = findViewById(R.id.goback);

        addLunes = findViewById(R.id.addLunes);
        addMartes = findViewById(R.id.addMartes);
        addMiercoles = findViewById(R.id.addMiercoles);
        addJueves = findViewById(R.id.addJueves);
        addViernes = findViewById(R.id.addViernes);
        addSabado = findViewById(R.id.addSabado);
        addDomingo = findViewById(R.id.addDomingo);

        Button[] botones={addLunes,addMartes,addMiercoles,addJueves,addViernes,addSabado,addDomingo};

        for(int i = 0; i< botones.length; i++){
            int finalI = i;

            botones[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String day = "";
                    switch (finalI){
                        case 0:
                            day = "Lunes";
                            break;
                        case 1:
                            day = "Martes";
                            break;
                        case 2:
                            day ="Miercoles";
                            break;
                        case 3:
                            day = "Jueves";
                            break;
                        case 4:
                            day = "Viernes";
                            break;
                        case 5:
                            day = "Sabado";
                            break;
                        case 6:
                            day = "Domingo";
                            break;
                        default:
                            day = "Lunes";
                    }

                    Intent intent = new Intent(CrearRutina.this, AddExerciseToDay.class);
                    intent.putExtra("day", day);
                    startActivity(intent);
                }
            });
        }

        goBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CrearRutina.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });



        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = nameRutine.getText().toString();

                if(name.equals("")){
                    Toast.makeText(CrearRutina.this, "Porfavor introuduce un nombre", Toast.LENGTH_SHORT).show();
                }else{
                    introducirRutina();
                    Intent intent = new Intent(CrearRutina.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                }
            }
        });



    }
    public void addExerciseToDay(String day){

    }

    public void introducirRutina(){
        Toast.makeText(this, "Rutina creada", Toast.LENGTH_SHORT).show();
    }
}