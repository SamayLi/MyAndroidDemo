package com.android.samay.canpullview.view;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Scroller;

/**
 * Created by samay on 5/14/15.
 */
public class MyCanPullView extends ViewGroup {

    private static final int MIN_VIEW_HEIGHT = 200;
    private static final int MIN_VIEW_WIDTH = 400;

    private static final String TAG = "MyCanPullView";

    private int mViewHeight;
    private int mViewWidth;

    private View mTopView;
    private View mBottomView;
    private View mCurrentView;
    private View mLeftView;
    private View mRightView;

    private Scroller mScroller;

    public MyCanPullView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
        mScroller = new Scroller(context);
    }

    private void initView(Context context) {
        setTopView(context);
        setBottomView(context);
        setLeftView(context);
        setRightView(context);
        setCurrentView(context);

    }

    private void setCurrentView(Context context) {
        View currentView = new View(context);
        currentView.setBackgroundColor(Color.WHITE);
        mCurrentView = currentView;
        this.addView(mCurrentView);

    }

    private void setTopView(Context context) {
        View topView = new View(context);
        topView.setBackgroundColor(Color.RED);
        mTopView = topView;
        this.addView(mTopView);
    }

    private void setBottomView(Context context) {
        View bottomView = new View(context);
        bottomView.setBackgroundColor(Color.GREEN);
        mBottomView = bottomView;
        this.addView(mBottomView);

    }

    private void setRightView(Context context) {
        View rightView = new View(context);
        rightView.setBackgroundColor(Color.BLUE);
        mRightView = rightView;
        this.addView(mRightView);

    }

    private void setLeftView(Context context) {
        View leftView = new View(context);
        leftView.setBackgroundColor(Color.YELLOW);
        mLeftView = leftView;
        this.addView(mLeftView);
    }


    public MyCanPullView(Context context) {
        this(context, null);
    }

    public MyCanPullView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }


    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        mTopView.layout(0, -mViewHeight, mViewWidth, 0);
        mBottomView.layout(0, mViewHeight, mViewWidth, 2 * mViewHeight);
        mCurrentView.layout(0, 0, mViewWidth, mViewHeight);
        mLeftView.layout(-mViewWidth, 0, 0, mViewHeight);
        mRightView.layout(mViewWidth, 0, 2 * mViewWidth, mViewHeight);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        mViewWidth = MeasureSpec.getSize(widthMeasureSpec);
        mViewHeight = MeasureSpec.getSize(heightMeasureSpec);
    }


    private float mDownX;
    private float mDownY;

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int disX;
        int disY;
        float eventX = event.getX();
        float eventY = event.getY();
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                mDownX = eventX;
                mDownY = eventY;
                break;
            case MotionEvent.ACTION_UP:
                disX = (int) (eventX - mDownX);
                disY = (int) (eventY - mDownY);
                if (Math.abs(disY) > Math.abs(disX)) {
                    if (Math.abs(disY) > MIN_VIEW_HEIGHT / 2) {
                        if (disY > 0) {
                            Log.d(TAG,"TO_BOTTOM");
                            changeToBottom();
                        } else {
                            Log.d(TAG,"TO_TOP");
                            changeToTop();
                        }
                    }
                } else {
                    if(Math.abs(disX)>MIN_VIEW_WIDTH/2){
                        if(disX>0){
                            Log.d(TAG,"TO_RIGHT");
                            changeToRight();
                        }else {
                            Log.d(TAG,"TO_LEFT");
                            changeToLeft();
                        }
                    }
                }
                break;
            default:
                break;
        }

        return true;
    }

    private void changeToRight() {
        int[] location=new int[2];
        mCurrentView.getLocationOnScreen(location);
        if(location[0]<=-mViewHeight-MIN_VIEW_HEIGHT/2) return;
        int dx=mViewWidth-MIN_VIEW_WIDTH;
        mScroller.startScroll(getScrollX(),0,-dx,0,500);
        invalidate();

    }

    private void changeToLeft() {
        int[] location=new int[2];
        mLeftView.getLocationOnScreen(location);
        if(location[0]<=-mViewWidth-MIN_VIEW_WIDTH/2) return;
        int dx=mViewWidth-MIN_VIEW_WIDTH;
        mScroller.startScroll(getScrollX(),0,dx,0,500);
        invalidate();
    }

    private void changeToTop() {
        int[] location=new int[2];
        mTopView.getLocationOnScreen(location);
        if(location[1]<=-mViewHeight-MIN_VIEW_HEIGHT/2) return;
        int dy=(int)(mViewHeight-MIN_VIEW_HEIGHT);
        mScroller.startScroll(0,getScrollY(),0,dy,500);
        invalidate();
    }

    private void changeToBottom() {
        int[] location=new int[2];
        mCurrentView.getLocationOnScreen(location);
        if(location[1]>=mViewWidth-MIN_VIEW_HEIGHT*2) return;
        int dy=(int)(mViewHeight-MIN_VIEW_HEIGHT);
        mScroller.startScroll(0,getScrollY(),0,-dy,500);
        invalidate();
    }

    @Override
    public void computeScroll() {
        super.computeScroll();
        if(mScroller.computeScrollOffset()){
            scrollTo(mScroller.getCurrX(),mScroller.getCurrY());
            postInvalidate();
        }
    }
}
