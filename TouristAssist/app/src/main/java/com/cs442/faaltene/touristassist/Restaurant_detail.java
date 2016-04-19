package com.cs442.faaltene.touristassist;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import org.ksoap2.serialization.SoapObject;

import java.util.ArrayList;

import models.Restaurant;
import models.Review;

public class Restaurant_detail extends AppCompatActivity {
    Restaurant[] restaurant;
    Review[] reviews;
    TextView rname;
    TextView rad;
    TextView rinfo;
    ListView rrev;
    TextView rating;
    TextView review;
    ArrayList<Review> rev;
    ArrayAdapter<Review> revad;
    TextView rcui;


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
        String cuisine=intent.getStringExtra("RCui");
        String coord=intent.getStringExtra("RCoord");
        String rid = intent.getStringExtra("Rid");
        rev = new ArrayList<Review>();
        rname = (TextView) findViewById(R.id.rname);
        rname.setText(name);
        rad = (TextView) findViewById(R.id.rad);
        rad.setText(add);
        rinfo = (TextView) findViewById(R.id.rinfo);
        rinfo.setText(info);
        rcui = (TextView) findViewById(R.id.rCui);
        rcui.setText(cuisine);
        rrev = (ListView)findViewById(R.id.rrev);
        for(int i = 0; i<reviews.length; i++){
            if(reviews[i].getEntityId().equalsIgnoreCase(rid)){
                rev.add(reviews[i]);
            }
        }
        if (!rev.isEmpty()){
            rrev.setAdapter(new ArrayAdapter<Review>(this,
                    R.layout.list_item, rev) {

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


            });

        }else{
            rrev.setVisibility(View.GONE);
        }





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


}
