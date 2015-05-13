package com.android.samay.volleydemo.adapter;

import android.content.Context;
import android.widget.ImageView;

import com.android.samay.volleydemo.R;
import com.android.samay.volleydemo.util.LruImageCache;
import com.android.samay.volleydemo.util.StringUtil;
import com.android.samay.volleydemo.util.VolleyUtil;
import com.android.volley.toolbox.ImageLoader;

/**
 * Created by samay on 5/8/15.
 */
public class ImageLoaderAdapter extends ImageBaseAdapter {

    private ImageLoader imageLoader;

    public ImageLoaderAdapter(Context context, String[] imageArray) {
        super(context, imageArray);
        this.imageLoader=new ImageLoader(VolleyUtil.getQueue(context),new LruImageCache());
    }

    @Override
    void setImage(ImageView imageView, String imageUrl) {
        ImageLoader.ImageContainer container;
        try {
            if (imageView.getTag() != null) {
                container = (ImageLoader.ImageContainer) imageView.getTag();
                container.cancelRequest();
            }
        }catch (Exception e){

        }
        ImageLoader.ImageListener listener=ImageLoader.getImageListener(imageView,R.drawable.ic_launcher,R.drawable.ic_launcher);

        container=imageLoader.get(StringUtil.perUrl(imageUrl),listener);
        imageView.setTag(container);

    }

    @Override
    int getItemLayout() {
        return R.layout.fr_image_request_list_item;
    }
}
