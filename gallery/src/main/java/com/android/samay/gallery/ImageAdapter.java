package com.android.samay.gallery;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Gallery;
import android.widget.ImageView;

/**
 * Created by samay on 5/15/15.
 */
public class ImageAdapter extends BaseAdapter{
    private Context mContext;
    private int[] mImageId;

    public ImageAdapter(Context context,int[] mImageId) {
        this.mContext=context;
        this.mImageId=mImageId;
    }

    @Override
    public int getCount() {
        return mImageId.length;
    }

    @Override
    public Object getItem(int position) {
        return mImageId[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ImageView imageView=new ImageView(mContext);
        imageView.setImageResource(mImageId[position]);
        imageView.setScaleType(ImageView.ScaleType.FIT_XY);
        imageView.setLayoutParams(new Gallery.LayoutParams(Gallery.LayoutParams.WRAP_CONTENT,Gallery.LayoutParams.WRAP_CONTENT));
        imageView.setScaleX(0.7f);
        imageView.setScaleY(0.7f);
        return  imageView;
    }


    static class ViewHolder{
        ImageView imageView;
    }
}
