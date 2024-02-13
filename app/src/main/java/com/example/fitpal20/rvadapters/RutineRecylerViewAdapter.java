package com.example.fitpal20.rvadapters;

import android.content.Context;
import android.graphics.Color;
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
import com.example.fitpal20.models.RutineModel;

import java.util.ArrayList;

public class RutineRecylerViewAdapter extends RecyclerView.Adapter<RutineRecylerViewAdapter.MyViewHolder> {
    Context context;
    ArrayList<RutineModel> RutineModels;
    RutineModel rutineSelect;

    int posicionMarcada = 0;

    public RutineRecylerViewAdapter(Context context, ArrayList<RutineModel> RutineModels){
        this.RutineModels = RutineModels;
        this.context = context;

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
        holder.tvDayList.setText(RutineModels.get(position).getDays());

        final int pos = position;
        holder.llCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                posicionMarcada = pos;
                notifyDataSetChanged();
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
            tvDayList = itemView.findViewById(R.id.tv_rutineDayList);

            llCheck = itemView.findViewById(R.id.ll_check);
            imageCheck = itemView.findViewById(R.id.imageCheck);


            cvRutine = itemView.findViewById(R.id.cardview);
        }
    }
}
