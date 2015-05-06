package com.android.samay.volleydemo;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

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
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){

        }
    }
}
