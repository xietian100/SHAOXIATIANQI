package com.xietian.shaoxiaweather.sxtq.net;


import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;

import android.widget.Toast;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;
import com.xietian.shaoxiaweather.sxtq.bean.CityInfo;
import com.xietian.shaoxiaweather.sxtq.utils.GsonTools;
import com.xietian.shaoxiaweather.sxtq.utils.LogUtils;
import com.xietian.shaoxiaweather.sxtq.utils.PrefUtils;
import com.xietian.shaoxiaweather.sxtq.utils.SystemUtils;
import com.xietian.shaoxiaweather.sxtq.utils.UIUtils;


/**
 * Created by pc on 2017/1/13.
 */

public class GetDataFromServer {
    private static String weatherInfo;
    private static String city;


    public static void getWeatherInfo(String city) {
        String url = "https://api.thinkpage.cn/v3/weather/daily.json?key=2ed4ivvahiutolju&location="+city+ "&language=zh-Hans&unit=c&start=0&days=5";
        HttpUtils httpUtils = new HttpUtils();
        httpUtils.send(HttpRequest.HttpMethod.GET, url, new RequestCallBack<String>() {
            @Override
            public void onSuccess(ResponseInfo<String> responseInfo) {
                String result= responseInfo.result;
                PrefUtils.putString("weatherInfo",result,UIUtils.getContext());
            }

            @Override
            public void onFailure(HttpException e, String s) {
                UIUtils.showToast("无法获取定位");
            }
        });

    }

    public static String getCityInfo(String ip) {
            String url = "https://api.thinkpage.cn/v3/location/search.json?key=2ed4ivvahiutolju&q=" + ip;
            HttpUtils httpUtils = new HttpUtils();
            httpUtils.send(HttpRequest.HttpMethod.GET, url, new RequestCallBack<String>() {

                @Override
                public void onSuccess(ResponseInfo<String> responseInfo) {
                    String result = responseInfo.result;
                    LogUtils.i(result);
                    CityInfo cityInfo = GsonTools.changeGsonToBean(result, CityInfo.class);
                    city = cityInfo.getNameX();
                    PrefUtils.putString("city", city, UIUtils.getContext());
                }

            @Override
            public void onFailure(HttpException e, String s) {
                city = null;
                UIUtils.showToast("无法获取定位");
            }
        });
        return city;
    }



}
