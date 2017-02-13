package com.xietian.shaoxiaweather.sxtq.view;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

import com.xietian.shaoxiaweather.sxtq.R;
import com.xietian.shaoxiaweather.sxtq.adapter.HorizontalListViewAdapter;
import com.xietian.shaoxiaweather.sxtq.application.BaseApplication;
import com.xietian.shaoxiaweather.sxtq.bean.WeatherInfo;
import com.xietian.shaoxiaweather.sxtq.golabal.Constant;
import com.xietian.shaoxiaweather.sxtq.utils.UIUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import lecho.lib.hellocharts.gesture.ContainerScrollType;
import lecho.lib.hellocharts.gesture.ZoomType;
import lecho.lib.hellocharts.model.Axis;
import lecho.lib.hellocharts.model.AxisValue;
import lecho.lib.hellocharts.model.Line;
import lecho.lib.hellocharts.model.LineChartData;
import lecho.lib.hellocharts.model.PointValue;
import lecho.lib.hellocharts.model.ValueShape;
import lecho.lib.hellocharts.view.LineChartView;

import static com.xietian.shaoxiaweather.sxtq.application.BaseApplication.getApplication;

/**
 * Created by Administrator on 2017/2/2.
 */

public class FutureDetail extends LinearLayout{
    private HorizontalListView horizontalListView;
    private HorizontalListViewAdapter horizontalListViewAdapter;
    private LineChartView lineChart;
    private BaseApplication app;

    public FutureDetail(Context context) {
        super(context);
        initView();
    }

    public FutureDetail(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    public FutureDetail(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    private void initView() {
        LayoutInflater inflater = (LayoutInflater) UIUtils.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.future_detail, this);
        app= (BaseApplication) getApplication();

        horizontalListView= (HorizontalListView) view.findViewById(R.id.HorizontalListView);
        horizontalListViewAdapter=new HorizontalListViewAdapter(UIUtils.getContext());
        horizontalListViewAdapter.notifyDataSetChanged();
        horizontalListView.setAdapter(horizontalListViewAdapter);
        lineChart= (LineChartView) view.findViewById(R.id.line_chart);


        List<PointValue> valuesHigh = new ArrayList<PointValue>();
        List<PointValue> valuesLow = new ArrayList<PointValue>();

        int i;
        WeatherInfo weatherInfo1 = app.getWeatherInfo1();
        if(weatherInfo1!=null){

        List<WeatherInfo.ResultsBean.DailyBean> day = weatherInfo1.getResults().get(0).getDaily();

            for(i=0;i<day.size();i++){
                valuesHigh.add(new PointValue(i, Integer.parseInt(day.get(i).getHigh())));
                valuesLow.add(new PointValue(i, Integer.parseInt(day.get(i).getLow())));
        }

        }



        //In most cased you can call data model methods in builder-pattern-like manner.
        Line lineHigh = new Line(valuesHigh).setColor(Color.parseColor("#2c3e50")).setCubic(false).setStrokeWidth(1);
        lineHigh.setPointRadius(3);//座标点大小
        lineHigh.setFilled(false);//是否填充曲线的面积

        Line lineLow = new Line(valuesLow).setColor(Color.parseColor("#2c3e20")).setCubic(false).setStrokeWidth(1);
        lineLow.setPointRadius(3);//座标点大小
        lineLow.setFilled(false);//是否填充曲线的面积



        List<Line> lines = new ArrayList<Line>();
        lines.add(lineHigh);
        lines.add(lineLow);

        LineChartData data = new LineChartData();
        data.setValueLabelBackgroundEnabled(false);
        data.setLines(lines);

        lineChart.setInteractive(false);
        lineChart.setScrollEnabled(false);
        lineChart.setLineChartData(data);
        lineChart.setValueTouchEnabled(false);
        lineChart.setFocusableInTouchMode(false);
        lineChart.setVisibility(View.VISIBLE);
        lineChart.startDataAnimation();
    }




}
