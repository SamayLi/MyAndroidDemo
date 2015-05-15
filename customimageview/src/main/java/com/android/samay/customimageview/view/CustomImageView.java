package com.android.samay.customimageview.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;

import com.android.samay.customimageview.R;

/**
 * Created by samay on 5/14/15.
 */
public class CustomImageView extends View {

    private int type;
    private static final int TYPE_CIRCLE=0;
    private static final int TYPE_ROUND=1;

    private Bitmap mSrc;
    private int mRadius;
    private int mWidth;
    private int mHeight;

    public CustomImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        TypedArray typedArray=context.getTheme().obtainStyledAttributes(attrs, R.styleable.CustomImageView,defStyleAttr,0);

        int n=typedArray.getIndexCount();
        for(int i=0;i<n;i++){
            int attr= typedArray.getIndex(i);
            switch (attr){
                case  R.styleable.CustomImageView_src:
                    mSrc= BitmapFactory.decodeResource(getResources(),typedArray.getResourceId(attr,0));
                    break;
                case R.styleable.CustomImageView_type:
                    type=typedArray.getInt(attr,0);
                    break;
                case R.styleable.CustomImageView_borderRadius:
                    mRadius=typedArray.getDimensionPixelSize(attr,(int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,10f,getResources().getDisplayMetrics()));
                    break;
            }
        }
        typedArray.recycle();
    }

    public CustomImageView(Context context) {
        this(context, null);
    }

    public CustomImageView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int specMode=MeasureSpec.getMode(widthMeasureSpec);
        int specSize=MeasureSpec.getSize(widthMeasureSpec);
        if(specMode==MeasureSpec.EXACTLY){
            mWidth=specSize;
        }else{
            int desireByImg=getPaddingLeft()+getPaddingRight()+mSrc.getWidth();
            if(specMode==MeasureSpec.AT_MOST){
                mWidth=Math.min(desireByImg,specSize);
            }else {
                mWidth=desireByImg;
            }
        }

        specMode=MeasureSpec.getMode(heightMeasureSpec);
        specSize=MeasureSpec.getSize(heightMeasureSpec);
        if(specMode==MeasureSpec.EXACTLY){
            mHeight=specSize;
        }else{
            int desire=getPaddingTop()+getPaddingBottom()+mSrc.getHeight();
            if(specMode==MeasureSpec.AT_MOST){
                mHeight=Math.min(desire,specSize);
            }else {
                mHeight=desire;
            }
        }

        setMeasuredDimension(mWidth, mHeight);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        switch (type){
            case TYPE_CIRCLE:
                int min=Math.min(mWidth,mHeight);
                mSrc=Bitmap.createScaledBitmap(mSrc,min,min,false);
                canvas.drawBitmap(createCircleImage(mSrc,min),0,0,null);
                break;
            case TYPE_ROUND:
                canvas.drawBitmap(createRoundConerImage(mSrc),0,0,null);
                break;
        }
    }

    private Bitmap createRoundConerImage(Bitmap mSrc) {
        final Paint paint=new Paint();
        paint.setAntiAlias(true);
        Bitmap target=Bitmap.createBitmap(mWidth,mHeight, Bitmap.Config.ARGB_8888);
        Canvas canvas=new Canvas(target);
        RectF rect=new RectF(0,0,mSrc.getWidth(),mSrc.getHeight());
        canvas.drawRoundRect(rect,mRadius,mRadius,paint);
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        canvas.drawBitmap(mSrc,0,0,paint);
        return target;
    }

    private Bitmap createCircleImage(Bitmap mSrc, int min) {
        final Paint paint=new Paint();
        paint.setAntiAlias(true);
        Bitmap target=Bitmap.createBitmap(min,min, Bitmap.Config.ARGB_8888);
        Canvas canvas=new Canvas(target);
        canvas.drawCircle(min/2,min/2,min/2,paint);
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        canvas.drawBitmap(mSrc,0,0,paint);
        return target;
    }

}
