package com.android.samay.volleydemo.util;

import android.graphics.Bitmap;
import android.util.LruCache;

import com.android.volley.toolbox.ImageLoader;

/**
 * Created by samay on 5/8/15.
 */
public class LruImageCache implements ImageLoader.ImageCache {
    private LruCache<String,Bitmap> lruCache;
    public LruImageCache() {
        int maxSize=10*1024*1024;
        lruCache=new LruCache<String,Bitmap>(maxSize){
            @Override
            protected int sizeOf(String key, Bitmap value) {
                return value.getRowBytes()*value.getHeight();
            }
        };
    }

    @Override
    public Bitmap getBitmap(String url) {
        return lruCache.get(url);
    }

    @Override
    public void putBitmap(String url, Bitmap bitmap) {
        lruCache.put(url,bitmap);
    }
}
