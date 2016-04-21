package com.cs442.faaltene.touristassist;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
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
import models.Hotel;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link hotel_frag.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link hotel_frag#newInstance} factory method to
 * create an instance of this fragment.
 */
public class hotel_frag extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    ListView hotel;
    Hotel[] hotels;
    String cityid;
    Context mContext;
    City city;
    int cid;
    String cityname;
    String TAG = "Response";
    ArrayList<String> hot;
    ArrayAdapter<String> hotad;
    View rootView;
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public hotel_frag() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment hotel_frag.
     */
    // TODO: Rename and change types and number of parameters
    public static hotel_frag newInstance(String param1, String param2) {
        hotel_frag fragment = new hotel_frag();
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
        city = (City) getArguments().getSerializable("city");
        cityid = city.getCityId();
        cityname = city.getCityName();
        Log.i(TAG, "Result : " + cityname);
        cid = Integer.parseInt(cityid);
        AsyncCallWS task = new AsyncCallWS();
        task.execute();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        ((MainNavigation) getActivity()).setActionBarTitle("Hotels");
        rootView = inflater.inflate(R.layout.fragment_hotel_frag, container, false);
        hot = new ArrayList<String>();
        hotels = (Hotel[]) getArguments().getSerializable("hotels");
        hotel = (ListView)rootView.findViewById(R.id.hotel_list);

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
            retrieveHotels();
            for (int i = 0; i<hotels.length; i++){
                hot.add(hotels[i].getHotelName());
            }
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
            hotad = new ArrayAdapter<String>(getContext(),android.R.layout.simple_list_item_1, hot);
            hotel.setAdapter(hotad);
            mContext = getActivity().getApplicationContext();
            hotel.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Intent i = new Intent(mContext, Hotel_detail.class);
                    //i.putExtra("reviews",reviews);
                    i.putExtra("hotel", hotels[position]);
                    i.putExtra("Hname", hotels[position].getHotelName());
                    i.putExtra("Hinfo", hotels[position].getHotelDetails());
                    i.putExtra("Had", hotels[position].getHotelAddress());
                    i.putExtra("Hcoord", hotels[position].getCoordinates());
                    i.putExtra("Hid", hotels[position].getHotelId());
                    startActivity(i);

                }
            });
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

    public static Hotel[] parseHotels(SoapObject soap)
    {
        Hotel[] hotels = new Hotel[soap.getPropertyCount()];
        for (int i = 0; i < hotels.length; i++) {
            SoapObject pii = (SoapObject)soap.getProperty(i);
            Hotel hotel = new Hotel();

            hotel.setCity(pii.getProperty(0).toString());
            hotel.setCityId(pii.getProperty(1).toString());
            hotel.setCoordinates(pii.getProperty(2).toString());
            hotel.setHotelAddress(pii.getProperty(3).toString());
            hotel.setHotelDetails(pii.getProperty(4).toString());
            hotel.setHotelId(pii.getProperty(5).toString());
            hotel.setHotelName(pii.getProperty(6).toString());

            hotels[i] = hotel;
        }
        return hotels;
    }

    public void retrieveHotels() {
        String SOAP_ACTION = "http://main.ta.se.cs.com/getHotels";
        String METHOD_NAME = "getHotels";
        String NAMESPACE = "http://main.ta.se.cs.com/";
        String URL = "http://10.0.2.2:7101/SoftwareEngineeringHostServices-ViewController-context-root/TouristAssistServicePort?wsdl";

        try {
            SoapObject Request = new SoapObject(NAMESPACE, METHOD_NAME);
            cityid = cityid +"";
            Request.addProperty("arg1" ,cityid);

            SoapSerializationEnvelope soapEnvelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
            soapEnvelope.setOutputSoapObject(Request);

            HttpTransportSE transport = new HttpTransportSE(URL);

            transport.call(SOAP_ACTION, soapEnvelope);

            SoapObject soapObject = (SoapObject) soapEnvelope.getResponse();
            hotels = parseHotels(soapObject);

            String re = soapObject.getProperty(0).toString();
            // System.out.println("*************" +re);


            Log.i(TAG, "Result : " + re);
        } catch (Exception ex) {
            Log.e(TAG, "Error: " + ex.getMessage());
        }
    }
}
