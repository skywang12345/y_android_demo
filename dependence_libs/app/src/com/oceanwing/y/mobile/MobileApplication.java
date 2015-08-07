package com.oceanwing.y.mobile;

import android.app.Application;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;

public class MobileApplication extends Application {
    private static final String TAG = "##skywang-Application";

    private static MobileApplication mApplication;
    private Handler mMainHandler;

    public static MobileApplication get() {
        return mApplication;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        mApplication = this;
    }
}
