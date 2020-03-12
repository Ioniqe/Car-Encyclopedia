package com.example.carencyclopedia;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.carencyclopedia.Adapter.SearchAdapter;
import com.example.carencyclopedia.Model.Car;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


public class CarSearchActivity extends AppCompatActivity {

    private String searchedText;
    private String category;

    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;

    FirebaseDatabase database;
    DatabaseReference carList;

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

    SearchAdapter searchAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_car_search);

        //Firebase
        database = FirebaseDatabase.getInstance();
        carList = database.getReference("Cars");

        recyclerView = findViewById(R.id.recycler_view_car_list);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        manufacturerList = new ArrayList<>();
        nameList = new ArrayList<>();
        idList = new ArrayList<>();
        imageList = new ArrayList<>();

        styleList = new ArrayList<>();
        engineList = new ArrayList<>();
        transmissionList = new ArrayList<>();
        colorList = new ArrayList<>();
        interiorColorList = new ArrayList<>();
        horsepowerList = new ArrayList<>();
        driveTypeList = new ArrayList<>();
        fuelList = new ArrayList<>();
        fuelTankCapacityList = new ArrayList<>();
        doorNbList = new ArrayList<>();
        seatNbList = new ArrayList<>();
        consumptionList = new ArrayList<>();
        accelerationList = new ArrayList<>();
        cylinderNbList = new ArrayList<>();
        maxSpeedList = new ArrayList<>();
        weightList = new ArrayList<>();

        if (getIntent() != null) {
            searchedText = getIntent().getStringExtra("searchedText");
            category = getIntent().getStringExtra("category"); //Manufacturer or Name
        }
        if (!searchedText.isEmpty() && !category.isEmpty() && searchedText != null && category != null) {
            setAdapter(searchedText);
        }

    }

    private void setAdapter(final String searchedText) {
        manufacturerList.clear();
        nameList.clear();
        idList.clear();
        imageList.clear();
        styleList.clear();
        engineList.clear();
        transmissionList.clear();
        colorList.clear();
        interiorColorList.clear();
        horsepowerList.clear();
        driveTypeList.clear();
        fuelList.clear();
        fuelTankCapacityList.clear();
        doorNbList.clear();
        seatNbList.clear();
        consumptionList.clear();
        accelerationList.clear();
        cylinderNbList.clear();
        maxSpeedList.clear();
        weightList.clear();

        database.getReference().child("Cars").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                int counter = 0;
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {

                    Car car = snapshot.getValue(Car.class);

                    if (car != null && (car.getManufacturer().toLowerCase().contains(searchedText.toLowerCase())
                            || car.getName().toLowerCase().contains(searchedText.toLowerCase())
                            || car.getStyle().toLowerCase().contains(searchedText.toLowerCase())
                            || car.getEngine().toLowerCase().contains(searchedText.toLowerCase())
                            || car.getTransmission().toLowerCase().contains(searchedText.toLowerCase())
                            || car.getColor().toLowerCase().contains(searchedText.toLowerCase())
                            || car.getInteriorColor().toLowerCase().contains(searchedText.toLowerCase())
                            || car.getHorsepower().toLowerCase().contains(searchedText.toLowerCase())
                            || car.getDriveType().toLowerCase().contains(searchedText.toLowerCase())
                            || car.getFuel().toLowerCase().contains(searchedText.toLowerCase())
                            || car.getFuelTankCapacity().toLowerCase().contains(searchedText.toLowerCase())
                            || car.getDoorNb().toLowerCase().contains(searchedText.toLowerCase())
                            || car.getSeatNb().toLowerCase().contains(searchedText.toLowerCase())
                            || car.getConsumption().toLowerCase().contains(searchedText.toLowerCase())
                            || car.getAcceleration().toLowerCase().contains(searchedText.toLowerCase())
                            || car.getCylinderNb().toLowerCase().contains(searchedText.toLowerCase())
                            || car.getMaxSpeed().toLowerCase().contains(searchedText.toLowerCase())
                            || car.getWeight().toLowerCase().contains(searchedText.toLowerCase()))) {

                        manufacturerList.add(car.getManufacturer());
                        nameList.add(car.getName());
                        idList.add(car.getId());
                        imageList.add(car.getImage());
                        styleList.add(car.getStyle());
                        engineList.add(car.getEngine());
                        transmissionList.add(car.getTransmission());
                        colorList.add(car.getColor());
                        interiorColorList.add(car.getInteriorColor());
                        horsepowerList.add(car.getHorsepower());
                        driveTypeList.add(car.getDriveType());
                        fuelList.add(car.getFuel());
                        fuelTankCapacityList.add(car.getFuelTankCapacity());
                        doorNbList.add(car.getDoorNb());
                        seatNbList.add(car.getSeatNb());
                        consumptionList.add(car.getConsumption());
                        accelerationList.add(car.getAcceleration());
                        cylinderNbList.add(car.getCylinderNb());
                        maxSpeedList.add(car.getMaxSpeed());
                        weightList.add(car.getWeight());

                        counter++;
                    }

                    if (counter == 15)
                        break;
                }

                searchAdapter = new SearchAdapter(CarSearchActivity.this, manufacturerList, nameList, idList, imageList, styleList, engineList, transmissionList, colorList, interiorColorList, horsepowerList, driveTypeList,
                        fuelList, fuelTankCapacityList, doorNbList, seatNbList, consumptionList, accelerationList, cylinderNbList, maxSpeedList, weightList);
                recyclerView.setAdapter(searchAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }


}
