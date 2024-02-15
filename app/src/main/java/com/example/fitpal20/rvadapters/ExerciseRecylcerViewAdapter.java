package com.example.fitpal20.rvadapters;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Interpolator;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fitpal20.R;
import com.example.fitpal20.models.ExerciseModel;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class ExerciseRecylcerViewAdapter extends RecyclerView.Adapter<ExerciseRecylcerViewAdapter.MyViewHolder>{

    Context context;
    ArrayList<ExerciseModel> ExerciseModels;

    ArrayList<Integer> posicionesMarcadas = new ArrayList<>();


    public ExerciseRecylcerViewAdapter(Context context, ArrayList<ExerciseModel> exerciseModels) {
        this.context = context;
        this.ExerciseModels = exerciseModels;
    }

    public void setFilteredList(ArrayList<ExerciseModel> filteredList){
        this.ExerciseModels = filteredList;
        notifyDataSetChanged();
    }


    @NonNull
    @Override
    public ExerciseRecylcerViewAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.cv_exercise, parent, false);
        return new ExerciseRecylcerViewAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ExerciseRecylcerViewAdapter.MyViewHolder holder, int position) {
        holder.tvName.setText(ExerciseModels.get(position).getName());
        holder.ivEx.setImageBitmap(transformImage(ExerciseModels.get(position).getBase64ImgExercise()));

        final int pos = position;
        holder.exercises.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            if(!posicionesMarcadas.contains(pos)){
                posicionesMarcadas.add(pos);
                holder.exercises.getBackground().setTint(Color.parseColor("#FFA903"));
                notifyDataSetChanged();
            }else if(posicionesMarcadas.contains(pos)){
                posicionesMarcadas.remove(posicionesMarcadas.indexOf(pos));
                holder.exercises.getBackground().setTint(Color.parseColor("#464646"));
                notifyDataSetChanged();
            }
            }
        });
    }

    @Override
    public int getItemCount() {
        return ExerciseModels.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{
        TextView tvName;
        CardView exercises;
        ImageView ivEx;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tv_exercisename);
            ivEx = itemView.findViewById(R.id.iv_exerciseImage);
            exercises = itemView.findViewById(R.id.cardViewExercise);


        }
    }

    public Bitmap transformImage(String base64){
        byte[] decodedString = Base64.decode(base64, Base64.DEFAULT);
        Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
        Bitmap scaled = Bitmap.createScaledBitmap(decodedByte, 100, 100, true);
        return scaled;
    }


}

