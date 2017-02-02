package com.xietian.shaoxiaweather.sxtq;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.support.v7.widget.ViewUtils;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.lidroid.xutils.view.annotation.ViewInject;
import com.xietian.shaoxiaweather.sxtq.bean.CityInfo;
import com.xietian.shaoxiaweather.sxtq.bean.WeatherInfo;
import com.xietian.shaoxiaweather.sxtq.net.GetDataFromServer;
import com.xietian.shaoxiaweather.sxtq.utils.PrefUtils;
import com.xietian.shaoxiaweather.sxtq.utils.UIUtils;
import com.xietian.shaoxiaweather.sxtq.view.FutureDetail;
import com.xietian.shaoxiaweather.sxtq.view.TodayDetail;


public class MainActivity extends BaseActivity implements View.OnClickListener {

    private String city;
    private String weatherInfo;
    private ImageView share;
    private ImageView location;
    private ImageView refresh;
    private TextView cityname;
    private TodayDetail todayDetail;
    private FutureDetail future_detail;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        ininData();
        super.onCreate(savedInstanceState);

    }


    @Override
    protected void initView() {
        setContentView(R.layout.activity_main);
        share=(ImageView) findViewById(R.id.share);
        location=(ImageView) findViewById(R.id.location);
        refresh=(ImageView) findViewById(R.id.refresh);
        cityname= (TextView) findViewById(R.id.cityname);
        todayDetail= (TodayDetail) findViewById(R.id.today_detail);
        future_detail= (FutureDetail) findViewById(R.id.future_detail);

        cityname.setText(city);
    share.setOnClickListener(this);
    location.setOnClickListener(this);
    refresh.setOnClickListener(this);
}

    private void ininData(){
        weatherInfo = PrefUtils.getString("weatherInfo", null, UIUtils.getContext());
        city=PrefUtils.getString("city",null,UIUtils.getContext());
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.share:
                break;
            case R.id.location:
                Intent intent =new Intent(this,LoacationActivity.class);
                startActivity(intent);
                break;
            case R.id.refresh:
                GetDataFromServer.getWeatherInfo(city);
                weatherInfo=PrefUtils.getString("weatherInfo",weatherInfo,this);
                break;
        }
    }
}
