package com.example.fitpal20;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.fitpal20.models.Usuario;
import com.example.fitpal20.retrofit.APIClient;
import com.example.fitpal20.retrofit.APIService;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Register extends AppCompatActivity {

    Button btnRegister;
    EditText etCorreo, etPass, etConPass;
    TextView tvLogin;

    //Parametros para el toast
    CharSequence textToast;
    Integer duration;

    //If correo
    String verCorreo, verPass, verConPass;

    APIClient apiClient;
    APIService apiService;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        getWindow().setStatusBarColor(ContextCompat.getColor(this, R.color.orange));
        getWindow().setNavigationBarColor(ContextCompat.getColor(this, R.color.orange));

        btnRegister= findViewById(R.id.register_register_btn);
        etCorreo =  findViewById(R.id.register_email_editText);
        etPass = findViewById(R.id.register_pass_editText);
        etConPass = findViewById(R.id.register_confirmPass_editText);
        tvLogin = findViewById(R.id.register_login_textView);

        //Instancia de la api
        apiClient = APIClient.getInstance();
        apiClient.ApiClient();
        apiService = apiClient.getApiService();

        //Parametros para el toast
        textToast = "Te has registrado correctamente";
        duration = Toast.LENGTH_LONG;

        //Parametros para el if

        MessageDigest md = null;
        try {
            md = MessageDigest.getInstance("SHA-512");
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }

        //If Verificacion si esta vacio
        MessageDigest finalMd = md;
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
                }else if(!verCorreo.matches("[a-zA-Z0-9._-]+@[a-z]+.[a-z]+")){
                    etCorreo.setError("No es un formato valido de contraseña");
                    etCorreo.requestFocus();
                }else{

                    String hashPass =  hashPassword(verPass);

                    Intent i = new Intent(Register.this, PostRegister.class);
                    i.putExtra("correo", verCorreo);
                    i.putExtra("contra", hashPass);
                    startActivity(i);
                    finish();


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

    public String hashPassword(String pass) {
        MessageDigest md = null;
        try {
            md = MessageDigest.getInstance("MD5");
            md.update(pass.getBytes());

            byte[] resultByteArray = md.digest();
            StringBuilder sb = new StringBuilder();
            for (byte b : resultByteArray){
                sb.append(String.format("%02x", b));
            }
            return sb.toString();

        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }
}