package com.example.pryapigoogle;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    //Object Declaration
    Button btn_showMap;
    EditText etBusqueda;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Map
        btn_showMap = findViewById(R.id.bt_showMap);
        //Method for the Button, but not on Click, when the text is entered, and Enter is pressed.
        btn_showMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Connection
                Intent intent = new Intent(MainActivity.this, Login.class);                startActivity(intent);
            }
        });
    }
}