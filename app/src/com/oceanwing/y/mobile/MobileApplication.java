package com.oceanwing.y.mobile;

import android.app.Application;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;

// import com.oceanwing.y.mobile.communication.VolleyManager;

public class MobileApplication extends Application {
    private static final String TAG = "##skywang-Application";

    private static MobileApplication mApplication;
    private Handler mMainHandler;
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
        // mMainHandler = new Handler(Looper.getMainLooper(), new MyMainCallback());
    }

    private final class MyMainCallback implements Handler.Callback {
        @Override
        public boolean handleMessage(Message msg) {
            Log.d(TAG, "msg.what="+msg.what+", msg="+msg);
            return true;
        }
    }
}
