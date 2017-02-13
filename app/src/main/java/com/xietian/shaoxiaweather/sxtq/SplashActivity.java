package com.xietian.shaoxiaweather.sxtq;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.View;
import android.view.Window;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.location.Poi;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;
import com.xietian.shaoxiaweather.sxtq.application.BaseApplication;
import com.xietian.shaoxiaweather.sxtq.bean.CityInfo;
import com.xietian.shaoxiaweather.sxtq.bean.WeatherInfo;
import com.xietian.shaoxiaweather.sxtq.golabal.Constant;
import com.xietian.shaoxiaweather.sxtq.net.GetDataFromServer;

import com.xietian.shaoxiaweather.sxtq.net.GetLocation;
import com.xietian.shaoxiaweather.sxtq.utils.LogUtils;
import com.xietian.shaoxiaweather.sxtq.utils.PrefUtils;
import com.xietian.shaoxiaweather.sxtq.utils.SystemUtils;
import com.xietian.shaoxiaweather.sxtq.utils.UIUtils;
import com.zaaach.citypicker.CityPickerActivity;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.io.IOException;
import java.util.List;

import okhttp3.Call;


public class SplashActivity extends BaseActivity {


    private ProgressBar progressBar;
    private TextView textVeiw;
    private ImageView imageView;
    private static final int REQUEST_CODE_PICK_CITY = 0;

    private BaseApplication app;

    private String weatherInfo;




    @Override
    public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
    }

    @Override
    protected void initView() {

        setContentView(R.layout.activity_splash);

        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        textVeiw = (TextView) findViewById(R.id.textView);
        imageView = (ImageView) findViewById(R.id.imageView);

        RotateAnimation rotateAnimation = new RotateAnimation(0f, 360f,
                Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF,
                0.5f);
        rotateAnimation.setDuration(2000);

        ScaleAnimation scaleAnimation = new ScaleAnimation(0.0f, 1.0f, 0.0f, 1.0f,
                Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        scaleAnimation.setDuration(2000);

        AlphaAnimation alphaAnimation = new AlphaAnimation(0.5f, 1f);
        alphaAnimation.setDuration(2000);

        AnimationSet set = new AnimationSet(true);
        set.addAnimation(alphaAnimation);
        set.addAnimation(scaleAnimation);
        set.addAnimation(rotateAnimation);

        imageView.startAnimation(set);


        set.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationRepeat(Animation animation) {

            }

            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                progressBar.setVisibility(View.VISIBLE);
                textVeiw.setText("少侠莫急……");

                String city = PrefUtils.getString("city", null, UIUtils.getContext());
                app= (BaseApplication) getApplication();
                if (city == null) {
                    //如果city为空，自动定位
                    GetDataFromServer.getCityInfo();
                    city=app.getCity();
                    if(city==null){
                        city="上海";
                        app.setCity("上海");
                    }
                }
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
                                Intent intent=new Intent(SplashActivity.this,MainActivity.class);
                                startActivity(intent);
                                finish();
                            }
                        });
            }
        });
    }



}
