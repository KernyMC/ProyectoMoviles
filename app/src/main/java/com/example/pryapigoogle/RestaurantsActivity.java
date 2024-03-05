package com.example.pryapigoogle;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class RestaurantsActivity extends AppCompatActivity {
    private RecyclerView rvRestaurants;
    private RestaurantAdapter adapter;
    private static final String TAG = "RestaurantsActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurants);

        rvRestaurants = findViewById(R.id.rvRestaurants);
        rvRestaurants.setLayoutManager(new LinearLayoutManager(this));

        // Create the adapter once
        adapter = new RestaurantAdapter(new ArrayList<>());
        rvRestaurants.setAdapter(adapter);

        // Load restaurant data from XML
        String[] names = getResources().getStringArray(R.array.restaurant_names);
        String[] stars = getResources().getStringArray(R.array.restaurant_stars);
        String[] distances = getResources().getStringArray(R.array.restaurant_distances);
        String[] images = getResources().getStringArray(R.array.restaurant_images);
        String[] latitudes = getResources().getStringArray(R.array.restaurant_latitudes);
        String[] longitudes = getResources().getStringArray(R.array.restaurant_longitudes);

        List<Restaurant> restaurants = new ArrayList<>();

        for (int i = 0; i < names.length; i++) {
            restaurants.add(new Restaurant(names[i], Double.parseDouble(stars[i]), Double.parseDouble(distances[i]), images[i], Double.parseDouble(latitudes[i]), Double.parseDouble(longitudes[i])));
        }

        // Update the adapter with the list of restaurants
        adapter.updateData(restaurants);
    }
}