package com.example.fitpal20;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.fitpal20.models.Usuario;
import com.example.fitpal20.retrofit.APIClient;
import com.example.fitpal20.retrofit.APIService;
import com.example.fitpal20.retrofit.respuestas.RespuestaUsuario;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProfileFrgment extends AppCompatActivity {
    public static class ProfileFragment extends Fragment {

        Button logoutbtn;
        ImageView btnEditName, btnEditEmail;

        EditText editName, editEmail;

        TextView name, email, acceptChangeName,acceptChangeEmail, titleTv;

        APIService apiService;
        APIClient apiClient = new APIClient();

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            // Inflate the layout for this fragment
            View view = inflater.inflate(R.layout.fragment_profile, container, false);
            logoutbtn = view.findViewById(R.id.btnCloseSes);
            btnEditName = view.findViewById(R.id.imgEditName);
            btnEditEmail = view.findViewById(R.id.imgEditEmail);
            titleTv = view.findViewById(R.id.tv_TitleNameProfile);
            name = view.findViewById(R.id.tv_nameProfile);
            email= view.findViewById(R.id.tv_emailProfile);

            editName = view.findViewById(R.id.editTextName);
            acceptChangeName = view.findViewById(R.id.acceptNameChange);

            editEmail = view.findViewById(R.id.editTextEmail);
            acceptChangeEmail = view.findViewById(R.id.acceptEmailChange);

            Usuario actualUsuario = RespuestaUsuario.getInstance().getUsuario();

            String title = actualUsuario.getNombre();
            Character inicialApellido = actualUsuario.getApellido().charAt(0);
            titleTv.setText(title + " " + inicialApellido.toString().toUpperCase() + ".");

            name.setText(actualUsuario.getNombre() + " " + actualUsuario.getApellido());
            email.setText(actualUsuario.getCorreo());


            editName.setVisibility(View.GONE);
            editEmail.setVisibility(View.GONE);
            acceptChangeName.setVisibility(View.GONE);
            acceptChangeEmail.setVisibility(View.GONE);


            //Instancia de la api
            apiClient = APIClient.getInstance();
            apiClient.ApiClient();
            apiService = apiClient.getApiService();


            btnEditName.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    editName.setText(name.getText().toString());
                    editName.setVisibility(View.VISIBLE);
                    acceptChangeName.setVisibility(View.VISIBLE);
                    name.setVisibility(View.GONE);
                    btnEditName.setVisibility(View.GONE);
                    editName.requestFocus();

                }
            });
            acceptChangeName.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(editName.getText().toString().isEmpty()){
                        Toast.makeText(view.getContext(), "Porfavor no deje el campo en blanco", Toast.LENGTH_SHORT).show();
                    }else{
                        String newName = "";
                        String newSurname = "";
                        try{
                            newName = editName.getText().toString().split(" ")[0];
                            newSurname = editName.getText().toString().split(" ")[1];
                            editName.setVisibility(View.GONE);
                            acceptChangeName.setVisibility(View.GONE);
                            name.setText(newName);
                            name.setVisibility(View.VISIBLE);
                            btnEditName.setVisibility(View.VISIBLE);

                            actualUsuario.setNombre(newName);
                            actualUsuario.setApellido(newSurname);
                            if(apiService != null){
                                Call<Usuario> update = apiService.updateUsuario(actualUsuario);
                                update.enqueue(new Callback<Usuario>() {
                                    @Override
                                    public void onResponse(Call<Usuario> call, Response<Usuario> response) {
                                        if(response.isSuccessful()){
                                            Toast.makeText(view.getContext(), "Usuario Actualizado", Toast.LENGTH_SHORT).show();
                                        }
                                    }

                                    @Override
                                    public void onFailure(Call<Usuario> call, Throwable t) {
                                        Toast.makeText(view.getContext(), "Error actualizando datos", Toast.LENGTH_SHORT).show();
                                    }
                                });
                            }
                        }catch(Exception e){
                            Toast.makeText(view.getContext(), "Porfavor introduza su nombre y apellido", Toast.LENGTH_SHORT).show();
                        }

                    }
                }
            });


            btnEditEmail.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    editEmail.setText(email.getText().toString());
                    editEmail.setVisibility(View.VISIBLE);
                    acceptChangeEmail.setVisibility(View.VISIBLE);
                    email.setVisibility(View.GONE);
                    btnEditEmail.setVisibility(View.GONE);
                    editEmail.requestFocus();
                }
            });
            acceptChangeEmail.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String newEmail = editEmail.getText().toString();
                    if(newEmail.isEmpty()){
                        Toast.makeText(view.getContext(), "Porfavor, no deje el campo en blanco", Toast.LENGTH_SHORT).show();
                    }else{
                        editEmail.setVisibility(View.GONE);
                        acceptChangeEmail.setVisibility(View.GONE);
                        email.setText(newEmail);
                        email.setVisibility(View.VISIBLE);
                        btnEditEmail.setVisibility(View.VISIBLE);

                        actualUsuario.setCorreo(newEmail);
                        if(apiService != null){
                            Call<Usuario> update = apiService.updateUsuario(actualUsuario);
                            update.enqueue(new Callback<Usuario>() {
                                @Override
                                public void onResponse(Call<Usuario> call, Response<Usuario> response) {
                                    if(response.isSuccessful()){
                                        Toast.makeText(view.getContext(), "Usuario Actualizado", Toast.LENGTH_SHORT).show();
                                    }
                                }

                                @Override
                                public void onFailure(Call<Usuario> call, Throwable t) {
                                    Toast.makeText(view.getContext(), "Error actualizando datos", Toast.LENGTH_SHORT).show();
                                }
                            });
                        }
                    }

                }
            });


            logoutbtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getContext(), Lobby.class);
                    startActivity(intent);
                }
            });

            return view;
        }



    }
}