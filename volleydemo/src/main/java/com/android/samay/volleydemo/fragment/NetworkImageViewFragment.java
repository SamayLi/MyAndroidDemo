package com.android.samay.volleydemo.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import com.android.samay.volleydemo.R;
import com.android.samay.volleydemo.adapter.ImageBaseAdapter;
import com.android.samay.volleydemo.adapter.NetworkImageViewAdapter;
import com.android.samay.volleydemo.util.Constants;

/**
 * Created by samay on 5/8/15.
 */
public class NetworkImageViewFragment extends Fragment {
    public static final int INDEX=20;

    private String[] imageUrlArray= Constants.IMAGE_URLS;

    private GridView gvCar;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fr_image_request,container,false);
        gvCar=(GridView)view.findViewById(R.id.gv_car);
        ImageBaseAdapter adapter=new NetworkImageViewAdapter(getActivity(),imageUrlArray);
        gvCar.setAdapter(adapter);
        return view;
    }
}
