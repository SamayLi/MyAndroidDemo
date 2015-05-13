package com.android.samay.volleydemo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.android.samay.volleydemo.fragment.ImageLoaderFragment;
import com.android.samay.volleydemo.fragment.ImageRequestFragment;
import com.android.samay.volleydemo.fragment.JsonRequestFragment;
import com.android.samay.volleydemo.fragment.NetworkImageViewFragment;
import com.android.samay.volleydemo.fragment.PostRequestFragment;
import com.android.samay.volleydemo.fragment.StringRequestFragment;
import com.android.samay.volleydemo.fragment.XmlRequestFragment;
import com.android.samay.volleydemo.util.Constants;

/**
 * Created by samay on 5/6/15.
 */
public class MainActivity extends Activity implements View.OnClickListener{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_layout);
        initView();
    }

    private void initView() {
        findViewById(R.id.btn_string_request).setOnClickListener(this);
        findViewById(R.id.btn_json_request).setOnClickListener(this);
        findViewById(R.id.btn_image_request).setOnClickListener(this);
        findViewById(R.id.btn_image_loader_request).setOnClickListener(this);
        findViewById(R.id.btn_network_image).setOnClickListener(this);
        findViewById(R.id.btn_xml_request).setOnClickListener(this);
        findViewById(R.id.btn_post_request).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent=new Intent(MainActivity.this,RequestActivity.class);
        switch (v.getId()){
            case R.id.btn_string_request:
                intent.putExtra(Constants.Extra.FRAGMENT_INDEX, StringRequestFragment.INDEX);
                break;
            case R.id.btn_json_request:
                intent.putExtra(Constants.Extra.FRAGMENT_INDEX, JsonRequestFragment.INDEX);
                break;
            case R.id.btn_image_request:
                intent.putExtra(Constants.Extra.FRAGMENT_INDEX, ImageRequestFragment.INDEX);
                break;
            case R.id.btn_image_loader_request:
                intent.putExtra(Constants.Extra.FRAGMENT_INDEX, ImageLoaderFragment.INDEX);
                break;
            case R.id.btn_network_image:
                intent.putExtra(Constants.Extra.FRAGMENT_INDEX, NetworkImageViewFragment.INDEX);
                break;
            case R.id.btn_xml_request:
                intent.putExtra(Constants.Extra.FRAGMENT_INDEX, XmlRequestFragment.INDEX);
                break;
            case R.id.btn_post_request:
                intent.putExtra(Constants.Extra.FRAGMENT_INDEX, PostRequestFragment.INDEX);
                break;
        }
        startActivity(intent);
    }
}
