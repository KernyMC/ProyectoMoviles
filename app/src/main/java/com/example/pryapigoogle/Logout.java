package com.example.pryapigoogle;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class Logout extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logout);

        Button btnLeave = findViewById(R.id.btnLeave);

        btnLeave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int itemId = item.getItemId();
                if (itemId == R.id.mapa) {
                    Intent intentMapa = new Intent(Logout.this, MapsActivity.class);
                    startActivity(intentMapa);
                    return true;
                } else if (itemId == R.id.lista) {
                    Intent intentLista = new Intent(Logout.this, RestaurantsActivity.class);
                    startActivity(intentLista);

                    return true;
                } else if (itemId == R.id.cuenta) {

                    return true;
                }
                return false;
            }
        });
    }
}