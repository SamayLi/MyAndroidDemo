package com.android.samay.volleydemo.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.android.samay.volleydemo.R;
import com.android.samay.volleydemo.util.Constants;
import com.android.samay.volleydemo.util.StringUtil;
import com.android.samay.volleydemo.util.ToastUtil;
import com.android.samay.volleydemo.util.VolleyUtil;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by samay on 5/6/15.
 */
public class JsonRequestFragment extends Fragment {
    public final static int INDEX = 14;

    private ListView lvCar;

    private static final int[] ids = {R.id.tv_car_name, R.id.tv_car_level, R.id.tv_car_price};

    private static final String[] keys = {"name", "level", "price"};
    private List<Map<String, String>> carDataList;

    private SimpleAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fr_json_request, container, false);
        carDataList = new ArrayList<Map<String, String>>();
        lvCar = (ListView) view.findViewById(R.id.lv_car);
        adapter = new SimpleAdapter(getActivity(), carDataList, R.layout.fr_json_request_list_item, keys, ids);
        lvCar.setAdapter(adapter);
        VolleyUtil.getQueue(getActivity()).cancelAll(this);
        JsonObjectRequest request;
        request = new JsonObjectRequest(StringUtil.perUrl(Constants.JSON_REQUEST),null,new Response.Listener<JSONObject>(){

            @Override
            public void onResponse(JSONObject s) {

                    try {
                        if (!s.has("result")) {
                            return;
                        }
                        JSONObject jsonObject=s.getJSONObject("result");
                        if(!jsonObject.has("fctlist")){
                            return;
                        }
                        JSONArray jsonArray=jsonObject.getJSONArray("fctlist");
                        if(jsonArray.length()==0){
                            return;
                        }
                        JSONObject factory=jsonArray.getJSONObject(0);
                        if(!factory.has("serieslist")){
                            return;
                        }
                        JSONArray seriesArray=factory.getJSONArray("serieslist");
                        carDataList.clear();

                        for(int i=0;i<seriesArray.length();i++){
                            JSONObject series=seriesArray.getJSONObject(i);
                            Map<String,String> seriesMap=new HashMap<String,String>();

                            seriesMap.put("name",series.getString("name"));
                            seriesMap.put("level","level is "+series.getString("levelname"));
                            seriesMap.put("price","price is "+series.getString("price"));

                            carDataList.add(seriesMap);
                        }
                        adapter.notifyDataSetChanged();
                    }catch (Exception e){
                        ToastUtil.showToast(getActivity(), "json request is error");
                    }

                }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError volleyError) {
                ToastUtil.showToast(getActivity(),"json request is error");
            }
        });
        request.setTag(this);
        VolleyUtil.getQueue(getActivity()).add(request);
        return view;
    }

    @Override
    public void onDestroyView() {
        VolleyUtil.getQueue(getActivity()).cancelAll(this);
        super.onDestroyView();
    }
}
