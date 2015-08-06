package com.oceanwing.y.plugin.account.service;

import android.app.Service;
import android.content.Intent;
import android.os.Bundle;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;
import android.view.View;

import de.greenrobot.event.EventBus;

public class AccountService extends Service {
    private static final String TAG = "##skywang-AccountService";

    @Override
    public void onCreate() {
        Log.d(TAG, "onCreate");
        super.onCreate();
        EventBus.getDefault().register(this);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d(TAG, "onStartCommand");
        return START_STICKY;
    }
    
    @Override
    public void onDestroy() {
        Log.d(TAG, "onDestroy");
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
    
    @Override
    public IBinder onBind(Intent intent) {
        Log.d(TAG, "onBind");
        return null;
    }

    public void onEvent(Integer id) {
        Log.d(TAG, "onEvent: id=" + id);

        EventBus.getDefault().post(String.format("%d is receiver", id));
    }
}
