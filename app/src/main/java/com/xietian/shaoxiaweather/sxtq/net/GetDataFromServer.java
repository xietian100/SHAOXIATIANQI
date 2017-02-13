package com.xietian.shaoxiaweather.sxtq.net;


import com.google.gson.Gson;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;
import com.xietian.shaoxiaweather.sxtq.application.BaseApplication;
import com.xietian.shaoxiaweather.sxtq.bean.CityInfo;

import com.xietian.shaoxiaweather.sxtq.bean.WeatherInfo;
import com.xietian.shaoxiaweather.sxtq.utils.GsonTools;
import com.xietian.shaoxiaweather.sxtq.utils.LogUtils;

import com.xietian.shaoxiaweather.sxtq.utils.SystemUtils;
import com.xietian.shaoxiaweather.sxtq.utils.UIUtils;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.Callback;
import com.zhy.http.okhttp.callback.StringCallback;


import java.io.IOException;

import okhttp3.Call;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.Response;


import static com.xietian.shaoxiaweather.sxtq.application.BaseApplication.getApplication;


/**
 * Created by pc on 2017/1/13.
 */

public class GetDataFromServer {
    private static String weatherInfo;
    private static String city;
    private static BaseApplication app;




    public static String getWeatherInfo(String city) throws IOException {
        String url = "https://api.thinkpage.cn/v3/weather/daily.json?key=2ed4ivvahiutolju&location=" + city + "&language=zh-Hans&unit=c&start=0&days=5";

        OkHttpUtils
                .get()
                .url(url)
                .build()
                .execute(new StringCallback()
                {
                    @Override
                    public void onError(Call call, Exception e, int id) {

                    }

                    @Override
                    public void onResponse(String response, int id) {
                        weatherInfo=response;
                        app.setWeatherInfo(weatherInfo);
                    }

                });
        return weatherInfo;

    }






    public static String getCityInfo() {
        String ip = SystemUtils.getLocalAddress();
        app = (BaseApplication) getApplication();
        String url = "https://api.thinkpage.cn/v3/location/search.json?key=2ed4ivvahiutolju&q=" + ip;
        HttpUtils httpUtils = new HttpUtils();
        httpUtils.send(HttpRequest.HttpMethod.GET, url, new RequestCallBack<String>() {

            @Override
            public void onSuccess(ResponseInfo<String> responseInfo) {
                String result = responseInfo.result;
                LogUtils.i(result);
                CityInfo cityInfo = GsonTools.changeGsonToBean(result, CityInfo.class);
                city = cityInfo.getNameX();
                app.setCity(city);
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
