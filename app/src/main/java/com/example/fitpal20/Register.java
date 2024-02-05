package com.example.fitpal20;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Register extends AppCompatActivity {

    Button btnRegister;
    EditText etCorreo, etPass, etConPass;
    TextView tvLogin;

    //Parametros para el toast
    CharSequence textToast;
    Integer duration;

    //If correo
    String verCorreo, verPass, verConPass;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        btnRegister= (Button) findViewById(R.id.register_register_btn);
        etCorreo = (EditText) findViewById(R.id.register_email_editText);
        etPass = (EditText) findViewById(R.id.register_pass_editText);
        etConPass = (EditText) findViewById(R.id.register_confirmPass_editText);
        tvLogin = (TextView) findViewById(R.id.register_login_textView);

        //Parametros para el toast
        textToast = "Te has registrado correctamente";
        duration = Toast.LENGTH_LONG;

        //Parametros para el if
        verCorreo = etCorreo.getText().toString();
        verPass = etPass.getText().toString();
        verConPass = etConPass.getText().toString();


        //If Verificacion si esta vacio

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Register.this, Login.class);
                if(verCorreo.matches("")){
                    Toast.makeText(Register.this, "Porfavor ingresa un correo electronico", duration);
                    startActivity(intent);

                }else if (verPass.matches("")){
                    Toast.makeText(Register.this, "Porfavor ingresa una contraseña", duration);
                    startActivity(intent);

                } else if (!verConPass.matches(verPass)) {
                    Toast.makeText(Register.this, "Las contraseñas tienen que ser iguales", duration);                    startActivity(intent);
                    startActivity(intent);
                }else{
                    Toast toast = Toast.makeText(Register.this, textToast, duration);
                    toast.show();

                }
            }
        });

        tvLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Register.this, Login.class);
                startActivity(intent);
            }
        });
    }
}