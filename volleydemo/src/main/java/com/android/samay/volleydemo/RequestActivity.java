package com.android.samay.volleydemo;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;

import com.android.samay.volleydemo.fragment.ImageRequestFragment;
import com.android.samay.volleydemo.fragment.JsonRequestFragment;
import com.android.samay.volleydemo.fragment.StringRequestFragment;
import com.android.samay.volleydemo.util.Constants;

/**
 * Created by samay on 5/6/15.
 */
public class RequestActivity extends FragmentActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        int frIndex = getIntent().getIntExtra(Constants.Extra.FRAGMENT_INDEX, 0);
        Fragment fr;
        String tag;
        int titleRes;
        switch (frIndex) {
            default:
            case StringRequestFragment.INDEX:
                tag = StringRequestFragment.class.getSimpleName();
                fr = getSupportFragmentManager().findFragmentByTag(tag);
                if (fr == null) {
                    fr = new StringRequestFragment();
                }
                titleRes = R.string.string_request;
                break;
            case JsonRequestFragment.INDEX:
                tag=JsonRequestFragment.class.getSimpleName();
                fr=getSupportFragmentManager().findFragmentByTag(tag);
                if(fr==null){
                    fr=new JsonRequestFragment();
                }
                titleRes =R.string.json_request;
                break;
            case ImageRequestFragment.INDEX:
                tag= ImageRequestFragment.class.getSimpleName();
                fr=getSupportFragmentManager().findFragmentByTag(tag);
                if(fr==null){
                    fr=new ImageRequestFragment();
                }
                titleRes=R.string.image_request;
                break;
        }

        setTitle(titleRes);
        getSupportFragmentManager().beginTransaction().replace(android.R.id.content, fr, tag).commit();
    }
}
