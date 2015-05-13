package com.android.samay.volleydemo.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
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
import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by samay on 5/8/15.
 */
public class PostRequestFragment extends Fragment {
    public static final int INDEX=26;
    private EditText etUrl;
    private EditText etParams;
    private Button btnSend;
    private TextView tvResult;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fr_post_request,container,false);
        initView(view);

        return view;
    }

    private void initView(View view) {
        etUrl=(EditText)view.findViewById(R.id.et_url);
        etParams=(EditText)view.findViewById(R.id.et_params);
        btnSend=(Button)view.findViewById(R.id.btn_send);
        tvResult=(TextView)view.findViewById(R.id.tv_resulte);

        etUrl.setText(Constants.POST_REQUEST);
        etParams.setText("mobileCode=13636527078;\\nuserID=;");

        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (StringUtil.isEmpty(etUrl.getText().toString())){
                    ToastUtil.showToast(getActivity(),"please input url:");
                    return;
                }
                VolleyUtil.getQueue(getActivity()).cancelAll(this);
                tvResult.setText("");
                Response.Listener<String> listener=new Response.Listener<String>() {
                    @Override
                    public void onResponse(String s) {
                        tvResult.setText(s);
                    }
                };

                Response.ErrorListener errorListener=new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {
                        ToastUtil.showToast(getActivity(), "request fail");
                    }
                };
                StringRequest request=new StringRequest(Request.Method.POST,StringUtil.perUrl(etUrl.getText().toString().trim()),listener,errorListener){
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                       Map<String,String> paramMap=new HashMap<String, String>();
                        try {
                            String paramsStr=etParams.getText().toString().trim();
                            String[] paramsArray=paramsStr.split(";");
                            for (String param:paramsArray){
                                if(StringUtil.isEmpty(param)){
                                    continue;
                                }
                                String[] keyValueArray=param.split("=");
                                if(keyValueArray.length<1){
                                    continue;
                                }
                                if(StringUtil.isEmpty(keyValueArray[0])){
                                    continue;
                                }
                                paramMap.put(keyValueArray[0].trim(),keyValueArray.length>1?keyValueArray[1].trim():"");
                            }
                        }catch (Exception e){
                                ToastUtil.showToast(getActivity(),"have error");
                        }
                        return paramMap;
                    }
                };
                request.setTag(this);
                VolleyUtil.getQueue(getActivity()).add(request);
            }
        });
    }

    @Override
    public void onDestroyView() {
        VolleyUtil.getQueue(getActivity()).cancelAll(this);
        super.onDestroyView();
    }
}
