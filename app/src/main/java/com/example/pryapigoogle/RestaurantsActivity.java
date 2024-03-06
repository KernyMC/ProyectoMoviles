package com.example.pryapigoogle;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import java.util.ArrayList;
import java.util.List;

public class RestaurantsActivity extends AppCompatActivity {
    private RecyclerView rvRestaurants;
    private RestaurantAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurants);

        rvRestaurants = findViewById(R.id.rvRestaurants);
        rvRestaurants.setLayoutManager(new LinearLayoutManager(this));

        adapter = new RestaurantAdapter(new ArrayList<>(), this);
        rvRestaurants.setAdapter(adapter);

        String[] names = getResources().getStringArray(R.array.restaurant_names);
        String[] stars = getResources().getStringArray(R.array.restaurant_stars);
        String[] categories = getResources().getStringArray(R.array.restaurant_categories);
        String[] descriptions = getResources().getStringArray(R.array.restaurant_descriptions);
        String[] images = getResources().getStringArray(R.array.restaurant_images);
        String[] latitudes = getResources().getStringArray(R.array.restaurant_latitudes);
        String[] longitudes = getResources().getStringArray(R.array.restaurant_longitudes);

        List<Restaurant> restaurants = new ArrayList<>();

        for (int i = 0; i < names.length; i++) {
            String imageName = images[i].substring(images[i].indexOf("/") + 1);
            int imageResId = getResources().getIdentifier(imageName, "drawable", getPackageName());
            restaurants.add(new Restaurant(names[i], Double.parseDouble(stars[i]), categories[i], descriptions[i], imageResId, Double.parseDouble(latitudes[i]), Double.parseDouble(longitudes[i])));
        }

        adapter.updateData(restaurants);

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int itemId = item.getItemId();
                if (itemId == R.id.mapa) {
                    Intent intentMapa = new Intent(RestaurantsActivity.this, MapsActivity.class);
                    startActivity(intentMapa);
                    return true;
                } else if (itemId == R.id.lista) {
                    // Iniciar RestaurantsActivity cuando se haga clic en "lista"

                    return true;
                } else if (itemId == R.id.cuenta) {
                    Intent intentCuenta = new Intent(RestaurantsActivity.this, Logout.class);
                    startActivity(intentCuenta);
                    return true;
                }
                return false;
            }
        });

    }

    // Suponiendo que tienes un método para manejar el clic en un elemento de la lista
    public void onRestaurantSelected(Restaurant restaurant) {
        Intent intent = new Intent(this, RestaurantDetailActivity.class);
        intent.putExtra("selected_restaurant", restaurant);
        startActivity(intent);
    }
}