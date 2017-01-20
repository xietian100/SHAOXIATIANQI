package com.xietian.shaoxiaweather.sxtq.application;

import android.app.Application;
import android.os.Handler;

/**
 * Created by pc on 2017/1/9.
 */

public class BaseApplication extends Application {
    //获取到主线程的上下午
    private static BaseApplication mContext;

    //获取的到主线程的Handler
    private static Handler mMainThreadHandler;

    //获取到主线程
    private static Thread mMainThread;

    //获取到主线程的id
    private static  int mMainThreadId;
    @Override
    public void onCreate() {
        super.onCreate();
        this.mContext=this;
        this.mMainThreadHandler=new Handler();
        this.mMainThread=Thread.currentThread();
        this.mMainThreadId=android.os.Process.myPid();
    }
    public static BaseApplication getApplication(){
        return mContext;
    }
    public static Handler getMainThreadHandler(){
        return mMainThreadHandler;
    }
    public static Thread getMainThread(){
        return mMainThread;
    }
    public static int getMainThreadId(){
        return mMainThreadId;
    }



}
