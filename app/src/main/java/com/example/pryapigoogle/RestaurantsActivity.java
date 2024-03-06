package com.example.pryapigoogle;

import android.content.Intent;
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
    }

    // Suponiendo que tienes un método para manejar el clic en un elemento de la lista
    public void onRestaurantSelected(Restaurant restaurant) {
        Intent intent = new Intent(this, RestaurantDetailActivity.class);
        intent.putExtra("selected_restaurant", restaurant);
        startActivity(intent);
    }
}