package com.oceanwing.y.mobile;

import android.app.Application;

// import com.oceanwing.y.mobile.communication.VolleyManager;

public class MobileApplication extends Application {
    private static final String TAG = "##skywang-Application";

    private static MobileApplication mApplication;
    // private VolleyManager mVolleyManager;

    // public static MobileApplication get() {
    //     return mApplication;
    // }

    // public VolleyManager getVolleyManager() {
    //     return mVolleyManager;
    // }

    @Override
    public void onCreate() {
        super.onCreate();

        mApplication = this;
        // mVolleyManager = new VolleyManager(this);
    }
}
