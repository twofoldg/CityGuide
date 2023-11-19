package com.example.cityguide.ui;

import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cityguide.R;
import com.example.cityguide.database.AppDatabase;
import com.example.cityguide.adapter.CityAdapter;

public class CityListActivity extends AppCompatActivity {

    private CityAdapter cityAdapter;
    Button backButton;
    Button deleteAllButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_city_list);
        backButton = findViewById(R.id.back_button);
        deleteAllButton = findViewById(R.id.deleteAll_button);

        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        cityAdapter = new CityAdapter();
        recyclerView.setAdapter(cityAdapter);

        AppDatabase appDatabase = AppDatabase.getInstance(this);
        appDatabase.cityDao().getAllCities().observe(this, cities -> {
            cityAdapter.setCities(cities);
            deleteAllButton.setEnabled(!cities.isEmpty());
        });

        backButton.setOnClickListener(v -> finish());

        deleteAllButton.setOnClickListener(v -> new Thread(() -> appDatabase.cityDao().deleteAll()).start());
}

    @Override
    protected void onResume() {
        super.onResume();
        AppDatabase appDatabase = AppDatabase.getInstance(this);
        appDatabase.cityDao().getAllCities().observe(this, cities -> {
            cityAdapter.setCities(cities);
            deleteAllButton.setEnabled(!cities.isEmpty());
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        AppDatabase.destroyInstance();
    }
}
