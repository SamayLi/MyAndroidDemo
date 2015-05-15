package com.android.samay.pulltorefreshlistview;

import android.content.Context;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.Properties;

/**
 * Created by samay on 5/15/15.
 */
public class PullListVIewHeader extends LinearLayout{
    private final static String TAG="PullListVIewHeader";
    private LinearLayout mContainer;
    private ImageView mArrowImageView;
    private ProgressBar mProgressBar;
    private TextView mHintTextView;
    private int mState=STATE_NORMAL;

    private TextView mHeaderTimeView;

    private Animation mRotateUpAnim;
    private Animation mRotateDownAnim;

    private final int ROTATE_ANIM_DURATION=180;

    public final static int STATE_NORMAL=0;
    public final static int STATE_RELEASE_TO_REFRESH=1;
    public final static int STATE_REFREHING=2;
    public PullListVIewHeader(Context context) {
        this(context, null);
    }

    public PullListVIewHeader(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    private void initView(Context context) {
        LinearLayout.LayoutParams lp=new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,0);
        mContainer=(LinearLayout)LayoutInflater.from(context).inflate(R.layout.listview_header,null);
        addView(mContainer,lp);
        setGravity(Gravity.BOTTOM);

        mArrowImageView=(ImageView)findViewById(R.id.listview_header_arrow);
        mHintTextView=(TextView)findViewById(R.id.listview_header_hint_textview);
        mProgressBar=(ProgressBar)findViewById(R.id.listview_header_progressbar);

        mRotateUpAnim=new RotateAnimation(0.0f,-180.0f,Animation.RELATIVE_TO_SELF,0.5f,Animation.RELATIVE_TO_SELF,0.5f);
        mRotateUpAnim.setDuration(ROTATE_ANIM_DURATION);
        mRotateUpAnim.setFillAfter(true);
        mRotateUpAnim=new RotateAnimation(-180.0f,0.0f,Animation.RELATIVE_TO_SELF,0.5f,Animation.RELATIVE_TO_SELF,0.5f);
        mRotateDownAnim.setFillAfter(true);
        mHeaderTimeView=(TextView)findViewById(R.id.listview_header_time);
    }


    public void setState(int state){
        if(state==mState) return;
        if(state==STATE_REFREHING){
            mArrowImageView.clearAnimation();
            mArrowImageView.setVisibility(View.INVISIBLE);
            mProgressBar.setVisibility(View.VISIBLE);
        }else {
            mArrowImageView.setVisibility(View.VISIBLE);
            mProgressBar.setVisibility(View.INVISIBLE);
        }
        switch (state){
            case STATE_NORMAL:
                if(mState==STATE_RELEASE_TO_REFRESH){
                    mArrowImageView.startAnimation(mRotateDownAnim);
                }

                if(mState==STATE_REFREHING){
                    mArrowImageView.clearAnimation();
                }
                mHintTextView.setText(R.string.listview_footer_hint_normal);
                break;
            case STATE_RELEASE_TO_REFRESH:
                if(mState!=STATE_RELEASE_TO_REFRESH){
                    mArrowImageView.clearAnimation();
                    mArrowImageView.startAnimation(mRotateUpAnim);
                    mHintTextView.setText(R.string.listview_header_hint_release);
                }
                break;
            case STATE_REFREHING:
                mHintTextView.setText(R.string.listview_header_hint_loading);
                break;
            default:
        }
        mState=state;
    }

    public void setRefreshTime(String time){
        mHeaderTimeView.setText(time);
    }

    public void setVisiableHeight(int height){
        if(height<0){
            height=0;
        }
        LinearLayout.LayoutParams lp=(LinearLayout.LayoutParams)mContainer.getLayoutParams();
        mContainer.setLayoutParams(lp);
    }

    public int getVisiableHeight(){
        return mContainer.getHeight();
    }
}
