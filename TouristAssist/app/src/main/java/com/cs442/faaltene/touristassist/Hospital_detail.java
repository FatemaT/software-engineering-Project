package com.cs442.faaltene.touristassist;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.InputType;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapPrimitive;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

import java.util.ArrayList;

import models.Hospital;
import models.Restaurant;
import models.Review;

public class Hospital_detail extends AppCompatActivity {

    Hospital[] hospital;
    String TAG = "Response";
    Review[] reviews;
    TextView hname;
    TextView had;
    TextView hinfo;
    ListView hrev;
    TextView rating;
    int hid2;
    Context mContext;
    String hid;
    TextView review;
    ArrayList<Review> rev;
    ArrayAdapter<Review> revad;
    TextView hspec;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hospital_detail);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        Intent intent = getIntent();
        Typeface tf = Typeface.createFromAsset(getAssets(), "DroidSansMono.ttf");
        //reviews = (Review[])intent.getSerializableExtra("reviews");
        hname = (TextView)findViewById(R.id.hname);
        hname.setTypeface(tf);
        had = (TextView)findViewById(R.id.had);
        had.setTypeface(tf);
        hinfo = (TextView)findViewById(R.id.hinfo);
        hinfo.setTypeface(tf);
        hspec = (TextView)findViewById(R.id.hSpec);
        hspec.setTypeface(tf);
        String name = intent.getStringExtra("Hname");
        String add=intent.getStringExtra("Had");
        String info=intent.getStringExtra("Hinfo");
        String specializations=intent.getStringExtra("HSpec");
        String coord=intent.getStringExtra("HCoord");
        hid = intent.getStringExtra("Hid");
        //hid2 = Integer.parseInt(hid);
        rev = new ArrayList<Review>();
        hname = (TextView) findViewById(R.id.hname);
        hname.setText(name);
        had = (TextView) findViewById(R.id.had);
        had.setText(add);
        hinfo = (TextView) findViewById(R.id.hinfo);
        hinfo.setText(info);
        hspec = (TextView) findViewById(R.id.hSpec);
        hspec.setText(specializations);
        //hrev = (ListView)findViewById(R.id.hrev);
        AsyncCallWS task = new AsyncCallWS();
        task.execute();
        //New review button
        Button button = (Button) findViewById(R.id.button_add_review);
        Typeface btf = Typeface.createFromAsset(getAssets(), "green avocado.ttf");
        button.setTypeface(btf);
        if (button != null) {
            button.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    Log.i("Button", "new review pressed");
                    showReviewDialog("");
                }
            });
        }
    }

    /*
        New review zone
     */

    private String rev_Text = "";
    private String rev_Score = "";
    boolean result = false;

    private void showReviewDialog (String previousInput) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("New Review");

        Context context = builder.getContext();
        LinearLayout layout = new LinearLayout(context);
        layout.setOrientation(LinearLayout.VERTICAL);

        final EditText scoreBox = new EditText(context);
        scoreBox.setHint("Score 0 up to 5");
        layout.addView(scoreBox);

        final EditText descriptionBox = new EditText(context);
        descriptionBox.setText(previousInput);
        descriptionBox.setHint("Description");
        layout.addView(descriptionBox);

        builder.setView(layout);


        // Set up the buttons
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                rev_Score = scoreBox.getText().toString();
                rev_Text = descriptionBox.getText().toString();
                if (rev_Score.matches("[\\d]")) {
                    int score = Integer.parseInt(rev_Score);
                    if (score<0 || score>5) {
                        showScoreError();
                        showReviewDialog(rev_Text);
                    }
                    else if (!rev_Text.isEmpty())
                        onReviewSuccess();
                    else {
                        showEmptyReviewError();
                        showReviewDialog(rev_Text);
                    }
                } else {
                    showScoreError();
                    showReviewDialog(rev_Text);
                }
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        builder.show();
    }

    public void showScoreError () {
        Log.i("Regex","not accepted!!");
        Context context = getApplicationContext();
        CharSequence text = "The score input has to be a number between 0 and 5.";
        int duration = Toast.LENGTH_LONG;
        Toast toast = Toast.makeText(context, text, duration);
        toast.show();
    }

    public void showEmptyReviewError() {
        Log.i("Review","is empty!!");
        Context context = getApplicationContext();
        CharSequence text = "The review description cannot be empty.";
        int duration = Toast.LENGTH_LONG;
        Toast toast = Toast.makeText(context, text, duration);
        toast.show();
    }

    public void onReviewSuccess() {
        Log.i("Regex", "accepted");
        AsyncCallWSReview task = new AsyncCallWSReview ();
        task.execute();
    }

    public void onReviewRequestError() {
        Log.i("Add review", "Request Error");
    }

    public void sendReview () {
        String SOAP_ACTION = "http://main.ta.se.cs.com/postReview";
        String METHOD_NAME = "postReview";
        String NAMESPACE = "http://main.ta.se.cs.com/";
        String URL = "http://10.0.2.2:7101/SoftwareEngineeringHostServices-ViewController-context-root/TouristAssistServicePort?wsdl";

        try {
            SoapObject Request = new SoapObject(NAMESPACE, METHOD_NAME);
            //           hid = hid2+"";
            Request.addProperty("arg0" ,hid);
            Request.addProperty("arg1" ,rev_Score);
            Request.addProperty("arg2", rev_Text);

            SoapSerializationEnvelope soapEnvelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
            soapEnvelope.setOutputSoapObject(Request);

            HttpTransportSE transport = new HttpTransportSE(URL);

            transport.call(SOAP_ACTION, soapEnvelope);

            SoapPrimitive soapPrimitive = (SoapPrimitive) soapEnvelope.getResponse();
            String re = soapPrimitive.toString();

            Log.i(TAG, "Result primitive: " + re);
            result = Boolean.valueOf(re);


        } catch (Exception ex) {
            Log.e(TAG, "Error: " + ex.getMessage());
            onReviewRequestError();
        }
    }

    private void addNewReviewToList () {
        Log.i("Enters add new","1");
        if (result) {
            if (revad!=null) revad.clear();
            AsyncCallWS task = new AsyncCallWS ();
            task.execute();
        }
    }

    private class AsyncCallWSReview extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            Log.i(TAG, "onPreExecute");
            //hrev = (ListView)findViewById(R.id.hrev);
        }

        @Override
        protected Void doInBackground(Void... params) {
            Log.i(TAG, "doInBackground");
            sendReview();
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            Log.i(TAG, "onPostExecute");
            addNewReviewToList();
        }

    }

    /* End of review zone */

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
            hrev = (ListView)findViewById(R.id.hrev);
        }

        @Override
        protected Void doInBackground(Void... params) {
            Log.i(TAG, "doInBackground");
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
            mContext = getApplicationContext();
            if (!rev.isEmpty()){
                hrev.setVisibility(View.VISIBLE);
                revad = new ArrayAdapter<Review>(mContext, R.layout.list_item, rev) {

                    @Override
                    public View getView(int position, View convertView, ViewGroup parent) {
                        View v = null;

                        if (v == null) {
                            if (Hospital_detail.this != null) {
                                LayoutInflater vi = (LayoutInflater) Hospital_detail.this
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
                hrev.setAdapter(revad);
                revad.notifyDataSetChanged();
            }else{
                hrev.setVisibility(View.GONE);
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
 //           hid = hid2+"";
            Request.addProperty("arg0" ,hid);

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
