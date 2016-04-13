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

import models.Hotel;
import models.Mall;
import models.Restaurant;
import models.Showtime;

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
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            Log.i(TAG, "onPostExecute");
            i.putExtra("hotels",hotels);
            i.putExtra("showtimes",showtimes);
            i.putExtra("restaurants", restaurants);
            i.putExtra("malls",malls);
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


}