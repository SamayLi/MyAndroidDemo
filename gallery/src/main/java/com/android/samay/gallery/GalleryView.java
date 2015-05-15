package com.android.samay.gallery;

import android.content.Context;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.widget.Gallery;

/**
 * Created by samay on 5/15/15.
 */
public class GalleryView extends Gallery{
    public GalleryView(Context context) {
        super(context);
    }

    public GalleryView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
        if((e1.getX()-e2.getX())<0.0F){
            onKeyDown(KeyEvent.KEYCODE_DPAD_LEFT,null);
        }else {
            onKeyDown(KeyEvent.KEYCODE_DPAD_RIGHT,null);
        }
        return  true;
    }
}
