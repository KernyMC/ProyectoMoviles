package com.example.pryapigoogle;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import androidx.appcompat.widget.SearchView;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import java.util.ArrayList;
import java.util.List;

public class RestaurantsActivity extends AppCompatActivity {
    private RecyclerView rvRestaurants;
    private RestaurantAdapter adapter;
    private SearchView searchView;
    private Spinner spinner;
    private List<Restaurant> allRestaurants;
    private List<Restaurant> filteredRestaurants;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurants);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        searchView = findViewById(R.id.searchView);
        spinner = findViewById(R.id.spinner);

        rvRestaurants = findViewById(R.id.rvRestaurants);
        rvRestaurants.setLayoutManager(new LinearLayoutManager(this));

        allRestaurants = new ArrayList<>();
        filteredRestaurants = new ArrayList<>();

        adapter = new RestaurantAdapter(filteredRestaurants, this);
        rvRestaurants.setAdapter(adapter);

        String[] names = getResources().getStringArray(R.array.restaurant_names);
        String[] stars = getResources().getStringArray(R.array.restaurant_stars);
        String[] categories = getResources().getStringArray(R.array.restaurant_categories);
        String[] descriptions = getResources().getStringArray(R.array.restaurant_descriptions);
        String[] images = getResources().getStringArray(R.array.restaurant_images);
        String[] latitudes = getResources().getStringArray(R.array.restaurant_latitudes);
        String[] longitudes = getResources().getStringArray(R.array.restaurant_longitudes);

for (int i = 0; i < names.length; i++) {
    String imageName = images[i];
    int imageResId = getResources().getIdentifier(imageName, "drawable", getPackageName());
    allRestaurants.add(new Restaurant(names[i], Double.parseDouble(stars[i]), categories[i], descriptions[i], imageResId, Double.parseDouble(latitudes[i]), Double.parseDouble(longitudes[i])));
}

filterRestaurants(); // Asegúrate de llamar a este método aquí

setupSearchView();
setupSpinner();

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

private void setupSearchView() {
    searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
        @Override
        public boolean onQueryTextSubmit(String query) {
            filterRestaurants();
            return false;
        }

        @Override
        public boolean onQueryTextChange(String newText) {
            filterRestaurants();
            return true;
        }
    });
}

    private void setupSpinner() {
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                filterRestaurants();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // No hacer nada
            }
        });
    }

private void filterRestaurants() {
    String searchText = searchView.getQuery() != null ? searchView.getQuery().toString().toLowerCase() : "";
    String selectedCategory = spinner.getSelectedItem() != null ? spinner.getSelectedItem().toString() : "";

    filteredRestaurants.clear();

    for (Restaurant restaurant : allRestaurants) {
        if ((searchText.isEmpty() && selectedCategory.isEmpty()) ||
            (restaurant.getName().toLowerCase().contains(searchText) && restaurant.getCategory().equals(selectedCategory))) {
            filteredRestaurants.add(restaurant);
        }
    }

    adapter.updateData(filteredRestaurants);
}
    // Suponiendo que tienes un método para manejar el clic en un elemento de la lista
    public void onRestaurantSelected(Restaurant restaurant) {
        Intent intent = new Intent(this, RestaurantDetailActivity.class);
        intent.putExtra("selected_restaurant", restaurant);
        startActivity(intent);
    }
}