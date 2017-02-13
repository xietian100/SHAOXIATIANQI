package com.xietian.shaoxiaweather.sxtq.net;

import android.content.Context;

import android.location.LocationManager;

import android.widget.Toast;


import com.baidu.location.BDLocation ;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.location.Poi;
import com.xietian.shaoxiaweather.sxtq.utils.PrefUtils;
import com.xietian.shaoxiaweather.sxtq.utils.SystemUtils;
import com.xietian.shaoxiaweather.sxtq.utils.UIUtils;

import java.util.List;


/**
 * Created by Administrator on 2017/1/30.
 */

public class GetLocation {
    private static String weatherInfo;
    private static String city;
    private static String ip;
    private static  String citycode;




    public static String initMethod() {
        ip = SystemUtils.getLocalAddress();
        city = getIpLocation();
        return city;
    }

    public static String getIpLocation() {
        return GetDataFromServer.getCityInfo();
    }





}
