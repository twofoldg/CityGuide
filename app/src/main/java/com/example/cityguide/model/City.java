package com.example.cityguide.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;
import java.util.Objects;

@Entity(tableName = "cities")
public class City implements Serializable {

    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name = "name")
    private String name;

    @ColumnInfo(name = "country")
    private String country;

    @ColumnInfo(name = "description")
    private String description;

    @ColumnInfo(name = "population")
    private String population;

    @ColumnInfo(name = "latitude")
    private double latitude;

    @ColumnInfo(name = "longitude")
    private double longitude;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (name != null && !name.isEmpty()) {
            this.name = name;
        } else {
            throw new IllegalArgumentException("Invalid city name");
        }
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPopulation() {
        return population;
    }

    public void setPopulation(String population) {
        if (population != null && population.matches("\\d+")) {
            this.population = population;
        } else {
            throw new IllegalArgumentException("Invalid population value");
        }
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public City(String name, String country, String description, String population, double latitude, double longitude) {
        this.name = name;
        this.country = country;
        this.description = description;
        this.population = population;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public  City() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        City city = (City) o;
        return id == city.id && Double.compare(city.latitude, latitude) == 0 && Double.compare(city.longitude, longitude) == 0 && Objects.equals(name, city.name) && Objects.equals(country, city.country) && Objects.equals(description, city.description) && Objects.equals(population, city.population);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, country, description, population, latitude, longitude);
    }
}