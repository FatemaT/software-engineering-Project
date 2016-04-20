package com.cs442.faaltene.touristassist;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

import models.Attraction;
import models.Hospital;
import models.Hotel;
import models.Mall;
import models.Restaurant;
import models.Review;
import models.Showtime;
import models.Club;

public class MainScreen extends AppCompatActivity {
    Button submit;
    EditText cityName;

    String TAG = "Response";

    String getCel;
    Intent i;

    Hotel[] hotels;
    Showtime[] showtimes;
    Restaurant[] restaurants;
    Mall[] malls;
    Hospital[] hospitals;
    Club[] clubs;
    Review[] reviews;
    Attraction[] attractions;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_screen);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        submit = (Button) findViewById(R.id.submit);
        Typeface tf = Typeface.createFromAsset(getAssets(), "green avocado.ttf");
        submit.setTypeface(tf);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cityName = (EditText) findViewById(R.id.cityName);
                i = new Intent(MainScreen.this, MainNavigation.class);
                getCel = cityName.getText().toString();
                i.putExtra("city", getCel);
                //startActivity(i);
                AsyncCallWS task = new AsyncCallWS();
                task.execute();
            }
        });

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main_screen, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private class AsyncCallWS extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            Log.i(TAG, "onPreExecute");
        }

        @Override
        protected Void doInBackground(Void... params) {
            Log.i(TAG, "doInBackground");
            retrieveShowtimes();
            retrieveHotels();
            retrieveRestaurants();
            retrieveMalls();
            retrieveHospitals();
            retrieveClubs();
            retrieveAttractions();
            retrieveReviews();

            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            Log.i(TAG, "onPostExecute");
            i.putExtra("hotels", hotels);
            i.putExtra("hospitals",hospitals);
            i.putExtra("showtimes",showtimes);
            i.putExtra("restaurants", restaurants);
            i.putExtra("malls",malls);
            i.putExtra("clubs",clubs);
            i.putExtra("attractions",attractions);
            i.putExtra("reviews",reviews);
            startActivity(i);
            // Toast.makeText(MainActivity.this, "Response" + re, Toast.LENGTH_LONG).show();
        }

    }

    public void retrieveHotels() {
        String SOAP_ACTION = "http://main.ta.se.cs.com/getHotels";
        String METHOD_NAME = "getHotels";
        String NAMESPACE = "http://main.ta.se.cs.com/";
        String URL = "http://10.0.2.2:7101/SoftwareEngineeringHostServices-ViewController-context-root/TouristAssistServicePort?wsdl";

        try {
            SoapObject Request = new SoapObject(NAMESPACE, METHOD_NAME);
            Request.addProperty("arg0" ,getCel);

            SoapSerializationEnvelope soapEnvelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
            soapEnvelope.setOutputSoapObject(Request);

            HttpTransportSE transport = new HttpTransportSE(URL);

            transport.call(SOAP_ACTION, soapEnvelope);

            SoapObject soapObject = (SoapObject) soapEnvelope.getResponse();
            hotels = parseHotels(soapObject);

            String re = soapObject.getProperty(0).toString();
            // System.out.println("*************" +re);


            Log.i(TAG, "Result : " + re);
        } catch (Exception ex) {
            Log.e(TAG, "Error: " + ex.getMessage());
        }
    }


    public void retrieveShowtimes() {
        String SOAP_ACTION = "http://main.ta.se.cs.com/getShowtimes";
        String METHOD_NAME = "getShowtimes";
        String NAMESPACE = "http://main.ta.se.cs.com/";
        String URL = "http://10.0.2.2:7101/SoftwareEngineeringHostServices-ViewController-context-root/TouristAssistServicePort?wsdl";

        try {
            SoapObject Request = new SoapObject(NAMESPACE, METHOD_NAME);
            Request.addProperty("arg0" ,getCel);

            SoapSerializationEnvelope soapEnvelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
            soapEnvelope.setOutputSoapObject(Request);

            HttpTransportSE transport = new HttpTransportSE(URL);

            transport.call(SOAP_ACTION, soapEnvelope);

            SoapObject soapObject = (SoapObject) soapEnvelope.getResponse();
            showtimes = parseShowtimes(soapObject);

            String re = showtimes[0].getShowtimeDuration();
            //System.out.println("*************" +re);


            Log.i(TAG, "Result : " + re);
        } catch (Exception ex) {
            Log.e(TAG, "Error: " + ex.getMessage());
        }
    }

    public void retrieveRestaurants() {
        String SOAP_ACTION = "http://main.ta.se.cs.com/getRestaurants";
        String METHOD_NAME = "getRestaurants";
        String NAMESPACE = "http://main.ta.se.cs.com/";
        String URL = "http://10.0.2.2:7101/SoftwareEngineeringHostServices-ViewController-context-root/TouristAssistServicePort?wsdl";

        try {
            SoapObject Request = new SoapObject(NAMESPACE, METHOD_NAME);
            Request.addProperty("arg0" ,getCel);

            SoapSerializationEnvelope soapEnvelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
            soapEnvelope.setOutputSoapObject(Request);

            HttpTransportSE transport = new HttpTransportSE(URL);

            transport.call(SOAP_ACTION, soapEnvelope);

            SoapObject soapObject = (SoapObject) soapEnvelope.getResponse();
            restaurants = parseRestaurants(soapObject);

            String re = restaurants[0].getRestaurantCusines();
            //System.out.println("*************" +re);


            Log.i(TAG, "Result : " + re);
        } catch (Exception ex) {
            Log.e(TAG, "Error: " + ex.getMessage());
        }
    }

    public void retrieveMalls() {
        String SOAP_ACTION = "http://main.ta.se.cs.com/getMalls";
        String METHOD_NAME = "getMalls";
        String NAMESPACE = "http://main.ta.se.cs.com/";
        String URL = "http://10.0.2.2:7101/SoftwareEngineeringHostServices-ViewController-context-root/TouristAssistServicePort?wsdl";

        try {
            SoapObject Request = new SoapObject(NAMESPACE, METHOD_NAME);
            Request.addProperty("arg0" ,getCel);

            SoapSerializationEnvelope soapEnvelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
            soapEnvelope.setOutputSoapObject(Request);

            HttpTransportSE transport = new HttpTransportSE(URL);

            transport.call(SOAP_ACTION, soapEnvelope);

            SoapObject soapObject = (SoapObject) soapEnvelope.getResponse();
            malls = parseMalls(soapObject);

            String re = malls[0].getMall_stores();
            //System.out.println("*************" +re);


            Log.i(TAG, "Result : " + re);
        } catch (Exception ex) {
            Log.e(TAG, "Error: " + ex.getMessage());
        }
    }

    public void retrieveHospitals() {
        String SOAP_ACTION = "http://main.ta.se.cs.com/getHospitals";
        String METHOD_NAME = "getHospitals";
        String NAMESPACE = "http://main.ta.se.cs.com/";
        String URL = "http://10.0.2.2:7101/SoftwareEngineeringHostServices-ViewController-context-root/TouristAssistServicePort?wsdl";

        try {
            SoapObject Request = new SoapObject(NAMESPACE, METHOD_NAME);
            Request.addProperty("arg0" ,getCel);

            SoapSerializationEnvelope soapEnvelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
            soapEnvelope.setOutputSoapObject(Request);

            HttpTransportSE transport = new HttpTransportSE(URL);

            transport.call(SOAP_ACTION, soapEnvelope);

            SoapObject soapObject = (SoapObject) soapEnvelope.getResponse();
            hospitals = parseHospitals(soapObject);

            String re = hospitals[0].getSpecializations();

            Log.i(TAG, "Result : " + re);
        } catch (Exception ex) {
            Log.e(TAG, "Error: " + ex.getMessage());
        }
    }

    public void retrieveClubs() {
        String SOAP_ACTION = "http://main.ta.se.cs.com/getClubs";
        String METHOD_NAME = "getClubs";
        String NAMESPACE = "http://main.ta.se.cs.com/";
        String URL = "http://10.0.2.2:7101/SoftwareEngineeringHostServices-ViewController-context-root/TouristAssistServicePort?wsdl";

        try {
            SoapObject Request = new SoapObject(NAMESPACE, METHOD_NAME);
            Request.addProperty("arg0" ,getCel);

            SoapSerializationEnvelope soapEnvelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
            soapEnvelope.setOutputSoapObject(Request);

            HttpTransportSE transport = new HttpTransportSE(URL);

            transport.call(SOAP_ACTION, soapEnvelope);

            SoapObject soapObject = (SoapObject) soapEnvelope.getResponse();
            clubs = parseClubs(soapObject);

            String re = clubs[0].getClubDetails();

            Log.i(TAG, "Result : " + re);
        } catch (Exception ex) {
            Log.e(TAG, "Error: " + ex.getMessage());
        }
    }

    public void retrieveAttractions() {
        String SOAP_ACTION = "http://main.ta.se.cs.com/getAttractions";
        String METHOD_NAME = "getAttractions";
        String NAMESPACE = "http://main.ta.se.cs.com/";
        String URL = "http://10.0.2.2:7101/SoftwareEngineeringHostServices-ViewController-context-root/TouristAssistServicePort?wsdl";

        try {
            SoapObject Request = new SoapObject(NAMESPACE, METHOD_NAME);
            Request.addProperty("arg0" ,getCel);

            SoapSerializationEnvelope soapEnvelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
            soapEnvelope.setOutputSoapObject(Request);

            HttpTransportSE transport = new HttpTransportSE(URL);

            transport.call(SOAP_ACTION, soapEnvelope);

            SoapObject soapObject = (SoapObject) soapEnvelope.getResponse();
            attractions = parseAttractions(soapObject);

            String re = attractions[0].getAttractionDetails();

            Log.i(TAG, "Result : " + re);
        } catch (Exception ex) {
            Log.e(TAG, "Error: " + ex.getMessage());
        }
    }

    public void retrieveReviews() {
        String SOAP_ACTION = "http://main.ta.se.cs.com/getReviews";
        String METHOD_NAME = "getReviews";
        String NAMESPACE = "http://main.ta.se.cs.com/";
        String URL = "http://10.0.2.2:7101/SoftwareEngineeringHostServices-ViewController-context-root/TouristAssistServicePort?wsdl";

        try {
            SoapObject Request = new SoapObject(NAMESPACE, METHOD_NAME);
            Request.addProperty("arg0" ,getCel);

            SoapSerializationEnvelope soapEnvelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
            soapEnvelope.setOutputSoapObject(Request);

            HttpTransportSE transport = new HttpTransportSE(URL);

            transport.call(SOAP_ACTION, soapEnvelope);

            SoapObject soapObject = (SoapObject) soapEnvelope.getResponse();
            reviews = parseReviews(soapObject);

            String re = soapObject.getProperty(0).toString();
            // System.out.println("*************" +re);


            Log.i(TAG, "Result : " + re);
        } catch (Exception ex) {
            Log.e(TAG, "Error: " + ex.getMessage());
        }
    }

    public static Hotel[] parseHotels(SoapObject soap)
    {
        Hotel[] hotels = new Hotel[soap.getPropertyCount()];
        for (int i = 0; i < hotels.length; i++) {
            SoapObject pii = (SoapObject)soap.getProperty(i);
            Hotel hotel = new Hotel();

            hotel.setCity(pii.getProperty(0).toString());
            hotel.setCityId(pii.getProperty(1).toString());
            hotel.setCoordinates(pii.getProperty(2).toString());
            hotel.setHotelAddress(pii.getProperty(3).toString());
            hotel.setHotelDetails(pii.getProperty(4).toString());
            hotel.setHotelId(pii.getProperty(5).toString());
            hotel.setHotelName(pii.getProperty(6).toString());

            hotels[i] = hotel;
        }
        return hotels;
    }

    public static Showtime[] parseShowtimes(SoapObject soap)
    {
        Showtime[] showtimes = new Showtime[soap.getPropertyCount()];
        for (int i = 0; i < showtimes.length; i++) {
            SoapObject pii = (SoapObject)soap.getProperty(i);
            Showtime showtime = new Showtime();

            showtime.setCityId(pii.getProperty(0).toString());
            showtime.setShotimeCity(pii.getProperty(1).toString());
            showtime.setShowtimeAddress(pii.getProperty(2).toString());
            showtime.setShowtimeCoordinates(pii.getProperty(3).toString());
            showtime.setShowtimeDetails(pii.getProperty(4).toString());
            showtime.setShowtimeDuration(pii.getProperty(5).toString());
            showtime.setShowtimeId(pii.getProperty(6).toString());
            showtime.setShowtimeName(pii.getProperty(7).toString());
            showtime.setShowtimeShows(pii.getProperty(8).toString());
            showtimes[i] = showtime;
        }
        return showtimes;
    }

    public static Restaurant[] parseRestaurants(SoapObject soap)
    {
        Restaurant[] restaurants = new Restaurant[soap.getPropertyCount()];
        for (int i = 0; i < restaurants.length; i++) {
            SoapObject pii = (SoapObject)soap.getProperty(i);
            Restaurant restaurant = new Restaurant();

            restaurant.setCity(pii.getProperty(0).toString());
            restaurant.setCityId(pii.getProperty(1).toString());
            restaurant.setCoordinates(pii.getProperty(2).toString());
            restaurant.setRestaurantAddress(pii.getProperty(3).toString());
            restaurant.setRestaurantCusines(pii.getProperty(4).toString());
            restaurant.setRestaurantDetails(pii.getProperty(5).toString());
            restaurant.setRestaurantId(pii.getProperty(6).toString());
            restaurant.setRestaurantName(pii.getProperty(7).toString());
            restaurants[i] = restaurant;
        }
        return restaurants;
    }

    public static Mall[] parseMalls(SoapObject soap)
    {
        Mall[] malls = new Mall[soap.getPropertyCount()];
        for (int i = 0; i < malls.length; i++) {
            SoapObject pii = (SoapObject)soap.getProperty(i);
            Mall mall = new Mall();

            mall.setCity(pii.getProperty(0).toString());
            mall.setCityId(pii.getProperty(1).toString());
            mall.setCoordinates(pii.getProperty(2).toString());
            mall.setMallAddress(pii.getProperty(3).toString());
            mall.setMallDetails(pii.getProperty(4).toString());
            mall.setMallId(pii.getProperty(5).toString());
            mall.setMallName(pii.getProperty(6).toString());
            mall.setMall_brands(pii.getProperty(7).toString());
            mall.setMall_stores(pii.getProperty(8).toString());
            malls[i] = mall;
        }
        return malls;
    }

    public static Hospital[] parseHospitals(SoapObject soap)
    {
        Hospital[] hospitals = new Hospital[soap.getPropertyCount()];
        for (int i = 0; i < hospitals.length; i++) {
            SoapObject pii = (SoapObject)soap.getProperty(i);
            Hospital hospital = new Hospital();

            hospital.setCity(pii.getProperty(0).toString());
            hospital.setCityId(pii.getProperty(1).toString());
            hospital.setCoordinates(pii.getProperty(2).toString());
            hospital.setHospitalAddress(pii.getProperty(3).toString());
            hospital.setHospitalDetails(pii.getProperty(4).toString());
            hospital.setHospitalId(pii.getProperty(5).toString());
            hospital.setHospitalName(pii.getProperty(6).toString());
            hospital.setSpecializations(pii.getProperty(7).toString());
            hospitals[i] = hospital;

        }
        return hospitals;
    }

    public static Club[] parseClubs(SoapObject soap)
    {
        Club[] clubs = new Club[soap.getPropertyCount()];
        for (int i = 0; i < clubs.length; i++) {
            SoapObject pii = (SoapObject)soap.getProperty(i);
            Club club = new Club();

            club.setCity(pii.getProperty(0).toString());
            club.setCityId(pii.getProperty(1).toString());
            club.setClubAddress(pii.getProperty(2).toString());
            club.setClubDetails(pii.getProperty(3).toString());
            club.setClubId(pii.getProperty(4).toString());
            club.setClubName(pii.getProperty(5).toString());
            club.setClub_disc(pii.getProperty(6).toString());
            club.setCoordinates(pii.getProperty(7).toString());
            clubs[i]=club;
        }
        return clubs;
    }
    public static Review[] parseReviews(SoapObject soap)
    {
        Review[] reviews = new Review[soap.getPropertyCount()];
        for (int i = 0; i < reviews.length; i++) {
            SoapObject pii = (SoapObject)soap.getProperty(i);
            Review review = new Review();

            review.setEntityId(pii.getProperty(0).toString());
            review.setRating(pii.getProperty(1).toString());
            review.setReview(pii.getProperty(2).toString());
            review.setReviewId(pii.getProperty(3).toString());
            reviews[i] = review;

        }
        return reviews;
    }

    public static Attraction[] parseAttractions(SoapObject soap)
    {
        Attraction[] attractions = new Attraction[soap.getPropertyCount()];
        for (int i = 0; i < attractions.length; i++) {
            SoapObject pii = (SoapObject)soap.getProperty(i);
            Attraction attraction = new Attraction();

            attraction.setAttractionId(pii.getProperty(0).toString());
            attraction.setAttractionName(pii.getProperty(1).toString());
            attraction.setAttractionAddress(pii.getProperty(2).toString());
            attraction.setAttractionDetails(pii.getProperty(3).toString());
            attraction.setCoordinates(pii.getProperty(4).toString());
            attraction.setFee(pii.getProperty(5).toString());
            attraction.setCity(pii.getProperty(6).toString());
            attraction.setCityId(pii.getProperty(7).toString());

            attractions[i] = attraction;

        }
        return attractions;
    }

}