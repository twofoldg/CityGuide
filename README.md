# CityGuide Android Application

## Table of Contents

- [Overview](#overview)
- [Technologies Used](#technologies-used)
- [Primary Features](#primary-features)
- [Key Components](#key-components)
- [Future Improvements](#future-improvements)

## Overview

CityGuide is an Android application designed to guide users about different city locations. Users can add city details including name, country, a brief description, population, and geographic coordinates (latitude and longitude). The app utilizes Google Maps API to display the geographical locations of the cities.

## Technologies Used

- Android SDK: The software development kit used to create the application, its user interface, activities, and services.
- Java: The primary programming language used for developing the application.
- SQLite: Used for local data storage in the app.
- Room Persistence Library: An abstraction layer provided by Google on top of SQLite to ease database operations.
- Google Maps API: Implemented for displaying geographic locations based on latitude and longitude coordinates.
- Android Geocoder: Handles geocoding and reverse geocoding. Geocoding converts an address (city name and country in this app) into latitude and longitude which is used for plotting locations on Google Maps.

## Primary Features

- **Add City:** Users can fill out details about a city, including city name, country, description, population, and coordinates. The application validates these inputs before saving them to the local database.
- **Display City on Google Maps:** A city location marker is displayed on Google Maps. Geocoder is used to extract latitude and longitude coordinates mapped to the city's name and its country.

## Key Components

- **CityAddActivity:** Activity where a user enters a city's information and saves it to the database.
- **City:** The data model class representing a city. It holds various attributes like name, country, description, population, and geographical coordinates.
- **CityDao:** An interface containing data access object (DAO) methods used for database interactions using Room library.
- **AppDatabase:** A singleton database helper class using Room persistence library and SQLite for data storage and interactions.

## Future Improvements

1. **Enhance the App with More Features:** The application can be improved by the addition of more features like enabling search functionality or providing directions. This makes interaction with the map much richer.
2. **Improving UI/UX:** The current user interface is basic and functional. It can be improved both aesthetically and functionally (UX and UI enhancements) to make it more visually appealing and easier to interact with.
