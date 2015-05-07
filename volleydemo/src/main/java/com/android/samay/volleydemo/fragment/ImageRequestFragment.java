package com.android.samay.volleydemo.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import com.android.samay.volleydemo.R;
import com.android.samay.volleydemo.adapter.ImageBaseAdapter;
import com.android.samay.volleydemo.adapter.ImageRequestAdapter;
import com.android.samay.volleydemo.util.Constants;

/**
 * Created by samay on 5/7/15.
 */
public class ImageRequestFragment extends Fragment {

    public final static int INDEX=16;

    private GridView gvCard;

    private String[]  imageUrlArray= Constants.IMAGE_URLS;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view =inflater.inflate(R.layout.fr_image_request,container,false);
        gvCard=(GridView)view.findViewById(R.id.gv_car);
        ImageBaseAdapter adapter=new ImageRequestAdapter(getActivity(),imageUrlArray);
        gvCard.setAdapter(adapter);

        return view;
    }
}
