CityGuide Android Application
Overview
This is an Android application designed to guide users about different city locations. Users can add city information including city name, country, description, population, geographical coordinates (latitude and longitude). The Google Maps API is integrated to display cities' geographic locations.
Primary Features:
	•	Add City: Users can enter details about a city, which includes city name, country, description, population, and geographical coordinates (latitude and longitude). The application validates these inputs before persisting them to a local database.
	•	Display City on Google Maps: On Google Maps, a marker is added to the geographic location of the entered city. The Geocoder class is used to obtain latitude and longitude coordinates from the city name and country.
Key Components:
	•	CityAddActivity: This is the activity where a user inputs a city's information and saves it.
	•	City: A data model class representing a single city. It holds the attributes of a city - name, country, description, population, latitude, and longitude.
	•	CityDao: An interface containing methods used to interact with the local database using Room library.
	•	AppDatabase: A singleton database helper class that uses Room persistence library to interact with SQLite database.
Project Code:
	1.	Entering data for a city : Code snippet found in CityAddActivity class ensures data is correctly entered and then saved into the local SQLite database.
	2.	Validation of input city data : All fields are required and validated properly including necessary type and format checks.
	3.	Saving city to a local database : City data is stored in a local SQLite database, which uses the Room persistence library to abstract the underlying complexities of SQLite operations.
	4.	Google Maps API integration : Google Maps API is used to display the geographical location of a city. Geocoder.getFromLocationName() is used to fetch geo-coordinates from the city name and the country.
Despite being indirect, the use of Geocoder.getFromLocationName() constitutes a usage of web services (API calls to Google's geocoding service), which should ideally be done on a background thread given it's a network operation. It's recommended to refactor this part with Android's AsyncTask, Handler, or a more modern solution like Kotlin Coroutines or RxJava may be employed for this purpose.
The overall architecture and choice of the local database make the app robust, responsive and user-friendly. Although simple, the design can be scaled or expanded in future to add more functionality/features as required.
Upcoming Improvements:
	•	Handle network operations on background thread: The application needs to handle blocking operations like calls to the Geocoding service on a background thread to prevent ANR issues.
	•	Incorporate more features: Basic functionality can be extended to incorporate more features like Search and Directions to make it more interactive.
	•	UI/UX Enhancements: The current UI is functional but basic. Enhancements can be made to make it visually more appealing.