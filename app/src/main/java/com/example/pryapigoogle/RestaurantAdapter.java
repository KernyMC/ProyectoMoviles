package com.example.pryapigoogle;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class RestaurantAdapter extends RecyclerView.Adapter<RestaurantAdapter.RestaurantViewHolder> {
    private List<Restaurant> restaurants;

    public RestaurantAdapter(List<Restaurant> restaurants) {
        this.restaurants = restaurants;
    }

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
        holder.getTvRestaurantCategory().setText(String.valueOf(restaurant.getCategory()));
        holder.getIvRestaurantImage().setImageResource(restaurant.getImageUrl());

        holder.getBtnLocate().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), MapsActivity.class);
                intent.putExtra("latitude", restaurant.getX());
                intent.putExtra("longitude", restaurant.getY());
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
        private TextView tvRestaurantCategory;
        private Button btnLocate;
        private ImageView ivRestaurantImage;

        public RestaurantViewHolder(@NonNull View itemView) {
            super(itemView);
            tvRestaurantName = itemView.findViewById(R.id.tvRestaurantName);
            tvRestaurantStars = itemView.findViewById(R.id.tvRestaurantStars);
            tvRestaurantCategory = itemView.findViewById(R.id.tvRestaurantDistance);
            btnLocate = itemView.findViewById(R.id.btnLocate);
            ivRestaurantImage = itemView.findViewById(R.id.ivRestaurantImage);
        }

        public TextView getTvRestaurantName() {
            return tvRestaurantName;
        }

        public TextView getTvRestaurantStars() {
            return tvRestaurantStars;
        }

        public TextView getTvRestaurantDistance() {
            return tvRestaurantCategory;
        }

        public Button getBtnLocate() {
            return btnLocate;
        }

        public ImageView getIvRestaurantImage() {
            return ivRestaurantImage;
        }
    }
}