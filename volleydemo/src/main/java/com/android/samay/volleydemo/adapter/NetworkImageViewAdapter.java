package com.android.samay.volleydemo.adapter;

import android.content.Context;
import android.widget.ImageView;

import com.android.samay.volleydemo.R;
import com.android.samay.volleydemo.util.LruImageCache;
import com.android.samay.volleydemo.util.StringUtil;
import com.android.samay.volleydemo.util.VolleyUtil;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;

/**
 * Created by samay on 5/8/15.
 */
public class NetworkImageViewAdapter extends ImageBaseAdapter {
    private ImageLoader imageLoader;

    public NetworkImageViewAdapter(Context context, String[] imageArray) {
        super(context, imageArray);
        this.imageLoader = new ImageLoader(VolleyUtil.getQueue(context),new LruImageCache());
    }

    @Override
    int getItemLayout() {
        return R.layout.fr_network_image_view_list_item;
    }

    @Override
    void setImage(ImageView imageView, String imageUrl) {
        NetworkImageView networkImageView=(NetworkImageView)imageView;
        networkImageView.setDefaultImageResId(R.drawable.ic_launcher);
        networkImageView.setErrorImageResId(R.drawable.ic_launcher);
        networkImageView.setImageUrl(StringUtil.perUrl(imageUrl),imageLoader);
    }
}
