package com.example.pryapigoogle;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class RestaurantDetailActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.restaurant_detail);

        Restaurant restaurant = (Restaurant) getIntent().getSerializableExtra("selected_restaurant");

        TextView tvRestaurantNameDetail = findViewById(R.id.tvRestaurantNameDetail);
        TextView tvRestaurantStarsDetail = findViewById(R.id.tvRestaurantStarsDetail);
        TextView tvRestaurantCategoryDetail = findViewById(R.id.tvRestaurantCategoryDetail);
        ImageView ivRestaurantImageDetail = findViewById(R.id.ivRestaurantImageDetail);

        tvRestaurantNameDetail.setText(restaurant.getName());
        tvRestaurantStarsDetail.setText(String.valueOf(restaurant.getStars()));
        tvRestaurantCategoryDetail.setText(restaurant.getCategory());
        ivRestaurantImageDetail.setImageResource(restaurant.getImageUrl());
    }
}