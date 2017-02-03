package com.xietian.shaoxiaweather.sxtq.view;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

import com.xietian.shaoxiaweather.sxtq.R;
import com.xietian.shaoxiaweather.sxtq.adapter.HorizontalListViewAdapter;
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

/**
 * Created by Administrator on 2017/2/2.
 */

public class FutureDetail extends LinearLayout{
    private HorizontalListView horizontalListView;
    private HorizontalListViewAdapter horizontalListViewAdapter;
    private LineChartView lineChart;
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

        horizontalListView= (HorizontalListView) view.findViewById(R.id.HorizontalListView);
        horizontalListViewAdapter=new HorizontalListViewAdapter(UIUtils.getContext());
        horizontalListViewAdapter.notifyDataSetChanged();
        horizontalListView.setAdapter(horizontalListViewAdapter);
        lineChart= (LineChartView) view.findViewById(R.id.line_chart);


        List<PointValue> values = new ArrayList<PointValue>();
        values.add(new PointValue(0, 2));
        values.add(new PointValue(1, 4));
        values.add(new PointValue(2, 3));


        //In most cased you can call data model methods in builder-pattern-like manner.
        Line line = new Line(values).setColor(Color.BLUE).setCubic(true);
        List<Line> lines = new ArrayList<Line>();
        lines.add(line);

        LineChartData data = new LineChartData();
        data.setValueLabelBackgroundEnabled(false);
        data.setLines(lines);

        LineChartView chart = new LineChartView(UIUtils.getContext());
        chart.setLineChartData(data);

    }




}
