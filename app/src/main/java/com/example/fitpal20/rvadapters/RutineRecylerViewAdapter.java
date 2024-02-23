package com.example.fitpal20.rvadapters;

import android.content.Context;
import android.graphics.Color;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fitpal20.R;
import com.example.fitpal20.models.RutinaEjercicioModel;
import com.example.fitpal20.models.RutineModel;
import com.example.fitpal20.models.Usuario;
import com.example.fitpal20.retrofit.APIClient;
import com.example.fitpal20.retrofit.APIService;
import com.example.fitpal20.retrofit.respuestas.RespuestaUsuario;
import com.example.fitpal20.retrofit.respuestas.RutineExercisesSelected;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RutineRecylerViewAdapter extends RecyclerView.Adapter<RutineRecylerViewAdapter.MyViewHolder> {
    Context context;
    ArrayList<RutineModel> RutineModels;
    RutineModel rutineSelect;

    APIService apiService;
    APIClient apiClient = new APIClient();

    int posicionMarcada = 0;

    public RutineRecylerViewAdapter(Context context, ArrayList<RutineModel> RutineModels){
        this.RutineModels = RutineModels;
        this.context = context;

    }

    public void updateAdapter(List<RutineModel> listaUpdated){
        RutineModels.clear();
        RutineModels.addAll(listaUpdated);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public RutineRecylerViewAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view =inflater.inflate(R.layout.cv_rutine, parent, false);
        return new RutineRecylerViewAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RutineRecylerViewAdapter.MyViewHolder holder, int position) {
        holder.tvName.setText(RutineModels.get(position).getRutineName());

        //Instancia de la api
        apiClient = APIClient.getInstance();
        apiClient.ApiClient();
        apiService = apiClient.getApiService();
        int rutina_actual = RespuestaUsuario.getInstance().getUsuario().getRutina_actual();


        Usuario usuarioActual = RespuestaUsuario.getInstance().getUsuario();
        posicionMarcada = rutina_actual;
        

        final int pos = position;
        holder.llCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                posicionMarcada = pos;
                notifyDataSetChanged();
                Usuario updatedUser = RespuestaUsuario.getInstance().getUsuario();
                updatedUser.setRutina_actual(pos);
                if(apiService != null){
                    Call<Usuario> updateRutine = apiService.updateUsuario(updatedUser);
                    updateRutine.enqueue(new Callback<Usuario>() {
                        @Override
                        public void onResponse(Call<Usuario> call, Response<Usuario> response) {
                            if(response.isSuccessful()) {
                                Usuario userUpdated = response.body();
                                Log.d("Rutina", "Rutina Actualizada");

                            }
                        }

                        @Override
                        public void onFailure(Call<Usuario> call, Throwable t) {

                        }
                    });
                }
            }
        });

        if(posicionMarcada == position){
            holder.llCheck.getBackground().setTint(Color.parseColor("#FFA903"));
        }else{
            holder.llCheck.getBackground().setTint(Color.parseColor("#2F2F2F"));
        }
    }

    @Override
    public int getItemCount() {
        return RutineModels.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{
        TextView tvName, tvDayList;
        LinearLayout llCheck;
        ImageView imageCheck;
        CardView cvRutine;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tv_rutineName);

            llCheck = itemView.findViewById(R.id.ll_check);
            imageCheck = itemView.findViewById(R.id.imageCheck);


            cvRutine = itemView.findViewById(R.id.cardview);
        }
    }
}
