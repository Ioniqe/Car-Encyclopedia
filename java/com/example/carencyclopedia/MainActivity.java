package com.example.carencyclopedia;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.carencyclopedia.Adapter.MyAdapter;
import com.example.carencyclopedia.Model.Time;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

/**
 * This is the class that starts the application
 */

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView; //this is the thing that makes the window scrollable

    final int ITEM_LOAD_COUNT = 4; //how many items to be displayed at a time
    int total_item = 0, last_visible_item;
    MyAdapter adapter; //the adapter used
    boolean isLoading = false, isMaxData = false;
    String last_node = "", last_key = "";


    private Button search_btn;


    //what to do when this is loaded on the screen
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recycler_view);

        getLastKeyFromFirebase();

        final LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
//        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(), layoutManager.getOrientation());
//        recyclerView.addItemDecoration(dividerItemDecoration);

        adapter = new MyAdapter(this);
        recyclerView.setAdapter(adapter);

        getTime();

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                total_item = layoutManager.getItemCount();
                last_visible_item = layoutManager.findLastVisibleItemPosition();

                if (!isLoading && total_item <= ((last_visible_item + ITEM_LOAD_COUNT))) {
                    getTime();
                    isLoading = true;
                }
            }
        });

        //====================================

        search_btn = findViewById(R.id.search_button);

        final Intent intent = new Intent(this, CarSearchActivity.class);
        final Intent intentYear = new Intent(this, TimeSearchActivity.class);

        search_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                openDialog();

                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                View view = getLayoutInflater().inflate(R.layout.popup, null);
                final EditText search = view.findViewById(R.id.search_text);

                Button manufacturers = view.findViewById(R.id.butt_man);
//                Button names = view.findViewById(R.id.butt_name);
                Button years = view.findViewById(R.id.butt_year);


                builder.setView(view);
                final AlertDialog dialog = builder.create();
                dialog.show();


                years.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (!search.getText().toString().isEmpty()) {

                            intentYear.putExtra("category", "year");
                            intentYear.putExtra("searchedYear", search.getText().toString());
                            startActivity(intentYear);
                        } else {
                            System.out.println("ERROR");
                        }
                    }
                });

                manufacturers.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (!search.getText().toString().isEmpty()) {
                            intent.putExtra("category", "Manufacturer");
                            intent.putExtra("searchedText", search.getText().toString());
                            startActivity(intent);
                        } else {
                            System.out.println("ERROR");
                        }
                    }
                });

            }
        });
        //====================================
    }


    private void getTime() {
        if (!isMaxData) {
            Query query;
            if (TextUtils.isEmpty(last_node))
                query = FirebaseDatabase.getInstance().getReference()
                        .child("Time")
                        .orderByKey()
                        .limitToFirst(ITEM_LOAD_COUNT);
            else
                query = FirebaseDatabase.getInstance().getReference()
                        .child("Time")
                        .orderByKey()
                        .startAt(last_node)
                        .limitToFirst(ITEM_LOAD_COUNT);

            query.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    if (dataSnapshot.hasChildren()) {
                        List<Time> newTimeList = new ArrayList<>();
                        for (DataSnapshot timeSnapshot : dataSnapshot.getChildren()) {
                            newTimeList.add(timeSnapshot.getValue(Time.class));
                        }

                        last_node = newTimeList.get(newTimeList.size() - 1).getId();

                        if (!last_node.equals(last_key))
                            newTimeList.remove(newTimeList.size() - 1);
                        else
                            last_node = "end"; //Fix error infinity load final item

                        adapter.addAll(newTimeList);
                        isLoading = false;
                    } else {
                        isLoading = false;
                        isMaxData = true;
                    }

                }

                @Override
                public void onCancelled(DatabaseError databaseError) {
                    isLoading = false;
                }
            });
        }
    }

    private void getLastKeyFromFirebase() {
        Query getLastKey = FirebaseDatabase.getInstance().getReference()
                .child("Time")
                .orderByKey()
                .limitToLast(1);

        getLastKey.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot lastKey : dataSnapshot.getChildren())
                    last_key = lastKey.getKey();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(MainActivity.this, "Cannot get last key", Toast.LENGTH_SHORT).show();
            }
        });
    }

}