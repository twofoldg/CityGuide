package com.example.cityguide.adapter;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cityguide.R;
import com.example.cityguide.model.City;
import com.example.cityguide.ui.CityDetailActivity;

import java.util.ArrayList;
import java.util.List;

public class CityAdapter extends RecyclerView.Adapter<CityAdapter.CityViewHolder> {

    private List<City> cityList = new ArrayList<>();

    @NonNull
    @Override
    public CityViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item_city, parent, false);
        return new CityViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull CityViewHolder holder, int position) {
        City currentCity = cityList.get(position);
        holder.cityNameTextView.setText(currentCity.getName());
        holder.countryTextView.setText(currentCity.getCountry());
        holder.descriptionTextView.setText(currentCity.getDescription());
    }

    @Override
    public int getItemCount() {
        return cityList.size();
    }

    public void setCities(List<City> cities) {
        this.cityList = cities;
        notifyDataSetChanged();
    }

    class CityViewHolder extends RecyclerView.ViewHolder {

        private TextView cityNameTextView;
        private TextView countryTextView;
        private TextView descriptionTextView;

        public CityViewHolder(@NonNull View itemView) {
            super(itemView);

            cityNameTextView = itemView.findViewById(R.id.cityNameTextView);
            countryTextView = itemView.findViewById(R.id.countryTextView);
            descriptionTextView = itemView.findViewById(R.id.descriptionTextView);

            itemView.setOnClickListener(view -> {
                int position = getAdapterPosition();

                if (position != RecyclerView.NO_POSITION) {
                    City selectedCity = cityList.get(position);
                    Intent intent = new Intent(view.getContext(), CityDetailActivity.class);
                    intent.putExtra("city", selectedCity);
                    view.getContext().startActivity(intent);
                }
            });
        }
    }


}
