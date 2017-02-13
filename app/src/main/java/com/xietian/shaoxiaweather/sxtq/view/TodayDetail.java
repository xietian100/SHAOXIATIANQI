package com.xietian.shaoxiaweather.sxtq.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.xietian.shaoxiaweather.sxtq.MainActivity;
import com.xietian.shaoxiaweather.sxtq.R;
import com.xietian.shaoxiaweather.sxtq.application.BaseApplication;
import com.xietian.shaoxiaweather.sxtq.bean.WeatherInfo;
import com.xietian.shaoxiaweather.sxtq.golabal.Constant;
import com.xietian.shaoxiaweather.sxtq.utils.GsonTools;
import com.xietian.shaoxiaweather.sxtq.utils.PrefUtils;
import com.xietian.shaoxiaweather.sxtq.utils.UIUtils;

import static com.lidroid.xutils.view.ResType.Text;
import static com.xietian.shaoxiaweather.sxtq.application.BaseApplication.getApplication;

/**
 * Created by Administrator on 2017/1/30.
 */

public class TodayDetail extends LinearLayout {

    private ImageView pic;
    private TextView Today;
    private TextView Time;
    private TextView Weather;
    private TextView Temp;
    private BaseApplication app;

    public WeatherInfo getWeatherInfo1() {
        return weatherInfo1;
    }

    WeatherInfo weatherInfo1;


    public TodayDetail(Context context) {
        super(context);
        initView();
    }


    public TodayDetail(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    public TodayDetail(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    private void initView() {

        LayoutInflater inflater = (LayoutInflater) UIUtils.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.today_detail, this);

        app= (BaseApplication) getApplication();
        pic = (ImageView) view.findViewById(R.id.pic);
        Today = (TextView) view.findViewById(R.id.Today);
        Time = (TextView) view.findViewById(R.id.Time);
        Weather = (TextView) view.findViewById(R.id.Weather);
        Temp = (TextView) view.findViewById(R.id.Temp);

        /**
         * TODO
         * 可从mainActivity中直接取出
         */

        weatherInfo1 = app.getWeatherInfo1();

        if(weatherInfo1!=null){

        String time = weatherInfo1.getResults().get(0).getLast_update();
        String HighTemperature = weatherInfo1.getResults().get(0).getDaily().get(0).getHigh();
        String LowTemperature = weatherInfo1.getResults().get(0).getDaily().get(0).getLow();
        String DayText = weatherInfo1.getResults().get(0).getDaily().get(0).getText_day();
        String code_day = weatherInfo1.getResults().get(0).getDaily().get(0).getCode_day();

        String str = time.substring(0, 10);
        String str1 = str.substring(5);
        pic.setImageResource(ChooseWeatherPic.ChoosePic(code_day));
        Weather.setText(DayText);
        Time.setText(str1);
        Temp.setText(HighTemperature + "-" + LowTemperature+"℃");
        }


    }


}
