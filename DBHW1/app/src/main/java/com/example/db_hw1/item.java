package com.example.db_hw1;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class item extends RecyclerView.Adapter<item.MyViewHolder> {
    Context context;
    ArrayList id , name , six , base , tota , rate;
    RecyclerView mRecyclerView;

    item(Context context , ArrayList id , ArrayList name ,ArrayList six , ArrayList base , ArrayList tota , ArrayList rate){
        this.context = context;
        this.id= id;
        this.name=name;
        this.six= six;
        this.base=base;
        this.tota=tota;
        this.rate=rate;
    }

    @NonNull
    @Override
    public item.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.llyy, parent , false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.eid.setText(String.valueOf(id.get(position)));
        holder.ename.setText(String.valueOf(name.get(position)));
        holder.esix.setText(String.valueOf(six.get(position)));
        holder.ebase.setText(String.valueOf(base.get(position)));
        holder.etotal.setText(String.valueOf(tota.get(position)));
        holder.erate.setText(String.valueOf(rate.get(position)));

    }



    @Override
    public int getItemCount() {
        return id.size();
    }



    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView eid , ename , esix , ebase , etotal , erate;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            eid = itemView.findViewById(R.id.eid);
            ename =  itemView.findViewById(R.id.ename);
            esix =  itemView.findViewById(R.id.esix);
            ebase =  itemView.findViewById(R.id.ebase);
            etotal =  itemView.findViewById(R.id.etotal);
            erate =  itemView.findViewById(R.id.erate);



        }
    }

}