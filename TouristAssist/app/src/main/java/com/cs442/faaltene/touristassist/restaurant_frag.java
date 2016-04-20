package com.cs442.faaltene.touristassist;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

import java.util.ArrayList;

import models.City;
import models.Restaurant;
import models.Review;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link restaurant_frag.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link restaurant_frag#newInstance} factory method to
 * create an instance of this fragment.
 */
public class restaurant_frag extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    Context mContext;
    String TAG = "Response";
    ListView restaurant;
    int cid;
    String cityname;
    Restaurant[] restaurants;
    Review[] reviews;
    ArrayList<String> rest;
    String cityid;
    View rootView;
    City city;
    ArrayAdapter<String> restad;
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public restaurant_frag() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment restaurant_frag.
     */
    // TODO: Rename and change types and number of parameters
    public static restaurant_frag newInstance(String param1, String param2) {
        restaurant_frag fragment = new restaurant_frag();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        ((MainNavigation) getActivity()).setActionBarTitle("Restaurants");
        Toolbar toolbar = (Toolbar) getActivity().findViewById(R.id.toolbar);
        rootView = inflater.inflate(R.layout.fragment_restaurant_frag, container, false);
        rest = new ArrayList<String>();
        city = (City) getArguments().getSerializable("city");
        cityid = city.getCityId();
        cityname = city.getCityName();
        Log.i(TAG, "Result : " + cityname);
        cid = Integer.parseInt(cityid);
        AsyncCallWS task = new AsyncCallWS();
        task.execute();
        //restaurants = (Restaurant[]) getArguments().getSerializable("restaurants");
        //reviews = (Review[])getArguments().getSerializable("reviews");
/*        restaurant = (ListView)rootView.findViewById(R.id.restaurant_list);
        for (int i = 0; i<restaurants.length; i++){
            rest.add(restaurants[i].getRestaurantName());
        }
        restad = new ArrayAdapter<String>(getContext(),android.R.layout.simple_list_item_1, rest);
        restaurant.setAdapter(restad);
        mContext = getActivity().getApplicationContext();
        restaurant.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent i = new Intent(mContext, Restaurant_detail.class);
                //i.putExtra("reviews",reviews);
                i.putExtra("restaurant",restaurants[position]);
                i.putExtra("Rname",restaurants[position].getRestaurantName());
                i.putExtra("Rinfo",restaurants[position].getRestaurantDetails());
                i.putExtra("Rad",restaurants[position].getRestaurantAddress());
                i.putExtra("RCui",restaurants[position].getRestaurantCusines());
                i.putExtra("RCoord",restaurants[position].getCoordinates());
                i.putExtra("Rid",restaurants[position].getRestaurantId());
                startActivity(i);

            }
        });*/
        return rootView;

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
            retrieveRestaurants();
            //retrieveMalls();
            //retrieveHospitals();
            //retrieveClubs();
            //retrieveAttractions();
            //retrieveReviews();
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
        }

    }
    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
    public void retrieveRestaurants() {
        String SOAP_ACTION = "http://main.ta.se.cs.com/getRestaurants";
        String METHOD_NAME = "getRestaurants";
        String NAMESPACE = "http://main.ta.se.cs.com/";
        String URL = "http://10.0.2.2:7101/SoftwareEngineeringHostServices-ViewController-context-root/TouristAssistServicePort?wsdl";

        try {
            SoapObject Request = new SoapObject(NAMESPACE, METHOD_NAME);
            Request.addProperty("arg0" ,cityname);

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
}
