package com.android.samay.recyclerview;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by samay on 5/5/15.
 */
public class MyRecyclerView extends RecyclerView implements RecyclerView.OnScrollListener{

    private View mCurrentView;


    private OnItemScrollChangeListener mItemScrollChangeListener;

    @Override
    public void onScrollStateChanged(int i) {

    }

    @Override
    public void onScrolled(int i, int i1) {
        View newView=getChildAt(0);

        if(mItemScrollChangeListener !=null){
            if(newView !=null && newView !=mCurrentView){
                mCurrentView=newView;
                mItemScrollChangeListener.onChange(mCurrentView,getChildPosition(mCurrentView));
            }
        }
    }

    public interface OnItemScrollChangeListener{
        void onChange(View view,int position);
    }

    public void setmItemScrollChangeListener(OnItemScrollChangeListener mItemScrollChangeListener) {
        this.mItemScrollChangeListener = mItemScrollChangeListener;
    }


    public MyRecyclerView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.setOnScrollListener(this);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
        mCurrentView=getChildAt(0);
        if(mItemScrollChangeListener !=null){
            mItemScrollChangeListener.onChange(mCurrentView,getChildPosition(mCurrentView));
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent e) {
        if(e.getAction()==MotionEvent.ACTION_MOVE){
            if(mItemScrollChangeListener!=null){
                mItemScrollChangeListener.onChange(mCurrentView,getChildPosition(mCurrentView));
            }
        }
        return super.onTouchEvent(e);
    }

}
