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
import models.Hospital;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link hospital_frag.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link hospital_frag#newInstance} factory method to
 * create an instance of this fragment.
 */
public class hospital_frag extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    Context mContext;
    ListView hospital;
    Hospital[] hospitals;
    ArrayList<String> hos;
    ArrayAdapter<String> hosad;
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

    public hospital_frag() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment hospital_frag.
     */
    // TODO: Rename and change types and number of parameters
    public static hospital_frag newInstance(String param1, String param2) {
        hospital_frag fragment = new hospital_frag();
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
        ((MainNavigation) getActivity()).setActionBarTitle("Hospitals");
        rootView = inflater.inflate(R.layout.fragment_hospital_frag, container, false);
        hos = new ArrayList<String>();
        hospitals = (Hospital[]) getArguments().getSerializable("hospitals");

        hospital = (ListView)rootView.findViewById(R.id.hospital_list);

        for (int i = 0; i<hospitals.length; i++){
            hos.add(hospitals[i].getHospitalName());
        }
        hosad = new ArrayAdapter<String>(getContext(),android.R.layout.simple_list_item_1, hos);
        hospital.setAdapter(hosad);
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

    public void retrieveHospitals() {
        String SOAP_ACTION = "http://main.ta.se.cs.com/getHospitals";
        String METHOD_NAME = "getHospitals";
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
            hospitals = parseHospitals(soapObject);

            String re = hospitals[0].getSpecializations();

            Log.i(TAG, "Result : " + re);
        } catch (Exception ex) {
            Log.e(TAG, "Error: " + ex.getMessage());
        }
    }

    public static Hospital[] parseHospitals(SoapObject soap)
    {
        Log.i("parseHosps",soap.getPropertyCount()+"");
        Hospital[] hospitals = new Hospital[soap.getPropertyCount()];
        for (int i = 0; i < hospitals.length; i++) {
            SoapObject pii = (SoapObject)soap.getProperty(i);
            Hospital hospital = new Hospital();

            hospital.setCity(pii.getProperty(0).toString());
            hospital.setCityId(pii.getProperty(1).toString());
            hospital.setCoordinates(pii.getProperty(2).toString());
            hospital.setHospitalAddress(pii.getProperty(3).toString());
            hospital.setHospitalDetails(pii.getProperty(4).toString());
            hospital.setHospitalId(pii.getProperty(5).toString());
            hospital.setHospitalName(pii.getProperty(6).toString());
            hospital.setSpecializations(pii.getProperty(7).toString());
            hospitals[i] = hospital;

        }
        return hospitals;
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
            retrieveHospitals();
            for (int i = 0; i<hospitals.length; i++){
                hos.add(hospitals[i].getHospitalName());
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
            hosad = new ArrayAdapter<String>(getContext(),android.R.layout.simple_list_item_1, hos);
            hospital.setAdapter(hosad);
            mContext = getActivity().getApplicationContext();
            hospital.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Intent i = new Intent(mContext, Restaurant_detail.class);
                    //i.putExtra("reviews",reviews);

                    i.putExtra("hospital", hospitals[position]);
                    i.putExtra("Hname", hospitals[position].getHospitalName());
                    i.putExtra("Hinfo", hospitals[position].getHospitalDetails());
                    i.putExtra("Had", hospitals[position].getHospitalAddress());
                    i.putExtra("HCui", hospitals[position].getSpecializations());
                    i.putExtra("HCoord", hospitals[position].getCoordinates());
                    i.putExtra("Hid", hospitals[position].getHospitalId());
                    startActivity(i);

                }
            });
        }

    }

}
