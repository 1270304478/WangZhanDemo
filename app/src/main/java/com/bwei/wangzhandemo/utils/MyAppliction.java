package com.bwei.wangzhandemo.utils;

import android.app.Application;

import com.bwei.wangzhandemo.IGetDataBase;
import com.facebook.drawee.backends.pipeline.Fresco;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * 此类的作用：
 *
 * @author: forever
 * @date: 2017/12/15 18:56
 */
public class MyAppliction extends Application {
    public static IGetDataBase iGetDataBase;


    @Override
    public void onCreate() {
        super.onCreate();
        Fresco.initialize(this);
        //初始化
        ImageloaderUtil.initConfig(this);
        Retrofit retrofit =  new Retrofit.Builder().baseUrl("http://gank.io")
                //添加转化工厂，创建gson转化工厂
                .addConverterFactory(GsonConverterFactory.create())
                .build();
//对IGteDatabase编译。请求数据
        iGetDataBase = retrofit.create(IGetDataBase.class);

    }
}
