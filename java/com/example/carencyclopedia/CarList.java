package com.example.carencyclopedia;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.carencyclopedia.Adapter.MyAdapter;
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

public class CarList extends AppCompatActivity {

    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;

    FirebaseDatabase database;
    DatabaseReference carList;

    FirebaseRecyclerAdapter<Car, CarViewHolder> adapter;

    String timeId = "";

    TextView chosen_year;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_car_list);

        //Firebase
        database = FirebaseDatabase.getInstance();
        carList = database.getReference("Cars");

        recyclerView = findViewById(R.id.recycler_view_car_list);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        chosen_year = findViewById(R.id.chosen_year);

        if (getIntent() != null) {
            timeId = getIntent().getStringExtra("TimeId");
        }

        if (!timeId.isEmpty() && timeId != null) {
            getTime(timeId);
            loadCars(timeId);
        }

    }

    //get chosen year and display it instead of the logo
    private void getTime(String timeId) {

        database.getReference("Time").orderByChild("id").equalTo(timeId).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot userSnapshot : snapshot.getChildren()) {
                    Time time = userSnapshot.getValue(Time.class);
                    chosen_year.setText(time.getYear());
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    private void loadCars(String timeId) {


        Query query = FirebaseDatabase.getInstance().getReference()
                .child("Cars")
                .orderByChild("TimeId").equalTo(timeId);

        FirebaseRecyclerOptions<Car> options =
                new FirebaseRecyclerOptions.Builder<Car>()
                        .setQuery(query, new SnapshotParser<Car>() {
                            @NonNull
                            @Override
                            public Car parseSnapshot(@NonNull DataSnapshot snapshot) {
                                return new Car(snapshot.child("CarClass").getValue().toString(),
                                        snapshot.child("Id").getValue().toString(),
                                        snapshot.child("Image").getValue().toString(),
                                        snapshot.child("Manufacturer").getValue().toString(),
                                        snapshot.child("Name").getValue().toString(),
                                        snapshot.child("Price").getValue().toString(),
                                        snapshot.child("TimeId").getValue().toString(),
                                        snapshot.child("Website").getValue().toString(),
                                        snapshot.child("Style").getValue().toString(),
                                        snapshot.child("Engine").getValue().toString(),
                                        snapshot.child("Transmission").getValue().toString(),
                                        snapshot.child("Color").getValue().toString(),
                                        snapshot.child("InteriorColor").getValue().toString(),
                                        snapshot.child("DoorNb").getValue().toString(),
                                        snapshot.child("SeatNb").getValue().toString(),
                                        snapshot.child("Horsepower").getValue().toString(),
                                        snapshot.child("Description").getValue().toString(),
                                        snapshot.child("DriveType").getValue().toString(),
                                        snapshot.child("Fuel").getValue().toString(),
                                        snapshot.child("FuelTankCapacity").getValue().toString(),
                                        snapshot.child("Consumption").getValue().toString(),
                                        snapshot.child("Acceleration").getValue().toString(),
                                        snapshot.child("CylinderNb").getValue().toString(),
                                        snapshot.child("MaxSpeed").getValue().toString(),
                                        snapshot.child("Weight").getValue().toString());
                            }
                        })
                        .build();
        adapter = new FirebaseRecyclerAdapter<Car, CarViewHolder>(options) {


            @NonNull
            @Override
            public CarViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.car_item, parent, false);
                return new CarViewHolder(itemView);
            }

            @Override
            protected void onBindViewHolder(@NonNull CarViewHolder carViewHolder, int position, @NonNull Car car) {

                carViewHolder.car_manufacturer.setText(car.getManufacturer());
                carViewHolder.car_name.setText(car.getName());
                Picasso.get().load(car.getImage()).into(carViewHolder.car_image);

                final Car local = car;
                carViewHolder.setItemClickListener(new IItemClickListener() {

                    @Override
                    public void onClick(View view, int position) {
                        Intent carDetails = new Intent(CarList.this, CarDetails.class);
                        carDetails.putExtra("CarId", adapter.getRef(position).getKey());
                        startActivity(carDetails);
                    }
                });
            }
        };
        recyclerView.setAdapter(adapter);
    }

    @Override
    protected void onStart() {
        super.onStart();
        adapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        adapter.stopListening();
    }

}
