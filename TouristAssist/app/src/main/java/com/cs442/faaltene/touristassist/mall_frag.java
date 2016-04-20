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
import java.util.List;

import models.City;
import models.Mall;
import models.Review;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link mall_frag.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link mall_frag#newInstance} factory method to
 * create an instance of this fragment.
 */
public class mall_frag extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    ListView mall;
    String TAG = "Response";
    Context mContext;
    Mall[] malls;
    Review[] reviews;
    String cityid;
    int cid;
    String cityname;
    City city;
    ArrayList<String> mal;
    ArrayAdapter<String> malad;
    View rootView;
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public mall_frag() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment mall_frag.
     */
    // TODO: Rename and change types and number of parameters
    public static mall_frag newInstance(String param1, String param2) {
        mall_frag fragment = new mall_frag();
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
        ((MainNavigation) getActivity()).setActionBarTitle("Shopping Malls");
        malls = (Mall[]) getArguments().getSerializable("malls");
        rootView = inflater.inflate(R.layout.fragment_mall_frag, container, false);
        mal = new ArrayList<String>();
        mall = (ListView)rootView.findViewById(R.id.mall_list);

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
            //retrieveRestaurants();
            retrieveMalls();
            for (int i = 0; i<malls.length; i++){
                mal.add(malls[i].getMallName());
            }


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
            malad = new ArrayAdapter<String>(getContext(),android.R.layout.simple_list_item_1, mal);
            mall.setAdapter(malad);
            mContext = getActivity().getApplicationContext();
            mall.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Intent i = new Intent(mContext, Mall_detail.class);
                    i.putExtra("reviews", reviews);
                    i.putExtra("restaurant", malls[position]);
                    i.putExtra("Mname", malls[position].getMallName());
                    i.putExtra("Minfo", malls[position].getMallDetails());
                    i.putExtra("Mad", malls[position].getMallAddress());
                    i.putExtra("MBrand", malls[position].getMall_brands());
                    i.putExtra("MStore", malls[position].getMall_stores());
                    i.putExtra("MCoord", malls[position].getCoordinates());
                    i.putExtra("Mid", malls[position].getMallId());
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
    public void retrieveMalls() {
        String SOAP_ACTION = "http://main.ta.se.cs.com/getMalls";
        String METHOD_NAME = "getMalls";
        String NAMESPACE = "http://main.ta.se.cs.com/";
        String URL = "http://10.0.2.2:7101/SoftwareEngineeringHostServices-ViewController-context-root/TouristAssistServicePort?wsdl";

        try {
            SoapObject Request = new SoapObject(NAMESPACE, METHOD_NAME);
            Request.addProperty("arg0" ,cityid);

            SoapSerializationEnvelope soapEnvelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
            soapEnvelope.setOutputSoapObject(Request);

            HttpTransportSE transport = new HttpTransportSE(URL);

            transport.call(SOAP_ACTION, soapEnvelope);

            SoapObject soapObject = (SoapObject) soapEnvelope.getResponse();
            malls = parseMalls(soapObject);

            String re = malls[0].getMall_stores();
            //System.out.println("*************" +re);


            Log.i(TAG, "Result : " + re);
        } catch (Exception ex) {
            Log.e(TAG, "Error: " + ex.getMessage());
        }
    }
    public static Mall[] parseMalls(SoapObject soap)
    {
        Mall[] malls = new Mall[soap.getPropertyCount()];
        for (int i = 0; i < malls.length; i++) {
            SoapObject pii = (SoapObject)soap.getProperty(i);
            Mall mall = new Mall();
            mall.setCity(pii.getProperty(0).toString());
            mall.setCityId(pii.getProperty(1).toString());
            mall.setCoordinates(pii.getProperty(2).toString());
            mall.setMallAddress(pii.getProperty(3).toString());
            mall.setMallDetails(pii.getProperty(4).toString());
            mall.setMallId(pii.getProperty(5).toString());
            mall.setMallName(pii.getProperty(6).toString());
            mall.setMall_brands(pii.getProperty(7).toString());
            mall.setMall_stores(pii.getProperty(8).toString());
            malls[i] = mall;
        }
        return malls;
    }
}
