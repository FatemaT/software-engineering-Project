package com.cs442.faaltene.touristassist;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

import java.util.ArrayList;

import models.Hotel;
import models.Review;
import models.Showtime;

public class Hotel_detail extends AppCompatActivity {
    Hotel[] hotel;
    String TAG = "Response";
    Review[] reviews;
    TextView Hname;
    TextView Had;
    TextView Hinfo;
    ListView Hrev;
    TextView rating;
    int Hid2;
    Context mContext;
    String Hid;
    TextView review;
    ArrayList<Review> rev;
    ArrayAdapter<Review> revad;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hotel_detail);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        Intent intent = getIntent();
        reviews = (Review[])intent.getSerializableExtra("reviews");
        Hname = (TextView)findViewById(R.id.hname);
        Had = (TextView)findViewById(R.id.had);
        Hinfo = (TextView)findViewById(R.id.hinfo);

        String name = intent.getStringExtra("Hname");
        String add=intent.getStringExtra("Had");
        String info=intent.getStringExtra("Hinfo");
        String coord=intent.getStringExtra("Hcoord");
        Hid = intent.getStringExtra("Hid");
       // Hid2 = Integer.parseInt(Hid);
        rev = new ArrayList<Review>();
        Hname = (TextView) findViewById(R.id.hname);
        Hname.setText(name);
        Had = (TextView) findViewById(R.id.had);
        Had.setText(add);
        Hinfo = (TextView) findViewById(R.id.hinfo);
        Hinfo.setText(info);
        Hrev = (ListView)findViewById(R.id.hrev);
        AsyncCallWS task = new AsyncCallWS();
        task.execute();





    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish();
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
            //retrieveShowtimes();
            //retrieveHotels();
            //retrieveRestaurants();
            //retrieveMalls();
            //retrieveHospitals();
            //retrieveClubs();
            //retrieveAttractions();
            retrieveReviews();
            for(int i = 0; i<reviews.length; i++){
                rev.add(reviews[i]);

            }
            //retrieveCity();
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            Log.i(TAG, "onPostExecute");
            //i.putExtra("city", city);
            //i.putExtra("hotels", hotels);
            //i.putExtra("hospitals",hospitals);
            //i.putExtra("showtimes",showtimes);
            //i.putExtra("restaurants", restaurants);
            //i.putExtra("malls",malls);
            //i.putExtra("clubs",clubs);
            //i.putExtra("reviews",reviews);
            //startActivity(i);
            // Toast.makeText(MainActivity.this, "Response" + re, Toast.LENGTH_LONG).show();
            mContext = getApplicationContext();
            if (!rev.isEmpty()){
                Hrev.setVisibility(View.VISIBLE);
                revad  =new ArrayAdapter<Review>(mContext, R.layout.list_item, rev) {

                    @Override
                    public View getView(int position, View convertView, ViewGroup parent) {
                        View v = null;
                        if (v == null) {
                            if (Hotel_detail.this != null) {
                                LayoutInflater vi = (LayoutInflater) Hotel_detail.this
                                        .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                                v = vi.inflate(R.layout.list_item, null);
                            }
                        }

                        rating = (TextView) v.findViewById(R.id.rating);
                        review = (TextView) v.findViewById(R.id.review);

                        rating.setText(rev.get(position).getRating());
                        review.setText(rev.get(position).getReview());

                        return v;
                    }


                };
                Hrev.setAdapter(revad);
                Hrev.setAdapter(revad);
                revad.notifyDataSetChanged();
            }else{
                Hrev.setVisibility(View.GONE);
            }
        }

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
    public void retrieveReviews() {
        String SOAP_ACTION = "http://main.ta.se.cs.com/getReviews";
        String METHOD_NAME = "getReviews";
        String NAMESPACE = "http://main.ta.se.cs.com/";
        String URL = "http://10.0.2.2:7101/SoftwareEngineeringHostServices-ViewController-context-root/TouristAssistServicePort?wsdl";

        try {
            SoapObject Request = new SoapObject(NAMESPACE, METHOD_NAME);
           // Hid = Hid2+"";
            Request.addProperty("arg0" ,Hid);

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
}
