# CityGuide
CityGuide Android Application
Overview
CityGuide is an Android application designed to guide users about different city locations. Users can add information about a city including its name, country, a brief description, population, and geographic coordinates (latitude and longitude). The app utilizes Google Maps API to display geographical locations of the cities.
Technologies Used:
	•	Android SDK: Used for creating the overall application, UI, activities, and services.
	•	Java: The primary programming language used for developing the application.
	•	SQLite: The application uses SQLite for local data storage.
	•	Room Persistence Library: An abstraction layer over SQLite, used for managing database operations more efficiently.
	•	Google Maps API: Implemented for showing geographical locations based on latitude and longitude.
	•	Android Geocoder: A class used for handling geocoding and reverse geocoding. Geocoding converts an address (city name and country in this app) into latitude and longitude which can be plotted on Google Maps.
Primary Features:
	•	Add City: Users can enter details about a city, which includes city name, country, description, population, and geographical coordinates (latitude and longitude). The application validates these inputs before saving them to the local database.
	•	Display City on Google Maps: On Google Maps, a marker is added to the geographic location of a entered city. The Geocoder class is used to obtain the latitude and longitude coordinates from the city name and country.
Key Components:
	•	CityAddActivity: This is the activity where a user inputs a city's information and saves it.
	•	City: A data model class representing a single city. It holds the attributes of a city - name, country, description, population, latitude, and longitude.
	•	CityDao: An interface containing methods for interacting with the local database through the Room library.
	•	AppDatabase: A singleton database helper class that uses the Room persistence library to interact with the SQLite database.
Project Code Highlights:
	1.	Entering and validating data for a city: The code snippet found in CityAddActivity class ensures that the fields are properly filled out and the data is valid before being saved into the local SQLite database.
	2.	Saving city to a local database: The app employs Room ORM (an abstraction over SQLite), which in turn provides SQL-like operations for CRUD tasks.
	3.	Displaying cities on Google Maps: The app uses Google Maps API to visually represent entered cities on a map. Each city is indicated with a marker on the map.
N.B: The Geocoder's ﻿getFromLocationName() method, which is used to get geographical coordinates from city and country names, performs network operations (e.g., API requests to Google's geocoding service). It is highly recommended that these operations be conducted in a background thread using Android's AsyncTask, HandlerThread, or more modern solutions such as Kotlin Coroutines or RxJava.
Future Improvements:
	1.	Handle network operations on a background thread: To avoid freezing the UI or potentially triggering an Application Not Responding (ANR) error, Geocoding operations should be handled in a background thread.
	2.	Feature Expansion: The basic functionality can be extended with features like Search or Directions, making the app more interactive.
	3.	UI/UX Enhancements: The current basic UI could be improved at the aesthetic level and optimized for better user experience (UX). The aim would be to make it more visually appealing and intuitive.
