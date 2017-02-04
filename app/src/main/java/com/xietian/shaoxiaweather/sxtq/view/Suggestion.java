package com.xietian.shaoxiaweather.sxtq.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.LinearLayout;


import com.xietian.shaoxiaweather.sxtq.R;
import com.xietian.shaoxiaweather.sxtq.utils.UIUtils;




/**
 * Created by pc on 2017/2/4.
 */

public class Suggestion extends LinearLayout{
    GridView gridView;

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
        View view = inflater.inflate(R.layout.future_detail, this);


    }
}
