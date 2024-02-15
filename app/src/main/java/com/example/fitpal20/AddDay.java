package com.example.fitpal20;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;


public class AddDay extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_day);

        findViewById(R.id.btnAddDay).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(AddDay.this, "Workout added", Toast.LENGTH_SHORT).show();
                //saveExerciseEntry();
            }
        });
    }
/*
    private void saveExerciseEntry() {
        EditText exerciseNameField = findViewById(R.id.exercise_name);
        EditText setsDoneField = findViewById(R.id.sets_done);
        EditText repsDoneField = findViewById(R.id.reps_done);
        EditText weightUsedField = findViewById(R.id.weight_used);

        String exerciseName = exerciseNameField.getText().toString();
        int setsDone = Integer.parseInt(setsDoneField.getText().toString());
        int repsDone = Integer.parseInt(repsDoneField.getText().toString());
        int weightUsed = Integer.parseInt(weightUsedField.getText().toString());

        // Save the exercise entry to database
    }*/
}