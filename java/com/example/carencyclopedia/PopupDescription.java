package com.example.carencyclopedia;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

/**
 * This class is used to show the details of the chosen car
 */
public class PopupDescription extends AppCompatActivity {

    String descr = "";

    TextView description; //the text to be displayed

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_popup_description); //set the layout to be activity_popup_description

        description = findViewById(R.id.description);

        if (getIntent() != null)
            descr = getIntent().getStringExtra("CarTxt"); //get the text

        if (!descr.isEmpty()) {
            description.setText(descr); //display the text
        }
    }
}
