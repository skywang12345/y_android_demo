package com.oceanwing.y.plugin.payment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;
import android.widget.TextView;
import android.util.Log;

/**
 * 付款信息对应的Fragment
 *
 * @author skywang
 * @e-mail kuiwu-wang@163.com
 * @date 2015-06-23
 */
public class EditPaymentFragment extends Fragment 
    implements View.OnClickListener {
    private static final String TAG = "##skywang-Fragment";
    
    private EditText mEtCard;
    private EditText mEtExp;
    private EditText mEtCvv;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        Log.d(TAG, "onCreateView");
        View rootView = inflater.inflate(R.layout.pay_edit_payment_fragment, container, false);

        mEtCard = (EditText) rootView.findViewById(R.id.card_content);
        mEtExp = (EditText) rootView.findViewById(R.id.exp_content);
        mEtCvv = (EditText) rootView.findViewById(R.id.cvv_content);

        rootView.findViewById(R.id.card_container).setOnClickListener(this);
        rootView.findViewById(R.id.exp_container).setOnClickListener(this);
        rootView.findViewById(R.id.cvv_container).setOnClickListener(this);
        rootView.findViewById(R.id.scan).setOnClickListener(this);

        return rootView;
    }

    /**
     * TODO 检查各个参数的有效性
     */
    // CardInfo createCard() {
    //     String card = mEtCard.getText().toString();
    //     String exp = mEtExp.getText().toString();
    //     String cvv = mEtCvv.getText().toString();

    //     // 检查卡号
    //     if (TextUtil.isEmpty(card)) {
    //         Toast.makeText(getActivity(), "Card ID Error!", Toast.LENGTH_SHORT).show();
    //         return null;
    //     }

    //     // 检查有效日期
    //     int[] arr = TextUtil.parseYearMonth(exp);
    //     if (arr==null || arr.length!=2) {
    //         Toast.makeText(getActivity(), "Card Exp Error!", Toast.LENGTH_SHORT).show();
    //         return null;
    //     }

    //     // 检查有效日期
    //     if (TextUtil.isEmpty(cvv)) {
    //         Toast.makeText(getActivity(), "Card CVV Error!", Toast.LENGTH_SHORT).show();
    //         return null;
    //     }

    //     return new CardInfo(card, String.valueOf(arr[0]), String.valueOf(arr[1]), cvv);
    // }

    @Override
    public void onClick(View v) {
        // switch (v.getId()) {
        //     case R.id.card_container:
        //         mEtCard.requestFocus();
        //         break;
        //     case R.id.exp_container:
        //         mEtExp.requestFocus();
        //         break;
        //     case R.id.cvv_container:
        //         mEtCvv.requestFocus();
        //         break;
        //     case R.id.scan:
        //         break;
        // }
    }
}
