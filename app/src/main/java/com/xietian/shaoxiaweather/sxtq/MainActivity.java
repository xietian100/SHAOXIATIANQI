package com.xietian.shaoxiaweather.sxtq;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.format.DateUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;

import com.airbnb.lottie.LottieAnimationView;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshScrollView;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.HttpHandler;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;
import com.xietian.shaoxiaweather.sxtq.application.BaseApplication;
import com.xietian.shaoxiaweather.sxtq.bean.WeatherInfo;
import com.xietian.shaoxiaweather.sxtq.golabal.Constant;
import com.xietian.shaoxiaweather.sxtq.net.GetDataFromServer;
import com.xietian.shaoxiaweather.sxtq.utils.GsonTools;
import com.xietian.shaoxiaweather.sxtq.utils.PrefUtils;
import com.xietian.shaoxiaweather.sxtq.utils.UIUtils;
import com.xietian.shaoxiaweather.sxtq.view.FutureDetail;
import com.xietian.shaoxiaweather.sxtq.view.Suggestion;
import com.xietian.shaoxiaweather.sxtq.view.TodayDetail;
import com.zaaach.citypicker.CityPickerActivity;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.io.IOException;

import okhttp3.Call;


public class MainActivity extends BaseActivity implements View.OnClickListener {

    private String city;
    private String weatherInfo;
    private ImageView share;
    private ImageView location;
    private ImageView refresh;
    private TextView cityname;
    private TodayDetail todayDetail;
    private FutureDetail future_detail;
    private Suggestion suggestion;
    private LottieAnimationView animation_view;
    ScrollView mScrollView;

    private PullToRefreshScrollView scroll_view;


    private static final int REQUEST_CODE_PICK_CITY = 0;
    private BaseApplication app;


    private  WeatherInfo weatherInfo1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        initData();
        super.onCreate(savedInstanceState);

    }



    @Override
    protected void initView() {
        setContentView(R.layout.activity_main);
        share=(ImageView) findViewById(R.id.share);
        location=(ImageView) findViewById(R.id.location);
        refresh=(ImageView) findViewById(R.id.refresh);
        cityname= (TextView) findViewById(R.id.cityname);
        scroll_view= (PullToRefreshScrollView) findViewById(R.id.scroll_view);

        todayDetail= (TodayDetail) findViewById(R.id.today_detail);
        future_detail= (FutureDetail) findViewById(R.id.future_detail);
        suggestion= (Suggestion) findViewById(R.id.suggestion);

        cityname.setText(city);
        share.setOnClickListener(this);
        location.setOnClickListener(this);
        refresh.setOnClickListener(this);

        initScrollView();



}


    private void initData(){
        app= (BaseApplication) getApplication();
        weatherInfo=app.getWeatherInfo();
        city=app.getCity();
        if(weatherInfo!=null){
            weatherInfo1 = GsonTools.changeGsonToBean(weatherInfo, WeatherInfo.class);
            app.setWeatherInfo1(weatherInfo1);
        }
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.share:
                break;
            case R.id.location:
                startActivityForResult(new Intent(MainActivity.this, CityPickerActivity.class),
                        Constant.REQUEST_CODE_PICK_CITY);
                break;
            case R.id.refresh:

                break;
        }
    }
    private void initScrollView() {
        //这几个刷新Label的设置

        scroll_view.getLoadingLayoutProxy().setPullLabel("快点拉");
        scroll_view.getLoadingLayoutProxy().setRefreshingLabel("正在拉");
        scroll_view.getLoadingLayoutProxy().setReleaseLabel("放开刷新");

        //上拉、下拉设定
        scroll_view.setMode(PullToRefreshBase.Mode.PULL_FROM_START);


        //上拉监听函数
        scroll_view.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener<ScrollView>() {
            @Override
            public void onRefresh(PullToRefreshBase<ScrollView> refreshView) {
                String label = DateUtils.formatDateTime(getApplicationContext(), System.currentTimeMillis(),
                        DateUtils.FORMAT_SHOW_TIME | DateUtils.FORMAT_SHOW_DATE | DateUtils.FORMAT_ABBREV_ALL);

                // 更新显示的label
                refreshView.getLoadingLayoutProxy().setLastUpdatedLabel(label);

                //执行刷新函数
                new GetDataTask().execute();
            }
        });
        mScrollView=scroll_view.getRefreshableView();


    }

    private class GetDataTask extends AsyncTask<Void, Void, String[]> {

        @Override
        protected String[] doInBackground(Void... params) {
            return null;
        }

        @Override
        protected void onPostExecute(String[] result) {
            // Do some stuff here

            // Call onRefreshComplete when the list has been refreshed.
            //注意：执行完后通知控件刷新完成
            scroll_view.onRefreshComplete();

            super.onPostExecute(result);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_CODE_PICK_CITY && resultCode == RESULT_OK){
            if (data != null){
                String newCity = data.getStringExtra(CityPickerActivity.KEY_PICKED_CITY);
                city=newCity;
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
                                weatherInfo=response;;
                                if(weatherInfo!=null){
                                    weatherInfo1 = GsonTools.changeGsonToBean(weatherInfo, WeatherInfo.class);
                                    app.setWeatherInfo1(weatherInfo1);
                                }
                                initView();
                            }
                        });
                UIUtils.showToast("当前选择：" + newCity);
            }
        }
    }


}
