package com.xietian.shaoxiaweather.sxtq.adapter;

/**
 * Created by Administrator on 2017/2/2.
 */



import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.xietian.shaoxiaweather.sxtq.MainActivity;
import com.xietian.shaoxiaweather.sxtq.R;
import com.xietian.shaoxiaweather.sxtq.application.BaseApplication;
import com.xietian.shaoxiaweather.sxtq.bean.WeatherInfo;
import com.xietian.shaoxiaweather.sxtq.golabal.Constant;
import com.xietian.shaoxiaweather.sxtq.utils.GsonTools;
import com.xietian.shaoxiaweather.sxtq.utils.PrefUtils;
import com.xietian.shaoxiaweather.sxtq.utils.UIUtils;
import com.xietian.shaoxiaweather.sxtq.view.ChooseWeatherPic;

import static com.xietian.shaoxiaweather.sxtq.application.BaseApplication.getApplication;

public class HorizontalListViewAdapter extends BaseAdapter{

    WeatherInfo weatherInfo1 ;
    private LayoutInflater mInflater;
    private BaseApplication app;

    public HorizontalListViewAdapter(Context con){
        mInflater=LayoutInflater.from(con);
        app= (BaseApplication) getApplication();
        weatherInfo1= app.getWeatherInfo1();
    }
    @Override
    public int getCount() {
        if(weatherInfo1!= null){
        return weatherInfo1.getResults().get(0).getDaily().size();
        }else {
            return 0;
        }
    }
    @Override
    public Object getItem(int position) {
        return position;
    }
    private ViewHolder vh 	 =new ViewHolder();
    private static class ViewHolder {
        private TextView item_date ;
        private TextView item_weather ;
        private TextView item_high ;
        private TextView item_low ;
        private ImageView item_pic;
    }
    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView==null){
            convertView = mInflater.inflate(R.layout.horizontallistview_item, null);
            vh.item_date=(TextView) convertView.findViewById(R.id.item_date);
            vh.item_weather=(TextView)convertView.findViewById(R.id.item_weather);
            vh.item_high=(TextView)convertView.findViewById(R.id.item_high);
            vh.item_low=(TextView)convertView.findViewById(R.id.item_low);
            vh.item_pic= (ImageView) convertView.findViewById(R.id.item_pic);
            convertView.setTag(vh);
        }else{
            vh=(ViewHolder)convertView.getTag();
        }

        WeatherInfo.ResultsBean.DailyBean daily = weatherInfo1.getResults().get(0).getDaily().get(position);

        vh.item_date.setText(daily.getDate().substring(5));
        vh.item_weather.setText(daily.getText_day());
        vh.item_high.setText(daily.getHigh());
        vh.item_low.setText(daily.getLow());
        vh.item_pic.setImageResource(ChooseWeatherPic.ChoosePic(daily.getCode_day()));
        return convertView;
    }
}