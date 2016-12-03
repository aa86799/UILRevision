package com.stone.uilrevision;

import android.app.Application;

import com.stone.uilrevision.activity.util.ImageLoaderWrapper;

/**
 * desc   :
 * author : stone
 * email  : aa86799@163.com
 * time   : 2016/12/4 00 12
 */
public class UILRevisionApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        ImageLoaderWrapper.init(this);

    }
}
