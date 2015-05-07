package com.android.samay.volleydemo.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.android.samay.volleydemo.R;

/**
 * Created by samay on 5/7/15.
 */
public abstract class ImageBaseAdapter extends BaseAdapter{
    private String[] imageArray;
    private LayoutInflater inflater;

    public ImageBaseAdapter(Context context, String[] imageArray) {
        this.imageArray=imageArray;
        inflater=LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        if (imageArray == null) {
            return 0;
        } else {
            return imageArray.length;
        }
    }

    @Override
    public Object getItem(int position) {
        if(imageArray==null || position>=imageArray.length) {
            return null;
        }else{
            return imageArray[position];
        }
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final ViewHolder viewHolder;
        if(convertView==null){
            convertView=inflater.inflate(getItemLayout(),parent,false);
            viewHolder=new ViewHolder();
            viewHolder.ivCar=(ImageView)convertView.findViewById(R.id.iv_car);
            convertView.setTag(viewHolder);
        }else{
            viewHolder=(ViewHolder)convertView.getTag();
        }
        setImage(viewHolder.ivCar,imageArray[position]);
        return convertView;
    }

    static class ViewHolder{
        ImageView ivCar;
    }

    abstract int getItemLayout();

    abstract void setImage(ImageView imageView,String imageUrl);
}
