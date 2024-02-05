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

        btnRegister= findViewById(R.id.register_register_btn);
        etCorreo =  findViewById(R.id.register_email_editText);
        etPass = findViewById(R.id.register_pass_editText);
        etConPass = findViewById(R.id.register_confirmPass_editText);
        tvLogin = findViewById(R.id.register_login_textView);

        //Parametros para el toast
        textToast = "Te has registrado correctamente";
        duration = Toast.LENGTH_LONG;

        //Parametros para el if



        //If Verificacion si esta vacio

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                verCorreo = etCorreo.getText().toString();
                verPass = etPass.getText().toString();
                verConPass = etConPass.getText().toString();

                if(verCorreo.equals("")){
                    etCorreo.setError("Porfavor ingresa un correo electrónico");
                    etCorreo.requestFocus();


                }else if (verPass.equals("")){
                    etPass.setError("Porfavor introduzca una contraseña");
                    etPass.requestFocus();


                } else if(verConPass.equals("")){
                    etConPass.setError("Porfavor confirme una contraseña");
                    etConPass.requestFocus();

                } else if (!verConPass.equals(verPass)) {
                    etPass.setError("Las contraseñas no coinciden");
                    etConPass.setError("Las contraseñas no coinciden");
                    etConPass.requestFocus();
   ;
                }else{
                    Intent intent = new Intent(Register.this, Login.class);
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
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
            }
        });
    }
}