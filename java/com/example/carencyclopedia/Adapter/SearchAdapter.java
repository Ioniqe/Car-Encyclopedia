package com.example.carencyclopedia.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.carencyclopedia.CarDetails;
import com.example.carencyclopedia.Interface.IItemClickListener;
import com.example.carencyclopedia.R;
import com.example.carencyclopedia.ViewHolder.CarViewHolder;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class SearchAdapter extends RecyclerView.Adapter<CarViewHolder> {

    Context context;

    ArrayList<String> nameList;
    ArrayList<String> manufacturerList;
    ArrayList<String> idList;
    ArrayList<String> imageList;

    ArrayList<String> styleList;
    ArrayList<String> engineList;
    ArrayList<String> transmissionList;
    ArrayList<String> colorList;
    ArrayList<String> interiorColorList;
    ArrayList<String> horsepowerList;
    ArrayList<String> driveTypeList;
    ArrayList<String> fuelList;
    ArrayList<String> fuelTankCapacityList;
    ArrayList<String> doorNbList;
    ArrayList<String> seatNbList;
    ArrayList<String> consumptionList;
    ArrayList<String> accelerationList;
    ArrayList<String> cylinderNbList;
    ArrayList<String> maxSpeedList;
    ArrayList<String> weightList;


    public SearchAdapter(Context context, ArrayList<String> nameList, ArrayList<String> manufacturerList, ArrayList<String> idList, ArrayList<String> imageList,
                         ArrayList<String> styleList, ArrayList<String> engineList, ArrayList<String> transmissionList, ArrayList<String> colorList,
                         ArrayList<String> interiorColorList, ArrayList<String> horsepowerList, ArrayList<String> driveTypeList, ArrayList<String> fuelList,
                         ArrayList<String> fuelTankCapacityList, ArrayList<String> doorNbList, ArrayList<String> seatNbList, ArrayList<String> consumptionList,
                         ArrayList<String> accelerationList, ArrayList<String> cylinderNbList, ArrayList<String> maxSpeedList, ArrayList<String> weightList) {
        this.context = context;
        this.nameList = nameList;
        this.manufacturerList = manufacturerList;
        this.idList = idList;
        this.imageList = imageList;
        this.styleList = styleList;
        this.engineList = engineList;
        this.transmissionList = transmissionList;
        this.colorList = colorList;
        this.interiorColorList = interiorColorList;
        this.horsepowerList = horsepowerList;
        this.driveTypeList = driveTypeList;
        this.fuelList = fuelList;
        this.fuelTankCapacityList = fuelTankCapacityList;
        this.doorNbList = doorNbList;
        this.seatNbList = seatNbList;
        this.consumptionList = consumptionList;
        this.accelerationList = accelerationList;
        this.cylinderNbList = cylinderNbList;
        this.maxSpeedList = maxSpeedList;
        this.weightList = weightList;
    }

    @NonNull
    @Override
    public CarViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.car_search_layout, parent, false);
        return new CarViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull CarViewHolder carViewHolder, int position) {
        carViewHolder.car_manufacturer.setText(manufacturerList.get(position));
        carViewHolder.car_name.setText(nameList.get(position));
        Picasso.get().load(imageList.get(position)).into(carViewHolder.car_image);

        final String pos = idList.get(position);
//        final Car local = car;
        carViewHolder.setItemClickListener(new IItemClickListener() {

            @Override
            public void onClick(View view, int position) {
                Intent carDetails;
                carDetails = new Intent(view.getContext(), CarDetails.class);
                carDetails.putExtra("CarId", pos);
                context.startActivity(carDetails);
            }
        });
    }


    @Override
    public int getItemCount() {
        return manufacturerList.size();
    }
}
