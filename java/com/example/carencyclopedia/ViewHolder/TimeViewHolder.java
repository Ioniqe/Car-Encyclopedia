package com.example.carencyclopedia.ViewHolder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.carencyclopedia.Interface.IItemClickListener;
import com.example.carencyclopedia.R;

public class TimeViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    public TextView car_year;
    public ImageView car_image;

    //changed from the original "ItemClickListener" => https://www.youtube.com/watch?v=dJm7LACOn80&list=PLaoF-xhnnrRW4lXuIhNLhgVuYkIlF852V&index=2   24:31
    private IItemClickListener itemClickListener;

    public TimeViewHolder(@NonNull View itemView) {
        super(itemView);

        car_year = itemView.findViewById(R.id.car_name);
        car_image = itemView.findViewById(R.id.car_image);

        itemView.setOnClickListener(this);
    }

    public void setItemClickListener(IItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    @Override
    public void onClick(View v) {
        itemClickListener.onClick(v, getAdapterPosition());
    }
}
