package cn.skw.droidlib.utils;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.DisplayMetrics;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

public class ContextUtil {

    /**
     * 获取屏幕密度
     */
    public static float density(Context context) {
        return context.getResources().getDisplayMetrics().density;
    }

    /**
     * dp转换为px
     */
    public static int dp2px(Context context, float dp) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dp * scale + 0.5f);
    }

    /**
     * px转换为dp
     */
    public static int px2dp(Context context, float px) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (px / scale + 0.5f);
    }

    /**
     * 获取屏幕的宽
     */
    public static int getScreenWidth(Context context) {
        return getScreenSize(context)[0];
    }

    /**
     * 获取屏幕的宽/高
     */
    public static int getScreenHeight(Context context) {
        return getScreenSize(context)[1];
    }

    /**
     * 获取屏幕的宽/高
     */
    public static int[] getScreenSize(Context context) {
        DisplayMetrics metric = context.getResources().getDisplayMetrics();
        return new int[]{metric.widthPixels, metric.heightPixels};
    }

    /** 
     * 开启/关闭软键盘
     *
     * @param et 软键盘所属的EditText
     * @param show true，开启软键盘；false，关闭软键盘
     */
    public static void showSoftKeyboard(EditText et, boolean show) {
        InputMethodManager imm = (InputMethodManager) et.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);

        if (show) {
            imm.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);
        } else {
            imm.hideSoftInputFromWindow(et.getWindowToken(), 0); 
        }   
    }

    /**
     * 在浏览器中打开链接
     *
     * @param context 上下文环境变量
     * @param link 链接地址
     */
    public static void openLinkInBrowser(Context context, String link) {
        Uri uri = null;

        try {
            uri = Uri.parse(link);
        } catch (Exception e) {
            e.printStackTrace();
        }

        final Intent intent = new Intent();
        intent.setAction("android.intent.action.VIEW");
        intent.setData(uri);
        context.startActivity(intent);
    }
}
