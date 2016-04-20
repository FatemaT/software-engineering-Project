package com.cs442.faaltene.touristassist;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
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

import models.Club;
import models.Hospital;
import models.Review;

public class Bar_detail extends AppCompatActivity {

    Club[] club;
    String TAG = "Response";
    Review[] reviews;
    TextView bname;
    TextView bad;
    TextView binfo;
    ListView brev;
    TextView rating;
    int bid2;
    Context mContext;
    String bid;
    TextView review;
    ArrayList<Review> rev;
    ArrayAdapter<Review> bevad;
    TextView bDisco;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bar_detail);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        Intent intent = getIntent();
        reviews = (Review[])intent.getSerializableExtra("reviews");
        bname = (TextView)findViewById(R.id.bname);
        bad = (TextView)findViewById(R.id.bad);
        binfo = (TextView)findViewById(R.id.binfo);
        bDisco = (TextView)findViewById(R.id.bDisco);
        String name = intent.getStringExtra("Cname");
        String add=intent.getStringExtra("Cad");
        String info=intent.getStringExtra("Cinfo");
        String disco=intent.getStringExtra("CDisco");
        String coord=intent.getStringExtra("CCoord");
        bid = intent.getStringExtra("Cid");
        bid2 = Integer.parseInt(bid);
        rev = new ArrayList<Review>();
        bname = (TextView) findViewById(R.id.bname);
        bname.setText(name);
        bad = (TextView) findViewById(R.id.bad);
        bad.setText(add);
        binfo = (TextView) findViewById(R.id.binfo);
        binfo.setText(info);
        bDisco = (TextView) findViewById(R.id.bDisco);
        bDisco.setText("Disco?: " + disco);
        brev = (ListView)findViewById(R.id.brev);
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
            brev = (ListView)findViewById(R.id.brev);
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
            Log.i("Enters","1");
            if (!rev.isEmpty()){
                Log.i("Enters","2");
                brev.setVisibility(View.VISIBLE);
                bevad = new ArrayAdapter<Review>(mContext, R.layout.list_item, rev) {

                    @Override
                    public View getView(int position, View convertView, ViewGroup parent) {
                        View v = null;

                        if (v == null) {
                            if (Bar_detail.this != null) {
                                LayoutInflater vi = (LayoutInflater) Bar_detail.this
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
                brev.setAdapter(bevad);
                bevad.notifyDataSetChanged();
            }else{
                brev.setVisibility(View.GONE);
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
           // bid = bid2+"";
            Request.addProperty("arg0" ,bid);

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
