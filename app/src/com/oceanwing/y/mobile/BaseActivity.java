package com.oceanwing.y.mobile;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;

/**
 * FragmentActivity的基类。
 *
 * @desc 包含了"通用的方法"：
 * (1) 进度对话框
 *
 * @author skywang
 * @e-mail sky.wang@oceanwing.com
 */
public class BaseActivity extends FragmentActivity {
    private static final String TAG = "##skywang-Main";

    // private LoadingDialogFragment mLoadingDialog;

    /**
     * 显示进度对话框
     *
     * @param tag show为true时需要传递的参数，表示对话框的tag
     */
    // protected void showLoadingDialog(String tag) {
    //     hideLoadingDialog();
    //     mLoadingDialog = new LoadingDialogFragment();
    //     mLoadingDialog.show(getSupportFragmentManager(), tag);
    // }

    /**
     * 关闭进度对话框
     */
    // protected void hideLoadingDialog() {
    //     if (mLoadingDialog != null) {
    //         mLoadingDialog.dismiss();
    //         mLoadingDialog = null;
    //     }
    // }
}
