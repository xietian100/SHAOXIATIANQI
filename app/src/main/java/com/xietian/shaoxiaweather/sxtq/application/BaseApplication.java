package com.xietian.shaoxiaweather.sxtq.application;

import android.app.Application;
import android.os.Handler;

import com.xietian.shaoxiaweather.sxtq.bean.WeatherInfo;
import com.zhy.http.okhttp.OkHttpUtils;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;

/**
 * Created by pc on 2017/1/9.
 */

public class BaseApplication extends Application {
    //获取到主线程的上下午
    private static BaseApplication mContext;

    //获取的到主线程的Handler
    private static Handler mMainThreadHandler;

    //获取到主线程
    private static Thread mMainThread;

    //获取到主线程的id
    private static  int mMainThreadId;

    private static String city;

    private  static String weatherInfo;

    public  WeatherInfo getWeatherInfo1() {
        return weatherInfo1;
    }

    public  void setWeatherInfo1(WeatherInfo weatherInfo1) {
        BaseApplication.weatherInfo1 = weatherInfo1;
    }

    private static WeatherInfo weatherInfo1;
    @Override
    public void onCreate() {
        super.onCreate();
        this.mContext=this;
        this.mMainThreadHandler=new Handler();
        this.mMainThread=Thread.currentThread();
        this.mMainThreadId=android.os.Process.myPid();
        setCity("北京");
        setWeatherInfo("0");
        setWeatherInfo1(null);

        OkHttpClient okHttpClient = new OkHttpClient.Builder()
//                .addInterceptor(new LoggerInterceptor("TAG"))
                .connectTimeout(10000L, TimeUnit.MILLISECONDS)
                .readTimeout(10000L, TimeUnit.MILLISECONDS)
                //其他配置
                .build();

        OkHttpUtils.initClient(okHttpClient);

    }
    public static BaseApplication getApplication(){
        return mContext;
    }
    public static Handler getMainThreadHandler(){
        return mMainThreadHandler;
    }
    public static Thread getMainThread(){
        return mMainThread;
    }
    public static int getMainThreadId(){
        return mMainThreadId;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }


    public  String getWeatherInfo() {
        return weatherInfo;
    }

    public  void setWeatherInfo(String weatherInfo) {
        this.weatherInfo = weatherInfo;
    }






}
