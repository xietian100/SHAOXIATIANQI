package com.xietian.shaoxiaweather.sxtq;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by pc on 2017/1/9.
 */
public abstract class BaseActivity extends AppCompatActivity {
    private static BaseActivity mForegroundActivity;
    @Override
    public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);

    }

    public static BaseActivity getForegroundActivity() {
        return mForegroundActivity;
    }


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
    }

    @Override
    protected void onResume() {
        super.onResume();
        this.mForegroundActivity=this;
    }

    protected abstract void initView();
}
