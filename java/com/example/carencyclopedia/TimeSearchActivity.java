package com.example.carencyclopedia;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import com.example.carencyclopedia.Model.Time;
import com.example.carencyclopedia.ViewHolder.TimeViewHolder;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

/**
 * This class is used for the search option of looking for a certain year
 * The search button has two options, search for car details or search for a certain year,
 * this class implements the functionality of the search for a certain year button
 */
public class TimeSearchActivity extends AppCompatActivity {

    private String searchedYear;
    private String category;


    FirebaseDatabase database; //define the database to be used
    DatabaseReference TimeList; //defines the reference to the database

//    String id = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_time_search); //sets the layout that is used for this class (activity_time_search)

        //Firebase
        database = FirebaseDatabase.getInstance(); //this is done so we can traverse the db
        TimeList = database.getReference("Time"); //we need to look into "Time"


        if (getIntent() != null) { //this gets the searched year from the search layout
            searchedYear = getIntent().getStringExtra("searchedYear"); //the year that is searched
            category = getIntent().getStringExtra("category"); //
        }

        if (!searchedYear.isEmpty() && !category.isEmpty() && searchedYear != null && category != null) { //if the intents have transmitted correctly
            //loadTimes(searchedYear, category);


            TimeList.orderByChild("year").startAt(searchedYear).endAt(searchedYear + "\uf8ff").addValueEventListener(new ValueEventListener() { //query
                @Override
                public void onDataChange(DataSnapshot snapshot) {

                    for(DataSnapshot userSnapshot : snapshot.getChildren()){ //get the results of the query
                        Time time = userSnapshot.getValue(Time.class); //transform that snapshot into a Time class
                        Intent carList = new Intent(TimeSearchActivity.this, CarList.class); //send intents
                        carList.putExtra("TimeId", time.getId()); //timeList.get(position).getId()
                        startActivity(carList);
                    }

                }
                @Override
                public void onCancelled(DatabaseError databaseError) {
                }
            });

        }

    }

}
