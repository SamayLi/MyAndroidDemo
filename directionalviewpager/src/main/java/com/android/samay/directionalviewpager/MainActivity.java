package com.android.samay.directionalviewpager;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;

public class MainActivity extends FragmentActivity implements ViewPager.OnPageChangeListener{

    private DirectionalViewPager mDirectionalViewPager;
    private int mSize;
    private int mCurrentItem;
    private ImageView mBg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mDirectionalViewPager=(DirectionalViewPager)findViewById(R.id.pager);
        mBg=(ImageView)findViewById(R.id.mainBgImage);
        mDirectionalViewPager.setAdapter(new TestFragmentAdapter(getSupportFragmentManager()));
        mDirectionalViewPager.setOrientation(DirectionalViewPager.VERTICAL);
        mDirectionalViewPager.setOnPageChangeListener(this);
        int screenHeight=getScreenHeigh();

        mSize=(int)(screenHeight*0.05f);


        mCurrentItem=0;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public int getStatusBarHeight(){
        try{
            Class mainClass = Class.forName("com.android.internal.R$dimen");
            Object localObject = mainClass.newInstance();
            int statusBarHeightId = Integer.parseInt(mainClass
                    .getField("status_bar_height").get(localObject).toString());
            int statusBarHeight = getResources().getDimensionPixelSize(
                    statusBarHeightId);
            return statusBarHeight;
        }catch (Exception e){
            e.printStackTrace();
        }
        return getResources().getDimensionPixelSize(R.dimen.status_bar_height);
    }

    public int getScreenHeigh() {
        DisplayMetrics dm = getResources().getDisplayMetrics();
        return dm.heightPixels;
    }

    @Override
    public void onPageScrollStateChanged(int state) {
        Log.i("shaohua", "onPageScrollStateChanged...  state = " + state
                + ",  mCurrentItem = " + mCurrentItem);
        if (state == ViewPager.SCROLL_STATE_IDLE) {
            mBg.setY(-mCurrentItem * mSize);
        }
    }

    @Override
    public void onPageScrolled(int position, float positionOffset,
                               int positionOffsetPixels) {
        Log.i("shaohua", "onPageScrolled...  position=" + position
                + ", positionOffset=" + positionOffset
                + ", positionOffsetPixels=" + positionOffsetPixels);
        if (positionOffset == 0.0f)
            return;
        mBg.setY(-((position + positionOffset) * mSize));
    }

    @Override
    public void onPageSelected(int position) {
        Log.i("shaohua", "onPageSelected....  position=" + position);
        mCurrentItem = position;
    }
}
