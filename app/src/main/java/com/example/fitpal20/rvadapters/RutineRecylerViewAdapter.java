package com.example.fitpal20.rvadapters;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fitpal20.R;
import com.example.fitpal20.models.RutineModel;

import java.util.ArrayList;

public class RutineRecylerViewAdapter extends RecyclerView.Adapter<RutineRecylerViewAdapter.MyViewHolder> {
    Context context;
    ArrayList<RutineModel> RutineModels;

    public RutineRecylerViewAdapter(Context context, ArrayList<RutineModel> RutineModels){
        this.RutineModels = RutineModels;
        this.context = context;

    }

    @NonNull
    @Override
    public RutineRecylerViewAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RutineRecylerViewAdapter.MyViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return RutineModels.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{
        TextView tvName, tvDayList;
        CardView cvRutine;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tv_rutineName);
            tvDayList = itemView.findViewById(R.id.tv_rutineDayList);
            cvRutine = itemView.findViewById(R.id.cardview);
        }
    }
}
