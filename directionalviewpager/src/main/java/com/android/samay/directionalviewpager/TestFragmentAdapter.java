package com.android.samay.directionalviewpager;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * Created by samay on 5/19/15.
 */
public class TestFragmentAdapter extends FragmentPagerAdapter{

    public static final int[] CONTENT=new int[]{
            R.mipmap.biz_ad_new_version1_img0,
            R.mipmap.biz_ad_new_version1_img1,
            R.mipmap.biz_ad_new_version1_img2,
            R.mipmap.biz_ad_new_version1_img3
    };

    public TestFragmentAdapter(FragmentManager fm){
        super(fm);
    }


    @Override
    public Fragment getItem(int i) {
        boolean isLastPic=false;
        if(i==CONTENT.length-1)
            isLastPic=true;
        return TestFragment.newInstance(CONTENT[i],isLastPic);
    }

    @Override
    public int getCount() {
        return CONTENT.length;
    }
}
