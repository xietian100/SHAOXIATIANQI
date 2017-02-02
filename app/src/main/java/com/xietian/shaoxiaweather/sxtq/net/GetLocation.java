package com.xietian.shaoxiaweather.sxtq.net;

import android.content.Context;
import android.location.LocationManager;
import android.widget.Toast;

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
 * Created by Administrator on 2017/1/30.
 */

public class GetLocation {
    private static String weatherInfo;
    private static String city;
    private static String ip;

    public static String initMethod() {
        ip = SystemUtils.getLocalAddress();
        city = getIpLocation();
        if (city == null) {
            city = getGpsLocation();
        }

        return city;
    }

    public static String getIpLocation() {
        return GetDataFromServer.getCityInfo(ip);
    }


    public static String getGpsLocation() {
        LocationManager alm = (LocationManager) UIUtils.getContext().getSystemService(Context.LOCATION_SERVICE);
        if (alm.isProviderEnabled(android.location.LocationManager.GPS_PROVIDER)) {
            Toast.makeText(UIUtils.getContext(), "GPS模块正常", Toast.LENGTH_SHORT)
                    .show();

            //TODO:百度定位
            PrefUtils.putString("city", "北京", UIUtils.getContext());
        }
            return "北京";
    }
}
