package com.example.carencyclopedia.ViewHolder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.carencyclopedia.Interface.IItemClickListener;
import com.example.carencyclopedia.R;

public class CarViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    public TextView car_name, car_manufacturer;
    public ImageView car_image;

    //changed from the original "ItemClickListener" => https://www.youtube.com/watch?v=dJm7LACOn80&list=PLaoF-xhnnrRW4lXuIhNLhgVuYkIlF852V&index=2   24:31
    private IItemClickListener itemClickListener;

    public void setItemClickListener(IItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    public CarViewHolder(@NonNull View itemView) {
        super(itemView);

        car_manufacturer = itemView.findViewById(R.id.car_manufacturer);
        car_name = itemView.findViewById(R.id.car_name);
        car_image = itemView.findViewById(R.id.car_image);

        itemView.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        itemClickListener.onClick(v, getAdapterPosition());
    }
}
