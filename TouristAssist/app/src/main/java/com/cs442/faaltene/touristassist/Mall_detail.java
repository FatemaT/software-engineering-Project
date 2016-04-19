package com.cs442.faaltene.touristassist;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import models.Mall;
import models.Review;

public class Mall_detail extends AppCompatActivity {
    Mall[] mall;
    Review[] reviews;
    TextView mname;
    TextView mad;
    TextView minfo;
    ListView mrev;
    TextView mb;
    TextView ms;
    TextView rating;
    TextView review;
    ArrayList<Review> rev;
    ArrayAdapter<Review> revad;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mall_detail);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Intent intent = getIntent();
        reviews = (Review[])intent.getSerializableExtra("reviews");
        mname = (TextView)findViewById(R.id.mname);
        mad = (TextView)findViewById(R.id.mad);
        minfo = (TextView)findViewById(R.id.minfo);
        mb = (TextView)findViewById(R.id.mB);
        ms = (TextView)findViewById(R.id.mS);
        String name = intent.getStringExtra("Mname");
        String add=intent.getStringExtra("Mad");
        String info=intent.getStringExtra("Minfo");
        String brand=intent.getStringExtra("MBrand");
        String store=intent.getStringExtra("MBrand");
        String coord=intent.getStringExtra("MCoord");
        String mid = intent.getStringExtra("Mid");
        rev = new ArrayList<Review>();
        mname = (TextView) findViewById(R.id.mname);
        mname.setText(name);
        mad = (TextView) findViewById(R.id.mad);
        mad.setText(add);
        minfo = (TextView) findViewById(R.id.minfo);
        minfo.setText(info);
        mb = (TextView) findViewById(R.id.mB);
        mb.setText(brand);
        ms = (TextView) findViewById(R.id.mS);
        ms.setText(store);
        mrev = (ListView)findViewById(R.id.mrev);
        for(int i = 0; i<reviews.length; i++){
            if(reviews[i].getEntityId().equalsIgnoreCase(mid)){
                rev.add(reviews[i]);
            }
        }
        if (!rev.isEmpty()){
            mrev.setAdapter(new ArrayAdapter<Review>(this,
                    R.layout.list_item, rev) {

                @Override
                public View getView(int position, View convertView, ViewGroup parent) {
                    View v = null;
                    if (v == null) {
                        if (Mall_detail.this != null) {
                            LayoutInflater vi = (LayoutInflater) Mall_detail.this
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
            mrev.setVisibility(View.GONE);
        }

    }

}
