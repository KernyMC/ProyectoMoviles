package com.example.pryapigoogle;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.FragmentActivity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.libraries.places.api.Places;
import com.google.android.libraries.places.api.model.Place;
import com.google.android.libraries.places.api.model.PlaceLikelihood;
import com.google.android.libraries.places.api.net.FindCurrentPlaceRequest;
import com.google.android.libraries.places.api.net.FindCurrentPlaceResponse;
import com.google.android.libraries.places.api.net.PlacesClient;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {
    //Declare objects
    GoogleMap gmap;
    FrameLayout map;
    EditText busqueda;
    ImageView buscar;
    PlacesClient placesClient;
    private boolean locationPermissionGranted;
    private static final int PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        //Map
        map = findViewById(R.id.map);
        //Obtains map fragment reference from the design
        SupportMapFragment mapFragment =
                (SupportMapFragment) getSupportFragmentManager().
                        findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        busqueda = findViewById(R.id.etBusqueda);
        buscar = findViewById(R.id.ivBuscar);
        buscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onSearch(v);
            }
        });


        // Initialize the SDK
        Places.initialize(getApplicationContext(), "AIzaSyDRxolAYcASAmWkmc45VWX_MJY0hS4tNMA");
        // Create a new PlacesClient instance
        placesClient = Places.createClient(this);

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int itemId = item.getItemId();
                if (itemId == R.id.mapa) {
                    // No hacer nada cuando se haga clic en "mapa"
                    return true;
                } else if (itemId == R.id.lista) {
                    // Iniciar RestaurantsActivity cuando se haga clic en "lista"
                    Intent intentLista = new Intent(MapsActivity.this, RestaurantsActivity.class);
                    startActivity(intentLista);
                    return true;
                } else if (itemId == R.id.cuenta) {
                    Intent intentCuenta = new Intent(MapsActivity.this, Logout.class);
                    startActivity(intentCuenta);
                    return true;
                }
                return false;
            }
        });

    }

    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {
        this.gmap = googleMap;
        LatLng mapEcuador = new LatLng(-0.214950, -78.513199);
        LatLng mapESPE = new LatLng(-0.31305337694744995, -78.44547026622705);
        this.gmap.addMarker(new MarkerOptions().position(mapESPE).title("ESPE"));
        this.gmap.moveCamera(CameraUpdateFactory.newLatLng(mapESPE));
        this.gmap.animateCamera(CameraUpdateFactory.newLatLngZoom(mapESPE, 15));

        findNearbyRestaurants();
    }

    private void findNearbyRestaurants() {
        // Define a Place type to search for (in this case, restaurants)
        List<Place.Field> placeFields = Arrays.asList(Place.Field.NAME, Place.Field.ADDRESS, Place.Field.LAT_LNG, Place.Field.TYPES);

        // Construct a request object
        FindCurrentPlaceRequest request = FindCurrentPlaceRequest.newInstance(placeFields);

        // Call findCurrentPlace and handle the response (first check that the user has granted permission)
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            Task<FindCurrentPlaceResponse> placeResponse = placesClient.findCurrentPlace(request);
            placeResponse.addOnCompleteListener(task -> {
                if (task.isSuccessful()){
                    FindCurrentPlaceResponse response = task.getResult();
                    for (PlaceLikelihood placeLikelihood : response.getPlaceLikelihoods()) {
                        if (placeLikelihood.getPlace().getTypes().contains(Place.Type.RESTAURANT)) {
                            Log.i(TAG, String.format("Place '%s' is a restaurant and has likelihood: %f",
                                    placeLikelihood.getPlace().getName(),
                                    placeLikelihood.getLikelihood()));
                            LatLng latLng = new LatLng(placeLikelihood.getPlace().getLatLng().latitude, placeLikelihood.getPlace().getLatLng().longitude);
                            gmap.addMarker(new MarkerOptions().position(latLng).title(placeLikelihood.getPlace().getName()));
                        }
                    }
                } else {
                    Exception exception = task.getException();
                    if (exception instanceof ApiException) {
                        ApiException apiException = (ApiException) exception;
                        Log.e(TAG, "Place not found: " + apiException.getStatusCode());
                    }
                }
            });
        } else {
            // A local method to request required permissions;
            // See https://developer.android.com/training/permissions/requesting
            getLocationPermission();
        }
    }

    private void getLocationPermission() {
        if (ActivityCompat.checkSelfPermission(this.getApplicationContext(),
                android.Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED) {
            locationPermissionGranted = true;
        } else {
            ActivityCompat.requestPermissions(this,
                    new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION},
                    PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION);

        }
    }

    public void onSearch(View view) {
        String location = busqueda.getText().toString();
        List<Address> addressList = null;
        if (location != null || !location.equals("")) {
            Geocoder geocoder = new Geocoder(this);
            try {
                addressList = geocoder.getFromLocationName(location, 1);
            } catch (IOException e) {
                e.printStackTrace();
            }
            Address address = addressList.get(0);
            LatLng latLng = new LatLng(address.getLatitude(), address.getLongitude());
            gmap.addMarker(new MarkerOptions().position(latLng).title(location));
            gmap.animateCamera(CameraUpdateFactory.newLatLng(latLng));
            //Hacer zoom al lugar
            gmap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 15));
        }
    }
}