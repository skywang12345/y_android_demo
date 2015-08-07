package com.oceanwing.y.rpc;

import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.oceanwing.y.mobile.MobileApplication;
import com.oceanwing.y.rpc.request.CookieStringUnit;
import com.oceanwing.y.rpc.request.Unit;
import com.oceanwing.y.rpc.request.StringUnit;

import java.util.Map;

public class VolleyManager {
    private static String TAG = "##skywang-VolleyManager";

    // Volley的消息队列
    private RequestQueue mRequestQueue;

    // VolleyManager对象
    private static VolleyManager mVolleyManager;

    public static VolleyManager getInstance() {
        if (mVolleyManager==null) {
            mVolleyManager = new VolleyManager(MobileApplication.get());
        }
        return mVolleyManager;
    }

    private VolleyManager(Context context) {
        Log.d(TAG, "initilize RequestQueue");
        mRequestQueue = Volley.newRequestQueue(context);
    }

    public void postCookieStringRequest(String url,
        VolleyListener listener, Map<String,String> params, Map<String, String> headers) {
        Unit unit = new CookieStringUnit(url, listener, params, headers);
        mRequestQueue.add(unit.buildRequest());
    }

    public void postStringRequest(String url,
        VolleyListener listener, Map<String,String> params, Map<String, String> headers) {
        Log.d(TAG, "postStringRequest "+url);
        Unit unit = new StringUnit(url, listener, params, headers);
        mRequestQueue.add(unit.buildRequest());
    }


    /**
     * 保存Cookie
     */
    public final void checkSessionCookie(Map<String, String> headers) {
    }

    /**
     * 添加Cookie到头部
     */
    public final void addSessionCookie(Map<String, String> headers) {
    }

    /**
     * 删除Cookie
     */
    public final void removeSessionCookie() {
    }

    /**
     * Cookie是否存在
     */
    public final boolean isCookieExist() {
        return true;
    }
}
