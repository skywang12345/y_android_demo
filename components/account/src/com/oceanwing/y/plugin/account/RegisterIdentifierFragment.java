package com.oceanwing.y.plugin.account;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.util.Log;

/**
 * @author skywang
 * @e-mail kuiwu-wang@163.com
 */
public class RegisterIdentifierFragment extends Fragment 
    implements View.OnClickListener {
    private static final String TAG = "##skywang-Identify";

    private boolean bInitShowEmail = true;
    private boolean bInitShowPassword = true;
    private String mStringName = null;
    private String mStringPhone = null;
    private String mStringEmail = null;
    
    private View mContainerEmail;
    private View mContainerPassword;
    private EditText mEtEmail;
    private EditText mEtName;
    private EditText mEtMobile;
    private EditText mEtPassword;
    private ImageView mImgCountry;
    private TextView mTvMobilePrefix;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        Log.d(TAG, "onCreateView");
        View rootView = inflater.inflate(R.layout.account_register_identifier_fragment, container, false);

        mContainerEmail = rootView.findViewById(R.id.email_container);
        mContainerPassword = rootView.findViewById(R.id.password_container);
        mEtEmail = (EditText) rootView.findViewById(R.id.email_content);
        mEtName = (EditText) rootView.findViewById(R.id.name_content);
        mEtMobile = (EditText) rootView.findViewById(R.id.mobile_content);
        mEtPassword = (EditText) rootView.findViewById(R.id.password_content);
        mTvMobilePrefix = (TextView ) rootView.findViewById(R.id.mobile_prefix);
        mImgCountry = (ImageView) rootView.findViewById(R.id.mobile_img);
        mImgCountry.setOnClickListener(this);

        rootView.findViewById(R.id.email_container).setOnClickListener(this);
        rootView.findViewById(R.id.name_container).setOnClickListener(this);
        rootView.findViewById(R.id.mobile_container).setOnClickListener(this);
        rootView.findViewById(R.id.password_container).setOnClickListener(this);

        return rootView;
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();

        if (id == R.id.mobile_img) {
            Toast.makeText(getActivity(), "Click Image", Toast.LENGTH_SHORT).show();
        } else if (id == R.id.email_container) {
            Toast.makeText(getActivity(), "Click email", Toast.LENGTH_SHORT).show();
        } else if (id == R.id.name_container) {
            Toast.makeText(getActivity(), "Click name", Toast.LENGTH_SHORT).show();
        } else if (id == R.id.mobile_container) {
            Toast.makeText(getActivity(), "Click mobile", Toast.LENGTH_SHORT).show();
        } else if (id == R.id.password_container) {
            Toast.makeText(getActivity(), "Click password", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.d(TAG, "activity result");
    }
}
