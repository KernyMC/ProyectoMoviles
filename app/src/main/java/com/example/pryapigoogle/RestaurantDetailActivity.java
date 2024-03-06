package com.example.pryapigoogle;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class RestaurantDetailActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.restaurant_detail);

        Restaurant restaurant = (Restaurant) getIntent().getSerializableExtra("selected_restaurant");

        TextView tvRestaurantDescriptionDetail = findViewById(R.id.tvRestaurantDescriptionDetail);
        tvRestaurantDescriptionDetail.setText(restaurant.getDescription());
    }
}