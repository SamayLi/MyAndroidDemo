package com.android.samay.volleydemo.util;

import android.content.Context;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

/**
 * Created by samay on 5/6/15.
 */
public class VolleyUtil {
    private volatile static RequestQueue requestQueue;

    public static RequestQueue getQueue(Context context){
        if(requestQueue ==null){
            synchronized (Volley.class){
                requestQueue=Volley.newRequestQueue(context.getApplicationContext());
            }
        }
        return requestQueue;
    }
}
