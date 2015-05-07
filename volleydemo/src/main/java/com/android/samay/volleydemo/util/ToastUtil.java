package com.android.samay.volleydemo.util;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by samay on 5/7/15.
 */
public class ToastUtil {
    public static void showToast(Context context,String info){
        Toast.makeText(context,info,Toast.LENGTH_LONG).show();
    }
}
