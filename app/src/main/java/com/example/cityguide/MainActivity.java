package com.example.cityguide;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.cityguide.ui.CityAddActivity;
import com.example.cityguide.ui.CityListActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnShowCities = findViewById(R.id.btn_show_cities);
        Button btnAddCity = findViewById(R.id.btn_add_city);

        btnShowCities.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, CityListActivity.class);
            startActivity(intent);
        });

        btnAddCity.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, CityAddActivity.class);
            startActivity(intent);
        });
    }
}

