package com.xietian.shaoxiaweather.sxtq.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;


import com.xietian.shaoxiaweather.sxtq.MainActivity;
import com.xietian.shaoxiaweather.sxtq.R;
import com.xietian.shaoxiaweather.sxtq.application.BaseApplication;
import com.xietian.shaoxiaweather.sxtq.bean.WeatherInfo;
import com.xietian.shaoxiaweather.sxtq.golabal.Constant;
import com.xietian.shaoxiaweather.sxtq.utils.UIUtils;

import static com.xietian.shaoxiaweather.sxtq.application.BaseApplication.getApplication;


/**
 * Created by pc on 2017/2/4.
 */

public class Suggestion extends LinearLayout{
    Suggestion_item night;
    Suggestion_item wind_scare;
    Suggestion_item wind_speed;
    Suggestion_item wind_direction;
    private BaseApplication app;


    public Suggestion(Context context) {
        super(context);
        initView();
    }

    public Suggestion(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    public Suggestion(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    private void initView() {
        LayoutInflater inflater = (LayoutInflater) UIUtils.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.suggestion, this);
        app= (BaseApplication) getApplication();

        WeatherInfo weatherInfo1 = app.getWeatherInfo1();
        if(weatherInfo1!=null){
        WeatherInfo.ResultsBean.DailyBean today = weatherInfo1.getResults().get(0).getDaily().get(0);
        String time_zone_desc=today.getText_night();

        night = (Suggestion_item) view.findViewById(R.id.night);
        night.getTitle().setText("夜间气象");
        night.getDesc().setText(time_zone_desc);
            night.getPic().setImageResource(R.drawable.suggest_night);


        wind_scare = (Suggestion_item) view.findViewById(R.id.wind_scare);
        wind_scare.getTitle().setText("风力等级");
        wind_scare.getDesc().setText(today.getWind_scale());
            wind_scare.getPic().setImageResource(R.drawable.suggest_windscare);

        wind_speed = (Suggestion_item) view.findViewById(R.id.wind_speed);
        wind_speed.getTitle().setText("风速");
        wind_speed.getDesc().setText(today.getWind_speed());
            wind_speed.getPic().setImageResource(R.drawable.suggest_windspeed);

        wind_direction = (Suggestion_item) view.findViewById(R.id.wind_direction);
        wind_direction.getTitle().setText("风向");
        wind_direction.getDesc().setText(today.getWind_direction());
            wind_direction.getPic().setImageResource(R.drawable.suggest_windsorention);
        }
    }
}
