package com.example.fitpal20;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.fitpal20.models.Usuario;
import com.example.fitpal20.retrofit.APIClient;
import com.example.fitpal20.retrofit.APIService;
import com.example.fitpal20.retrofit.respuestas.RespuestaUsuario;

import kotlinx.coroutines.selects.SelectUnbiasedKt;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PostRegister extends AppCompatActivity {

    EditText nombre, apellido, peso, altura, genero;
    Button save;

    APIClient apiClient;
    APIService apiService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_register);

        nombre = findViewById(R.id.evDatosNombre);
        apellido = findViewById(R.id.evDatosApellido);
        peso = findViewById(R.id.evDatosPeso);
        altura = findViewById(R.id.evDatosAltura);

        save = findViewById(R.id.guardarDatosBtn);

        //Instancia de la api
        apiClient = APIClient.getInstance();
        apiClient.ApiClient();
        apiService = apiClient.getApiService();

        String correo = getIntent().getStringExtra("correo");
        String passHashed = getIntent().getStringExtra("contra");

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(     nombre.getText().toString().isEmpty() ||
                        apellido.getText().toString().isEmpty() ||
                        peso.getText().toString().isEmpty()||
                        altura.getText().toString().isEmpty()){
                    Toast.makeText(PostRegister.this, "Porfavor rellena todos los campos", Toast.LENGTH_SHORT).show();
                }else{
                    String nombreTxt = nombre.getText().toString();
                    String apellidoTxt = apellido.getText().toString();
                    String pesoTxt = peso.getText().toString();
                    String alturaTxt = altura.getText().toString();
                    String generoTxt = "generoPrueba";

                    Usuario regUser = new Usuario(nombreTxt, Float.parseFloat(alturaTxt), Float.parseFloat(pesoTxt),generoTxt,0 , apellidoTxt, correo, passHashed);
                    RespuestaUsuario.getInstance().setUsuario(regUser);
                    registrarUsuario(apiService, regUser);

                }
            }
        });






    }

    public void registrarUsuario(APIService apiService, Usuario user){
        if(apiService != null){
            Call<Usuario> callRegUser = apiService.postUser(user);
            callRegUser.enqueue(new Callback<Usuario>() {
                @Override
                public void onResponse(Call<Usuario> call, Response<Usuario> response) {
                    if(response.isSuccessful()){
                        Usuario user = response.body();
                        Log.d("Response", user.getContraseña());
                        Toast.makeText(PostRegister.this, "Usuario Registrado", Toast.LENGTH_SHORT).show();
                        Intent i = new Intent(PostRegister.this, MainActivity.class);
                        startActivity(i);
                        finish();

                    }
                }

                @Override
                public void onFailure(Call<Usuario> call, Throwable t) {
                    Toast.makeText(PostRegister.this, "Hubo un error", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
}