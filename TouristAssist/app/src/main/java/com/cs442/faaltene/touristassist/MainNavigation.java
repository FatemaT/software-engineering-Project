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
import android.util.Log;
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

import org.w3c.dom.Attr;

import models.Attraction;
import models.City;
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
    NavigationView navigationView=null;
    Toolbar toolbar = null;
    /*Hotel[] hotels;
    Showtime[] showtimes;
    Restaurant[] restaurants;
    Mall[] malls;
    Hospital[] hospitals;
    Club[] clubs;
    Attraction[] attractions;
    Review[] reviews;*/
    City citym;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_navigation);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Intent i= getIntent();
        city = (TextView) findViewById(R.id.citytv);
        citym = (City)i.getSerializableExtra("city");
        String citys = citym.getCityName();
        /*hotels = (Hotel[]) i.getSerializableExtra("hotels");
        showtimes = (Showtime[]) i.getSerializableExtra("showtimes");
        restaurants= (Restaurant[]) i.getSerializableExtra("restaurants");
        malls= (Mall[]) i.getSerializableExtra("malls");
        hospitals=(Hospital[]) i.getSerializableExtra("hospitals");
        clubs=(Club[]) i.getSerializableExtra("clubs");
        attractions = (Attraction[]) i.getSerializableExtra("attractions");
        reviews = (Review[]) i.getSerializableExtra("reviews");*/
        //Typeface tf = Typeface.createFromAsset(getAssets(), "future.ttf");
        //city.setTypeface(tf);
        //city.setText("City of " + citys);
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        navigationView = (NavigationView) findViewById(R.id.nav_view);
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
            b.putSerializable("city",citym);
        } else if (id == R.id.nav_bar) {//handle bar fragment
            fragmentClass = bar_frag.class;
            b.putSerializable("city",citym);
        } else if (id == R.id.nav_hotel) {//handle hotel fragment
            fragmentClass = hotel_frag.class;
            b.putSerializable("city",citym);
        } else if (id == R.id.nav_malls) {//handle mall fragment
            fragmentClass = mall_frag.class;
            b.putSerializable("city",citym);
        } else if (id == R.id.nav_museum) {//handle museum fragment
            fragmentClass = museum_frag.class;
            b.putSerializable("city",citym);
        } else if (id == R.id.nav_hospital) {//handle hospital fragment
            fragmentClass = hospital_frag.class;
            b.putSerializable("city",citym);
        } else if (id == R.id.nav_shows) {//handle show fragment
            fragmentClass = show_frag.class;
            b.putSerializable("city",citym);
        } else if (id == R.id.nav_home) {//handle restaurant fragment
            Intent in = new Intent(MainNavigation.this, MainScreen.class);
            in.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(in);
        } else if (id == R.id.nav_exit) {
            this.finish();
            Intent intent = new Intent(Intent.ACTION_MAIN);
            intent.addCategory(Intent.CATEGORY_HOME);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);

        }
        try {
            fragment = (Fragment) fragmentClass.newInstance();
            fragment.setArguments(b);
        } catch (Exception e) {
            e.printStackTrace();
        }
        FragmentManager fragmentManager = getSupportFragmentManager();
        if(fragment != null) {
            fragmentManager.beginTransaction().replace(R.id.content_frame, fragment).commit();

            DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
            drawer.closeDrawer(GravityCompat.START);
        }
        return true;
    }
    public void setActionBarTitle(String title) {
        getSupportActionBar().setTitle(title);
    }
    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
