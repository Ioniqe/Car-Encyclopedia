package com.example.carencyclopedia.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.carencyclopedia.CarList;
import com.example.carencyclopedia.Interface.IItemClickListener;
import com.example.carencyclopedia.Model.Time;
import com.example.carencyclopedia.R;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    List<Time> timeList;
    Context context;

    public MyAdapter(Context context) {
        this.timeList = new ArrayList<>();
        this.context = context;
    }

    public void addAll(List<Time> newTimeList){
        int initSize = timeList.size();
        timeList.addAll(newTimeList);
        notifyItemRangeChanged(initSize, newTimeList.size());
    }

    public void removeLastItem(){
        timeList.remove(timeList.size() - 1);
    }

    public String getLastItemId(){
        return timeList.get(timeList.size() - 1).getId();
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(context).inflate(R.layout.time_layout, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.txt_year.setText(timeList.get(position).getYear());
        Picasso.get().load(timeList.get(position).getImage()).into(holder.imageView);

        holder.setiItemClickListener(new IItemClickListener(){

           @Override
           public void onClick(View view, int position){
               Intent intent = new Intent(context, CarList.class);
               intent.putExtra("TimeId", timeList.get(position).getId());
               context.startActivity(intent);
           }
        });
    }

    @Override
    public int getItemCount() {
        return timeList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView txt_year;
        ImageView imageView;

        IItemClickListener iItemClickListener;
        public void setiItemClickListener(IItemClickListener iItemClickListener){
            this.iItemClickListener = iItemClickListener;
        }


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            txt_year = itemView.findViewById(R.id.year);
            imageView = itemView.findViewById(R.id.car_image);
            itemView.setOnClickListener(this);

        }

        @Override
        public void onClick(View v) {
            iItemClickListener.onClick(v, getAdapterPosition());
        }
    }


}
