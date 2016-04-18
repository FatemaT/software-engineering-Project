package com.cs442.faaltene.touristassist;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import org.ksoap2.serialization.SoapObject;

import models.Restaurant;
import models.Review;

public class Restaurant_detail extends AppCompatActivity {
    Restaurant[] restaurant;
    TextView rname;
    TextView rad;
    TextView rinfo;
    TextView rcui;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant_detail);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Intent intent = getIntent();
        restaurant[0]= (Restaurant) intent.getSerializableExtra("restaurant");

        rname = (TextView)findViewById(R.id.rname);
        rad = (TextView)findViewById(R.id.rad);
        rinfo = (TextView)findViewById(R.id.rinfo);
        rcui = (TextView)findViewById(R.id.rCui);
        String name = intent.getStringExtra("name");
        String add=intent.getStringExtra("");
        String info=intent.getStringExtra("");
        String cuisine=intent.getStringExtra("");
        String coord=intent.getStringExtra("");





    }
    public static Review[] parseHospitals(SoapObject soap)
    {
        Review[] reviews = new Review[soap.getPropertyCount()];
        for (int i = 0; i < reviews.length; i++) {
            SoapObject pii = (SoapObject)soap.getProperty(i);
            Review review = new Review();

            review.s(pii.getProperty(0).toString());
            review.setCityId(pii.getProperty(1).toString());
            review.setCoordinates(pii.getProperty(2).toString());
            review.setHospitalAddress(pii.getProperty(3).toString());
            review.setHospitalDetails(pii.getProperty(4).toString());
            review.setHospitalId(pii.getProperty(5).toString());
            review.setHospitalName(pii.getProperty(6).toString());
            hospital.setSpecializations(pii.getProperty(7).toString());
            reviews[i] = hospital;

        }
        return reviews;
    }

}
