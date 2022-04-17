package com.example.carinformationdashboard;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder>{
    private Context context;
    private Activity activity;
    private ArrayList car_id, car_marka, car_modl, car_regnom, car_danak, car_zatrahovka, car_pregled, car_vinetka;

    Animation translate_anim;

    CustomAdapter(Activity activity, Context context, ArrayList car_id, ArrayList car_marka, ArrayList car_modl,
                  ArrayList car_regnom, ArrayList car_danak, ArrayList car_zatrahovka, ArrayList car_pregled, ArrayList car_vinetka){
            this.activity = activity;
            this.context = context;
            this.car_id = car_id;
            this.car_marka = car_marka;
            this.car_modl = car_modl;
            this.car_regnom = car_regnom;
            this.car_danak = car_danak;
            this.car_zatrahovka = car_zatrahovka;
            this.car_pregled = car_pregled;
            this.car_vinetka = car_vinetka;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.my_row, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull  MyViewHolder holder, int position) {
        holder.car_id_txt.setText(String.valueOf(car_id.get(position)));
        holder.car_marka_txt.setText(String.valueOf(car_marka.get(position)));
        holder.car_modl_txt.setText(String.valueOf(car_modl.get(position)));
        holder.car_regnom_txt.setText(String.valueOf(car_regnom.get(position)));
        holder.mainLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, UpdateActivity.class);
                intent.putExtra("id", String.valueOf(car_id.get(holder.getAbsoluteAdapterPosition())));
                intent.putExtra("marka", String.valueOf(car_marka.get(holder.getAbsoluteAdapterPosition())));
                intent.putExtra("modl", String.valueOf(car_modl.get(holder.getAbsoluteAdapterPosition())));
                intent.putExtra("regnom", String.valueOf(car_regnom.get(holder.getAbsoluteAdapterPosition())));
                intent.putExtra("danak", String.valueOf(car_danak.get(holder.getAbsoluteAdapterPosition())));
                intent.putExtra("zastrahovka", String.valueOf(car_zatrahovka.get(holder.getAbsoluteAdapterPosition())));
                intent.putExtra("pregled", String.valueOf(car_pregled.get(holder.getAbsoluteAdapterPosition())));
                intent.putExtra("vinetka", String.valueOf(car_vinetka.get(holder.getAbsoluteAdapterPosition())));
                activity.startActivityForResult(intent, 1);
            }
        });
    }

    @Override
    public int getItemCount() {

        return car_id.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        TextView car_id_txt, car_marka_txt, car_modl_txt, car_regnom_txt;
        LinearLayout mainLayout;

        MyViewHolder(@NonNull View itemView) {
            super(itemView);
            car_id_txt = itemView.findViewById(R.id.car_id_txt);
            car_marka_txt = itemView.findViewById(R.id.car_marka_txt);
            car_modl_txt = itemView.findViewById(R.id.car_modl_txt);
            car_regnom_txt = itemView.findViewById(R.id.car_regnom_txt);
            mainLayout = itemView.findViewById(R.id.mainLayout);
            Animation translate_anim = AnimationUtils.loadAnimation(context, R.anim.translate_anim);
            mainLayout.setAnimation(translate_anim);
        }

    }

}
