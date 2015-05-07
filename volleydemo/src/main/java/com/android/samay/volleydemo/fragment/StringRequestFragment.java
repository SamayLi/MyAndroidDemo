package com.android.samay.volleydemo.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.android.samay.volleydemo.R;
import com.android.samay.volleydemo.util.Constants;
import com.android.samay.volleydemo.util.StringUtil;
import com.android.samay.volleydemo.util.ToastUtil;
import com.android.samay.volleydemo.util.VolleyUtil;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

/**
 * Created by samay on 5/6/15.
 */
public class StringRequestFragment extends Fragment {
    public final static int INDEX=21;
    private EditText edURL;
    private Button btnRequest;
    private TextView tvResult;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fr_string_request,container,false);
        initView(view);
        return  view;
    }

    private void initView(View view) {
        edURL=(EditText)view.findViewById(R.id.ed_url);
        btnRequest=(Button)view.findViewById(R.id.btn_string_send);
        tvResult=(TextView)view.findViewById(R.id.tv_resulte);

        edURL.setText(Constants.STRING_REQUEST);

        btnRequest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(StringUtil.isEmpty(edURL.getText().toString())){
                    ToastUtil.showToast(getActivity(),"Input url is null !!!");
                    return;
                }
                tvResult.setText("");
                VolleyUtil.getQueue(getActivity()).cancelAll(this);
                Log.e("LSH", StringUtil.perUrl(edURL.getText().toString().trim()));
                StringRequest stringRequest=new StringRequest(StringUtil.perUrl(edURL.getText().toString().trim()), new Response.Listener<String>() {
                    @Override
                    public void onResponse(String s) {
                        tvResult.setText(s);
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {
                        ToastUtil.showToast(getActivity(),"StringRequest is Error");
                    }
                });
                stringRequest.setTag(this);
                VolleyUtil.getQueue(getActivity()).add(stringRequest);
            }
        });
    }

    @Override
    public void onDestroyView() {
        VolleyUtil.getQueue(getActivity()).cancelAll(this);
        super.onDestroyView();
    }
}
