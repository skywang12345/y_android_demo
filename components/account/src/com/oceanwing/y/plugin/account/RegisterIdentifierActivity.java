package com.oceanwing.y.plugin.account;

import android.support.v4.app.FragmentActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

/**
 * @author skywang
 * @e-mail kuiwu-wang@163.com
 */
public class RegisterIdentifierActivity extends FragmentActivity
    implements View.OnClickListener {
    private static final String TAG = "##skywang-Register";

    private RegisterIdentifierFragment mIdentifyFragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.account_register_identifier_activity);
        
        // 创建RegisterIdentifierFragment对象，并将其添加到当前Activity中
        mIdentifyFragment = new RegisterIdentifierFragment();
        getSupportFragmentManager().beginTransaction().add(R.id.frag, mIdentifyFragment).commit();

    }
        
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
        }
    }
}
