package com.example.fitpal20;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.fitpal20.models.Usuario;
import com.example.fitpal20.retrofit.APIClient;
import com.example.fitpal20.retrofit.APIService;
import com.example.fitpal20.retrofit.respuestas.RespuestaUsuario;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ChangePassword extends AppCompatActivity {

    ImageView atras;

    EditText passAct, nuevaPass, confirmarPass;

    Button btnSave;

    APIService apiService;
    APIClient apiClient = new APIClient();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);

        atras = findViewById(R.id.btnAtras);
        passAct = findViewById(R.id.contraActual);
        nuevaPass = findViewById(R.id.contraNueva);
        confirmarPass = findViewById(R.id.contraConfirmar);

        btnSave = findViewById(R.id.btnSavePassword);

        //Instancia de la api
        apiClient = APIClient.getInstance();
        apiClient.ApiClient();
        apiService = apiClient.getApiService();

        atras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(ChangePassword.this, MainActivity.class);
                i.putExtra("profile", "yes");
                startActivity(i);
                finish();
            }
        });

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(passAct.getText().toString().isEmpty() ||
                        nuevaPass.getText().toString().isEmpty() ||
                        confirmarPass.getText().toString().isEmpty()){
                    Toast.makeText(ChangePassword.this, "Porfavor, rellene todos los campos", Toast.LENGTH_SHORT).show();
                }else{
                    String passActualTxt = passAct.getText().toString();
                    String nuevaPassTxt = nuevaPass.getText().toString();
                    String confirmarPassTxt = confirmarPass.getText().toString();

                    passActualTxt = hashPassword(passActualTxt);

                    if(passActualTxt.equals(RespuestaUsuario.getInstance().getUsuario().getContraseña())){
                        if(!nuevaPassTxt.equals(confirmarPassTxt)){
                            confirmarPass.setError("Las contraseñas no coinciden");
                            confirmarPass.requestFocus();
                        }else{
                            String passHashed = hashPassword(nuevaPassTxt);
                            RespuestaUsuario.getInstance().getUsuario().setContraseña(passHashed);

                            if(apiService != null){
                                Call<Usuario> update = apiService.updateUsuario(RespuestaUsuario.getInstance().getUsuario());
                                update.enqueue(new Callback<Usuario>() {
                                    @Override
                                    public void onResponse(Call<Usuario> call, Response<Usuario> response) {
                                        Toast.makeText(ChangePassword.this, "Contraseña actualizada", Toast.LENGTH_SHORT).show();
                                        Intent i = new Intent(ChangePassword.this, Login.class);
                                        startActivity(i);
                                        finish();
                                    }

                                    @Override
                                    public void onFailure(Call<Usuario> call, Throwable t) {

                                    }
                                });
                            }

                        }
                    }else{
                        passAct.setError("Tu contraseña actual no es correcta");
                        passAct.requestFocus();
                    }




                }
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