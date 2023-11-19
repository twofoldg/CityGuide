package com.example.cityguide.ui;

import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.cityguide.R;
import com.example.cityguide.database.AppDatabase;
import com.example.cityguide.model.City;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class CityDetailActivity extends AppCompatActivity implements OnMapReadyCallback {

    private TextView cityCountry;
    private TextView cityDescription;
    private TextView cityPopulation;
    private TextView cityLatitude;
    private TextView cityLongitude;
    Button backButton;
    Button deleteButton;
    private GoogleMap mMap;
    private City city;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_city_detail);

        backButton = findViewById(R.id.back_button);
        deleteButton = findViewById(R.id.delete_button);

        MapFragment mapFragment = (MapFragment) getFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        city = getIntent().getParcelableExtra("city");

        TextView cityName = findViewById(R.id.city_name);
        cityCountry = findViewById(R.id.city_country);
        cityDescription = findViewById(R.id.city_description);
        cityPopulation = findViewById(R.id.city_population);
        cityLatitude = findViewById(R.id.city_latitude);
        cityLongitude = findViewById(R.id.city_longitude);

        Intent intent = getIntent();
        city = (City) intent.getSerializableExtra("city");

        cityName.setText(city.getName());
        cityCountry.setText(city.getCountry());
        cityDescription.setText(city.getDescription());
        cityPopulation.setText(city.getPopulation());
        cityLatitude.setText(String.valueOf(city.getLatitude()));
        cityLongitude.setText(String.valueOf(city.getLongitude()));

        backButton.setOnClickListener(v -> finish());

        deleteButton.setOnClickListener(v -> {
            new Thread(() -> {
                AppDatabase.getInstance(CityDetailActivity.this).cityDao().delete(city);
            }).start();
            finish();
        });

    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        Geocoder geocoder = new Geocoder(this, Locale.getDefault());
        try {
            List<Address> addresses = geocoder.getFromLocationName(city.getName() + ", " + city.getCountry(), 1);
            if (addresses != null && !addresses.isEmpty()) {
                Address address = addresses.get(0);

                LatLng cityLocation = new LatLng(address.getLatitude(), address.getLongitude());
                mMap.addMarker(new MarkerOptions().position(cityLocation).title(city.getName()));
                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(cityLocation, 10));
            } else {
                Toast.makeText(this, "Unable to find location for the city", Toast.LENGTH_LONG).show();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        AppDatabase.destroyInstance();
    }
}
