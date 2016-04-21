package com.cs442.faaltene.touristassist;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.test.RenamingDelegatingContext;
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

import models.Restaurant;
import models.Review;

public class Restaurant_detail extends AppCompatActivity {
    Restaurant[] restaurant;
    String TAG = "Response";
    Review[] reviews;
    TextView rname;
    TextView rad;
    TextView rinfo;
    ListView rrev;
    TextView rating;
    int rid2;
    Context mContext;
    String rid;

    TextView review;
    ArrayList<Review> rev;
    ArrayAdapter<Review> revad;
    TextView rcui;
    TextView rcuitv;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant_detail);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        Intent intent = getIntent();
        reviews = (Review[])intent.getSerializableExtra("reviews");
        rname = (TextView)findViewById(R.id.rname);
        rad = (TextView)findViewById(R.id.rad);
        rinfo = (TextView)findViewById(R.id.rinfo);
        rcui = (TextView)findViewById(R.id.rCui);
        String name = intent.getStringExtra("Rname");
        String add=intent.getStringExtra("Rad");
        String info=intent.getStringExtra("Rinfo");
        Typeface tf = Typeface.createFromAsset(getAssets(), "DroidSansMono.ttf");
        String cuisine=intent.getStringExtra("RCui");
        String coord=intent.getStringExtra("RCoord");
        rid = intent.getStringExtra("Rid");
        //rid2 = Integer.parseInt(rid);
        rev = new ArrayList<Review>();
        rname = (TextView) findViewById(R.id.rname);
        rname.setText(name);
        rname.setTypeface(tf);
        rad = (TextView) findViewById(R.id.rad);
        rad.setTypeface(tf);
        rad.setText(add);
        rinfo = (TextView) findViewById(R.id.rinfo);
        rinfo.setTypeface(tf);
        rinfo.setText(info);
        rcui = (TextView) findViewById(R.id.rCui);
        rcuitv = (TextView) findViewById(R.id.rCuitv);
        rcui.setTypeface(tf);
        rcuitv.setTypeface(tf);
        rcui.setText(cuisine);

        rrev = (ListView)findViewById(R.id.rrev);
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
                rrev.setVisibility(View.VISIBLE);
                revad = new ArrayAdapter<Review>(mContext, R.layout.list_item, rev) {

                    @Override
                    public View getView(int position, View convertView, ViewGroup parent) {
                        View v = null;

                        if (v == null) {
                            if (Restaurant_detail.this != null) {
                                LayoutInflater vi = (LayoutInflater) Restaurant_detail.this
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
                rrev.setAdapter(revad);
                rrev.setAdapter(revad);
                revad.notifyDataSetChanged();
            }else{
                rrev.setVisibility(View.GONE);
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
            //rid = rid2+"";
            Request.addProperty("arg0" ,rid);

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
