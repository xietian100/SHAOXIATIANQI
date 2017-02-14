package com.xietian.shaoxiaweather.sxtq.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.xietian.shaoxiaweather.sxtq.R;
import com.xietian.shaoxiaweather.sxtq.utils.UIUtils;

/**
 * Created by pc on 2017/2/6.
 */

public class Suggestion_item extends LinearLayout{
    private ImageView pic;
    private TextView title;
    private TextView desc;


    public Suggestion_item(Context context) {
        super(context);
        initView();
    }

    public Suggestion_item(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    public Suggestion_item(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    private void initView() {
        LayoutInflater inflater = (LayoutInflater) UIUtils.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.suggestion_item, this);

        title= (TextView) view.findViewById(R.id.title);
        desc= (TextView) view.findViewById(R.id.desc);
        pic= (ImageView) view.findViewById(R.id.pic);

    }

    public ImageView getPic() {
        return pic;
    }

    public TextView getTitle() {
        return title;
    }

    public TextView getDesc() {
        return desc;
    }

}
