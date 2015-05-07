package com.android.samay.volleydemo.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.widget.ImageView;

import com.android.samay.volleydemo.R;
import com.android.samay.volleydemo.util.StringUtil;
import com.android.samay.volleydemo.util.VolleyUtil;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;

/**
 * Created by samay on 5/7/15.
 */
public class ImageRequestAdapter extends ImageBaseAdapter {
    private Context context;

    public ImageRequestAdapter(Context context, String[] imageArray) {
        super(context, imageArray);
        this.context = context;
    }

    @Override
    int getItemLayout() {
        return R.layout.fr_image_request_list_item;
    }

    @Override
    void setImage(final ImageView imageView, String imageUrl) {
        imageView.setImageResource(R.drawable.ic_launcher);
        VolleyUtil.getQueue(context).cancelAll(imageView);
        ImageRequest request=new ImageRequest(StringUtil.perUrl(imageUrl), new Response.Listener<Bitmap>() {
            @Override
            public void onResponse(Bitmap bitmap) {
                imageView.setImageBitmap(bitmap);
            }
        }, 0, 0, Bitmap.Config.RGB_565, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                imageView.setImageResource(R.mipmap.ic_launcher);
            }
        });
        request.setTag(imageView);
        VolleyUtil.getQueue(context).add(request);
    }
}
