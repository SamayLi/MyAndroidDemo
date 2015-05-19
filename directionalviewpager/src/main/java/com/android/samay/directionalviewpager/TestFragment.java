package com.android.samay.directionalviewpager;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.support.v4.app.Fragment;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link TestFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link TestFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class TestFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String KEY_CONTENT = "TestFragment:Content";
    private static final String KEY_ISLASTPIC = "TestFragment:IsLastPic";

    // TODO: Rename and change types of parameters
    private int  mContent;
    private boolean  mIsLastPic;


    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment TestFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static TestFragment newInstance(int  mContent, boolean mIsLastPic) {
        TestFragment fragment = new TestFragment();

        fragment.mContent = mContent;
        fragment.mIsLastPic = mIsLastPic;
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if ((savedInstanceState != null)
                && savedInstanceState.containsKey(KEY_CONTENT)) {
            mContent = savedInstanceState.getInt(KEY_CONTENT);
            mIsLastPic = savedInstanceState.getBoolean(KEY_ISLASTPIC);
        }
        View root = inflater
                .inflate(R.layout.fragment_layout, container, false);
        ImageView iv = (ImageView) root.findViewById(R.id.iv);
        iv.setImageResource(mContent);
        Button btn = (Button) root.findViewById(R.id.btn);
        if (mIsLastPic)
            btn.setVisibility(View.VISIBLE);
        else
            btn.setVisibility(View.GONE);
        if (btn.getVisibility() == View.VISIBLE)
            btn.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    // TODO Auto-generated method stub
                }
            });
        return root;
    }


    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(KEY_CONTENT, mContent);
        outState.putBoolean(KEY_ISLASTPIC, mIsLastPic);
    }
}
