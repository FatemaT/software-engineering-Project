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
import models.Club;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link bar_frag.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link bar_frag#newInstance} factory method to
 * create an instance of this fragment.
 */
public class bar_frag extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    Context mContext;
    ListView club;
    Club[] clubs;
    ArrayList<String> clu;
    ArrayAdapter<String> cluad;
    View rootView;
    City city;
    String cityid;
    String cityname;
    int cid;
    String TAG = "Response";
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public bar_frag() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment bar_frag.
     */
    // TODO: Rename and change types and number of parameters
    public static bar_frag newInstance(String param1, String param2) {
        bar_frag fragment = new bar_frag();
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
            city = (City) getArguments().getSerializable("city");
            cityid = city.getCityId();
            cityname = city.getCityName();
            Log.i(TAG, "Result : " + cityname);
            cid = Integer.parseInt(cityid);
            AsyncCallWS task = new AsyncCallWS();
            task.execute();
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        ((MainNavigation) getActivity()).setActionBarTitle("Bars");

        rootView = inflater.inflate(R.layout.fragment_bar_frag, container, false);
        clu = new ArrayList<String>();

        club = (ListView)rootView.findViewById(R.id.bar_list);

        return rootView;

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

    public void retrieveClubs() {
        String SOAP_ACTION = "http://main.ta.se.cs.com/getClubs";
        String METHOD_NAME = "getClubs";
        String NAMESPACE = "http://main.ta.se.cs.com/";
        String URL = "http://10.0.2.2:7101/SoftwareEngineeringHostServices-ViewController-context-root/TouristAssistServicePort?wsdl";

        try {
            SoapObject Request = new SoapObject(NAMESPACE, METHOD_NAME);
            cityid = cid + "";
            Request.addProperty("arg0",cityid);

            SoapSerializationEnvelope soapEnvelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
            soapEnvelope.setOutputSoapObject(Request);

            HttpTransportSE transport = new HttpTransportSE(URL);

            transport.call(SOAP_ACTION, soapEnvelope);

            SoapObject soapObject = (SoapObject) soapEnvelope.getResponse();
            clubs = parseClubs(soapObject);

            String re = clubs[0].getClubDetails();

            Log.i(TAG, "Result : " + re);
        } catch (Exception ex) {
            Log.e(TAG, "Error: " + ex.getMessage());
        }
    }

    public static Club[] parseClubs(SoapObject soap)
    {
        Club[] clubs = new Club[soap.getPropertyCount()];
        for (int i = 0; i < clubs.length; i++) {
            SoapObject pii = (SoapObject)soap.getProperty(i);
            Club club = new Club();

            club.setCity(pii.getProperty(0).toString());
            club.setCityId(pii.getProperty(1).toString());
            club.setClubAddress(pii.getProperty(2).toString());
            club.setClubDetails(pii.getProperty(3).toString());
            club.setClubId(pii.getProperty(4).toString());
            club.setClubName(pii.getProperty(5).toString());
            club.setClub_disc(pii.getProperty(6).toString());
            club.setCoordinates(pii.getProperty(7).toString());
            clubs[i]=club;
        }
        return clubs;
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
            retrieveClubs();
            for (int i = 0; i<clubs.length; i++){
                clu.add(clubs[i].getClubName());
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
            cluad = new ArrayAdapter<String>(getContext(),android.R.layout.simple_list_item_1, clu);
            club.setAdapter(cluad);
            mContext = getActivity().getApplicationContext();
            club.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Intent i = new Intent(mContext, Bar_detail.class);
                    //i.putExtra("reviews",reviews);

                    i.putExtra("club", clubs[position]);
                    i.putExtra("Cname", clubs[position].getClubName());
                    i.putExtra("Cinfo", clubs[position].getClubDetails());
                    i.putExtra("Cad", clubs[position].getClubAddress());
                    i.putExtra("CDisco", clubs[position].getClub_disc());
                    i.putExtra("CCoord", clubs[position].getCoordinates());
                    i.putExtra("Cid", clubs[position].getClubId());
                    startActivity(i);

                }
            });
        }

    }
}
