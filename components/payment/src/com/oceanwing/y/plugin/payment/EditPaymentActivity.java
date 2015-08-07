package com.oceanwing.y.plugin.payment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;
import android.widget.TextView;

/**
 * Payment 编辑界面
 *
 * @author skywang
 * @e-mail kuiwu-wang@163.com
 */
public class EditPaymentActivity extends FragmentActivity 
    implements View.OnClickListener {
    private static final String TAG = "##skywang-EditPayment";

    private EditPaymentFragment mFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pay_edit_payment_activity);

        // 创建EditPaymentFragment对象，并将其添加到当前Activity中
        mFragment = new EditPaymentFragment();
        getSupportFragmentManager().beginTransaction()
                    .add(R.id.frag, mFragment).commit();

        findViewById(R.id.btn_update).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        // switch (v.getId()) {
        //     case R.id.header_left_img:
        //         break;
        //     case R.id.btn_update:
        //         break;
        // }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }
}
