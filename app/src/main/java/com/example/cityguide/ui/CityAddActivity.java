package com.example.cityguide.ui;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.cityguide.R;
import com.example.cityguide.database.AppDatabase;
import com.example.cityguide.model.City;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class CityAddActivity extends AppCompatActivity {

    private EditText cityName;
    private EditText cityCountry;
    private EditText cityDescription;
    private EditText cityPopulation;
    private EditText cityLatitude;
    private EditText cityLongitude;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_city_add);

        cityName = findViewById(R.id.city_name);
        cityCountry = findViewById(R.id.city_country);
        cityDescription = findViewById(R.id.city_description);
        cityPopulation = findViewById(R.id.city_population);
        cityLatitude = findViewById(R.id.city_latitude);
        cityLongitude = findViewById(R.id.city_longitude);

        Button saveButton = findViewById(R.id.save_button);
        Button backButton = findViewById(R.id.back_button);

        saveButton.setOnClickListener(v -> {
            String name = cityName.getText().toString().trim();
            String country = cityCountry.getText().toString().trim();
            String description = cityDescription.getText().toString().trim();
            String population = cityPopulation.getText().toString().trim();
            double latitude = 0;
            double longitude = 0;

            try {
                latitude = Double.parseDouble(cityLatitude.getText().toString().trim());
                longitude = Double.parseDouble(cityLongitude.getText().toString().trim());
            } catch (NumberFormatException e) {
                Toast.makeText(CityAddActivity.this, "Please enter valid coordinates", Toast.LENGTH_SHORT).show();
                return;
            }

            if (validateInput(name, country, description, population, latitude, longitude)) {
                City cityToSave = new City(name, country, description, population, latitude, longitude);

                AppDatabase.getInstance(CityAddActivity.this)
                        .cityDao()
                        .insert(cityToSave)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(cityId -> {
                            Toast.makeText(CityAddActivity.this,
                                            "City successfully added with name: " + cityName.getText(),
                                            Toast.LENGTH_LONG)
                                    .show();

                            startActivity(new Intent(CityAddActivity.this, CityListActivity.class));
                            finish();
                        }, throwable -> {
                            Toast.makeText(CityAddActivity.this,
                                            "Error occurred: " + throwable.getMessage(),
                                            Toast.LENGTH_LONG)
                                    .show();
                        });
            }
        });
        backButton.setOnClickListener(v -> finish());
    }

    private boolean validateInput(String name, String country, String description,
                                  String population, double latitude, double longitude) {

        if (name.isEmpty() ||
                country.isEmpty() ||
                description.isEmpty() ||
                population.isEmpty() ||
                Double.isNaN(latitude) ||
                Double.isNaN(longitude)) {
            Toast.makeText(this, "Fields cannot be empty", Toast.LENGTH_SHORT).show();
            return false;
        }

        int pop = 0;
        try{
            pop = Integer.parseInt(population);
        }catch(NumberFormatException e){
            Toast.makeText(this, "Enter valid number for population", Toast.LENGTH_SHORT).show();
            return false;
        }

        if(pop < 0){
            Toast.makeText(this, "Population can't be negative", Toast.LENGTH_SHORT).show();
            return false;
        }

        if (!name.matches("[a-zA-Z ]+")) {
            Toast.makeText(this, "Name can only contain letters and spaces", Toast.LENGTH_SHORT).show();
            return false;
        }

        if (!country.matches("[a-zA-Z ]+")) {
            Toast.makeText(this, "Name can only contain letters and spaces", Toast.LENGTH_SHORT).show();
            return false;
        }

        if (latitude < -90 || latitude > 90) {
            Toast.makeText(this, "Latitude must be a decimal number between -90 and 90", Toast.LENGTH_SHORT).show();
            return false;
        }

        if (longitude < -180 || longitude > 180) {
            Toast.makeText(this, "Longitude must be a decimal number between -180 and 180", Toast.LENGTH_SHORT).show();
            return false;
        }

        return true;
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        AppDatabase.destroyInstance();
    }
}
