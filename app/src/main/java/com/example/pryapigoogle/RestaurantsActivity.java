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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurants);

        rvRestaurants = findViewById(R.id.rvRestaurants);
        rvRestaurants.setLayoutManager(new LinearLayoutManager(this));

        adapter = new RestaurantAdapter(new ArrayList<>());
        rvRestaurants.setAdapter(adapter);

        String[] names = getResources().getStringArray(R.array.restaurant_names);
        String[] stars = getResources().getStringArray(R.array.restaurant_stars);
        String[] distances = getResources().getStringArray(R.array.restaurant_distances);
        String[] images = getResources().getStringArray(R.array.restaurant_images);
        String[] latitudes = getResources().getStringArray(R.array.restaurant_latitudes);
        String[] longitudes = getResources().getStringArray(R.array.restaurant_longitudes);

        List<Restaurant> restaurants = new ArrayList<>();

        for (int i = 0; i < names.length; i++) {
            String imageName = images[i].substring(images[i].indexOf("/") + 1);
            int imageResId = getResources().getIdentifier(imageName, "drawable", getPackageName());
            restaurants.add(new Restaurant(names[i], Double.parseDouble(stars[i]), Double.parseDouble(distances[i]), imageResId, Double.parseDouble(latitudes[i]), Double.parseDouble(longitudes[i])));
        }

        adapter.updateData(restaurants);
    }
}