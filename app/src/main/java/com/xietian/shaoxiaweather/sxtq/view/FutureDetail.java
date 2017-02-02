package com.xietian.shaoxiaweather.sxtq.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

import com.xietian.shaoxiaweather.sxtq.R;
import com.xietian.shaoxiaweather.sxtq.adapter.HorizontalListViewAdapter;
import com.xietian.shaoxiaweather.sxtq.utils.UIUtils;

/**
 * Created by Administrator on 2017/2/2.
 */

public class FutureDetail extends LinearLayout{
    private HorizontalListView horizontalListView;
    private HorizontalListViewAdapter horizontalListViewAdapter;

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
    }


}
