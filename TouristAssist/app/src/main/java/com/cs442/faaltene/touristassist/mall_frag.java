package com.cs442.faaltene.touristassist;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

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
    Context mContext;
    Mall[] malls;
    Review[] reviews;
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
        reviews = (Review[])getArguments().getSerializable("reviews");
        rootView = inflater.inflate(R.layout.fragment_mall_frag, container, false);
        mal = new ArrayList<String>();
        malls = (Mall[]) getArguments().getSerializable("malls");
        mall = (ListView)rootView.findViewById(R.id.mall_list);
        for (int i = 0; i<malls.length; i++){
            mal.add(malls[i].getMallName());
        }
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
}
