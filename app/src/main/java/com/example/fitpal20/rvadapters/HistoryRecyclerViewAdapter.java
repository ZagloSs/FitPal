package com.example.fitpal20.rvadapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fitpal20.R;
import com.example.fitpal20.models.HistoryModel;
import com.example.fitpal20.models.RutineModel;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class HistoryRecyclerViewAdapter extends RecyclerView.Adapter<HistoryRecyclerViewAdapter.MyViewHolder> {

    Context context;
    ArrayList<HistoryModel> HistoryModels;

    public HistoryRecyclerViewAdapter(Context context, ArrayList<HistoryModel> HistoryModels) {
        this.context = context;
        this.HistoryModels = HistoryModels;
    }

    @NonNull
    @Override
    public HistoryRecyclerViewAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.cv_history, parent, false);
        return new HistoryRecyclerViewAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HistoryRecyclerViewAdapter.MyViewHolder holder, int position) {
        holder.tvName.setText(HistoryModels.get(position).getExName());
        holder.tvSets.setText("S: " + String.valueOf(HistoryModels.get(position).getSets()));
        holder.tvReps.setText("R: " + String.valueOf(HistoryModels.get(position).getReps()));
        holder.tvKg.setText("KG: " + String.valueOf(HistoryModels.get(position).getKg()));
    }

    @Override
    public int getItemCount() {
        return HistoryModels.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        TextView tvName, tvSets, tvReps, tvKg;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tv_nameEx);
            tvName = itemView.findViewById(R.id.tv_nameEx);
            tvName = itemView.findViewById(R.id.tv_nameEx);
            tvName = itemView.findViewById(R.id.tv_nameEx);
            tvSets = itemView.findViewById(R.id.tv_sets);
            tvReps = itemView.findViewById(R.id.tv_reps);
            tvKg = itemView.findViewById(R.id.tv_peso);

        }
    }
}
