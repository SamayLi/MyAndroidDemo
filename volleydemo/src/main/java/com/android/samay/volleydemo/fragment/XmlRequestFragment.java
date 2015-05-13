package com.android.samay.volleydemo.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.android.samay.volleydemo.R;
import com.android.samay.volleydemo.custom.XmlRequest;
import com.android.samay.volleydemo.util.Constants;
import com.android.samay.volleydemo.util.StringUtil;
import com.android.samay.volleydemo.util.ToastUtil;
import com.android.samay.volleydemo.util.VolleyUtil;
import com.android.volley.Response;
import com.android.volley.VolleyError;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by samay on 5/8/15.
 */
public class XmlRequestFragment extends Fragment {
    public static final int INDEX=25;

    private ListView lvWeather;

    private static final int[] ids={R.id.tv_weather_city,R.id.tv_weather_detail,R.id.tv_weather_temp,R.id.tv_weather_wind};

    private static final String[]  keys={"city","detail","temp","wind"};

    private List<Map<String,String>> weatherDataList;

    private SimpleAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fr_xml_request,container,false);
        weatherDataList=new ArrayList<Map<String,String>>();

        lvWeather=(ListView)view.findViewById(R.id.lv_weather);

        adapter=new SimpleAdapter(getActivity(),weatherDataList,R.layout.fr_xml_request_list_item,keys,ids);
        lvWeather.setAdapter(adapter);

        XmlRequest request=new XmlRequest(StringUtil.perUrl(Constants.XML_REQUEST), new Response.Listener<XmlPullParser>() {
            @Override
            public void onResponse(XmlPullParser xmlPullParser) {
                try{
                    weatherDataList.clear();
                    int eventType=xmlPullParser.getEventType();
                    while (eventType!=XmlPullParser.END_DOCUMENT){
                        switch (eventType){
                            case XmlPullParser.START_TAG:
                                String nodeName=xmlPullParser.getName();
                                if("city".equals(nodeName)){
                                    Map<String,String> weatherMap=new HashMap<String,String>();
                                    weatherMap.put("city",xmlPullParser.getAttributeValue(2));
                                    weatherMap.put("detail",xmlPullParser.getAttributeValue(5));
                                    weatherMap.put("temp",xmlPullParser.getAttributeValue(7)+" from "+xmlPullParser.getAttributeValue(6));
                                    weatherMap.put("wind",xmlPullParser.getAttributeValue(8));

                                    weatherDataList.add(weatherMap);
                                }
                                break;
                        }
                        eventType=xmlPullParser.next();
                    }
                    adapter.notifyDataSetChanged();
                }catch (XmlPullParserException e){
                    e.printStackTrace();
                }catch (IOException e){
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                ToastUtil.showToast(getActivity(),"fail request");
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
