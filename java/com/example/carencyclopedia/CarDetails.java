package com.example.carencyclopedia;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.carencyclopedia.Interface.IItemClickListener;
import com.example.carencyclopedia.Model.Car;
import com.example.carencyclopedia.Model.Time;
import com.example.carencyclopedia.ViewHolder.CarViewHolder;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.firebase.ui.database.SnapshotParser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

public class CarDetails extends AppCompatActivity {

    TextView car_name, car_year;
    TextView car_price, car_manufacturer, car_class;
    ImageView car_image;

    TextView style, engine, transmission, color, interiorColor, doorNb, seatNb, horsepower;
    TextView driveType, fuel, fuelTankCapacity, consumption, acceleration, cylinderNb, maxSpeed, weight;

    String carId = "";
    public String website;

    FirebaseDatabase database;
    DatabaseReference cars, timeData;

    Button description;

    String descr = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_car_details);

        description = findViewById(R.id.description);

        //Firebase
        database = FirebaseDatabase.getInstance();
        cars = database.getReference("Cars");
        timeData = database.getReference("Time");

        //Init View

        car_name = findViewById(R.id.car_name);
        car_year = findViewById(R.id.car_year);
        car_image = findViewById(R.id.car_image);

        car_price = findViewById(R.id.car_price_detail);
        car_manufacturer = findViewById(R.id.car_manufacturer_detail);
        car_class = findViewById(R.id.car_class_detail);
        style = findViewById(R.id.car_style_detail);
        engine = findViewById(R.id.car_engine_detail);
        transmission = findViewById(R.id.car_transmission_detail);
        color = findViewById(R.id.car_color_detail);
        interiorColor = findViewById(R.id.car_interior_color_detail);
        doorNb = findViewById(R.id.car_door_number_detail);
        seatNb = findViewById(R.id.car_seat_number_detail);
        horsepower = findViewById(R.id.car_horsepower_detail);
        driveType = findViewById(R.id.car_drive_type_detail);
        fuel = findViewById(R.id.car_fuel_detail);
        fuelTankCapacity = findViewById(R.id.car_fuel_tank_capacity_detail);
        consumption = findViewById(R.id.car_consumption_detail);
        acceleration = findViewById(R.id.car_acceleration_detail);
        cylinderNb = findViewById(R.id.car_cylinder_number_detail);
        maxSpeed = findViewById(R.id.car_max_speed_detail);
        weight = findViewById(R.id.car_weight_detail);

        //Get food from Intent
        if (getIntent() != null)
            carId = getIntent().getStringExtra("CarId");
        if (!carId.isEmpty()) {
            getCarDetails(carId);
        }

        description.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Intent intent = new Intent(v.getContext(), PopupDescription.class);
                intent.putExtra("CarTxt", descr);
                startActivity(intent);

            }
        });
    }

    private void getCarDetails(String carId) {
        cars.child(carId).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Car car = dataSnapshot.getValue(Car.class);

                Picasso.get().load(car.getImage()).into(car_image);

                website = car.getWebsite();
                descr = car.getDescription();

                car_class.setText(car.getCarClass());
                car_manufacturer.setText(car.getManufacturer());
                car_name.setText(car.getName());
                car_price.setText(car.getPrice());
                style.setText(car.getStyle());
                engine.setText(car.getEngine());
                transmission.setText(car.getTransmission());
                color.setText(car.getColor());
                interiorColor.setText(car.getInteriorColor());
                doorNb.setText(car.getDoorNb());
                seatNb.setText(car.getSeatNb());
                horsepower.setText(car.getHorsepower());
                driveType.setText(car.getDriveType());
                fuel.setText(car.getFuel());
                fuelTankCapacity.setText(car.getFuelTankCapacity());
                consumption.setText(car.getConsumption());
                acceleration.setText(car.getAcceleration());
                cylinderNb.setText(car.getCylinderNb());
                maxSpeed.setText(car.getMaxSpeed());
                weight.setText(car.getWeight());


                timeData.child(car.getTimeId()).addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                        Time time = dataSnapshot.getValue(Time.class);
                        String time = dataSnapshot.child("year").getValue(String.class);
                        car_year.setText(time);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    public void site(View view) {
        Intent siteIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(website));
        startActivity(siteIntent);
    }


}
