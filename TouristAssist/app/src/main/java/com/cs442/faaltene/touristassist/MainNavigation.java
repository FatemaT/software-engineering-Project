package com.cs442.faaltene.touristassist;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v4.app.Fragment;
import android.app.FragmentTransaction;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentManager;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import models.Club;
import models.Hospital;
import models.Hotel;
import models.Mall;
import models.Restaurant;
import models.Review;
import models.Showtime;

public class MainNavigation extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener,
        restaurant_frag.OnFragmentInteractionListener,bar_frag.OnFragmentInteractionListener,
        hospital_frag.OnFragmentInteractionListener, hotel_frag.OnFragmentInteractionListener,
        mall_frag.OnFragmentInteractionListener, museum_frag.OnFragmentInteractionListener,
        show_frag.OnFragmentInteractionListener{
        TextView city;
    Hotel[] hotels;
    Showtime[] showtimes;
    Restaurant[] restaurants;
    Mall[] malls;
    Hospital[] hospitals;
    Club[] clubs;
    Review[] reviews;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_navigation);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Intent i= getIntent();
        city = (TextView) findViewById(R.id.citytv);
        String citys = i.getStringExtra("city");
        hotels = (Hotel[]) i.getSerializableExtra("hotels");
        showtimes = (Showtime[]) i.getSerializableExtra("showtimes");
        restaurants= (Restaurant[]) i.getSerializableExtra("restaurants");
        malls= (Mall[]) i.getSerializableExtra("malls");
        hospitals=(Hospital[]) i.getSerializableExtra("hospitals");
        clubs=(Club[]) i.getSerializableExtra("clubs");
        reviews = (Review[]) i.getSerializableExtra("reviews");
        Typeface tf = Typeface.createFromAsset(getAssets(), "future.ttf");
        city.setTypeface(tf);
        city.setText("City of " + citys);
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main_navigation, menu);
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

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        Fragment fragment = null;
        Class fragmentClass = null;
        Bundle b =new Bundle();

        FragmentTransaction ft = getFragmentManager().beginTransaction();
        if (id == R.id.nav_restaurant) {//handle restaurant fragment
            fragmentClass = restaurant_frag.class;
            b.putSerializable("restaurants", restaurants);
            b.putSerializable("reviews",reviews);
        } else if (id == R.id.nav_bar) {//handle bar fragment
            fragmentClass = bar_frag.class;
            b.putSerializable("clubs", clubs);
            b.putSerializable("reviews",reviews);
        } else if (id == R.id.nav_hotel) {//handle hotel fragment
            fragmentClass = hotel_frag.class;
            b.putSerializable("hotels", hotels);
            b.putSerializable("reviews",reviews);
        } else if (id == R.id.nav_malls) {//handle mall fragment
            fragmentClass = mall_frag.class;
            b.putSerializable("malls", malls);
            b.putSerializable("reviews",reviews);
        } else if (id == R.id.nav_museum) {//handle museum fragment
            fragmentClass = museum_frag.class;
        } else if (id == R.id.nav_hospital) {//handle hospital fragment
            fragmentClass = hospital_frag.class;
            b.putSerializable("hospitals", hospitals);
            b.putSerializable("reviews",reviews);
        } else if (id == R.id.nav_shows) {//handle show fragment
            fragmentClass = show_frag.class;
            b.putSerializable("showtimes", showtimes);
            b.putSerializable("reviews",reviews);
        } else if (id == R.id.nav_share) {//handle restaurant fragment

        } else if (id == R.id.nav_send) {

        }
        try {
            fragment = (Fragment) fragmentClass.newInstance();
            fragment.setArguments(b);
        } catch (Exception e) {
            e.printStackTrace();
        }
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.content_frame, fragment).commit();

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
    public void setActionBarTitle(String title) {
        getSupportActionBar().setTitle(title);
    }
    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
