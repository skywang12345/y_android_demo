package com.oceanwing.y.plugin.account;

import android.support.v4.app.FragmentActivity;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.ComponentName;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.view.View;

import com.oceanwing.y.plugin.account.service.AccountService;

import de.greenrobot.event.EventBus;
/**
 * @author skywang
 * @e-mail kuiwu-wang@163.com
 */
public class RegisterIdentifierActivity extends FragmentActivity {
    private static final String TAG = "##skywang-Register";

    private Intent mServiceIntent;

    private RegisterIdentifierFragment mIdentifyFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.account_register_identifier_activity);

        findViewById(R.id.btn_register).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "post 5");
                EventBus.getDefault().post(new Integer(5));
            }
        });
        
        // 创建RegisterIdentifierFragment对象，并将其添加到当前Activity中
        mIdentifyFragment = new RegisterIdentifierFragment();
        getSupportFragmentManager().beginTransaction().add(R.id.frag, mIdentifyFragment).commit();

        // 启动服务，用来更新进度
        mServiceIntent = new Intent(this, AccountService.class);
        startService(mServiceIntent);
    }
        
    @Override  
    public void onDestroy() {
        super.onDestroy();
        if(mServiceIntent != null) {
            stopService(mServiceIntent);
            mServiceIntent = null;
        }
    }
}
