package com.example.pryapigoogle;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class RestaurantAdapter extends RecyclerView.Adapter<RestaurantAdapter.RestaurantViewHolder> {
    private List<Restaurant> restaurants;

    public RestaurantAdapter(List<Restaurant> restaurants) {
        this.restaurants = restaurants;
    }

    // Add a method to update the data and notify the adapter
    public void updateData(List<Restaurant> newRestaurants) {
        this.restaurants = newRestaurants;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public RestaurantViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.restaurant_item, parent, false);
        return new RestaurantViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RestaurantViewHolder holder, int position) {
        Restaurant restaurant = restaurants.get(position);
        holder.getTvRestaurantName().setText(restaurant.getName());
        holder.getTvRestaurantStars().setText(String.valueOf(restaurant.getStars()));
        holder.getTvRestaurantDistance().setText(String.valueOf(restaurant.getDistance()));

        // Add a click listener to the button
        holder.getBtnLocate().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Create an intent to open MapsActivity
                Intent intent = new Intent(v.getContext(), MapsActivity.class);
                // Pass the coordinates of the restaurant to the intent
                intent.putExtra("latitude", restaurant.getX());
                intent.putExtra("longitude", restaurant.getY());
                // Start MapsActivity
                v.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return restaurants.size();
    }

    public static class RestaurantViewHolder extends RecyclerView.ViewHolder {
        private TextView tvRestaurantName;
        private TextView tvRestaurantStars;
        private TextView tvRestaurantDistance;
        private Button btnLocate;

        public RestaurantViewHolder(@NonNull View itemView) {
            super(itemView);
            tvRestaurantName = itemView.findViewById(R.id.tvRestaurantName);
            tvRestaurantStars = itemView.findViewById(R.id.tvRestaurantStars);
            tvRestaurantDistance = itemView.findViewById(R.id.tvRestaurantDistance);
            btnLocate = itemView.findViewById(R.id.btnLocate);
        }

        public TextView getTvRestaurantName() {
            return tvRestaurantName;
        }

        public TextView getTvRestaurantStars() {
            return tvRestaurantStars;
        }

        public TextView getTvRestaurantDistance() {
            return tvRestaurantDistance;
        }

        public Button getBtnLocate() {
            return btnLocate;
        }
    }
}