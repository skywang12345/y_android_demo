package com.oceanwing.y.rpc;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.preference.PreferenceManager;
import android.text.TextUtils;
import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Request.Method;
import com.android.volley.Response;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.oceanwing.y.rpc.volley.CookieStringRequest;
import com.oceanwing.y.mobile.MobileApplication;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;
import java.util.List;
import java.util.Map;

import org.apache.http.cookie.Cookie;
import org.apache.http.Header;
import org.apache.http.HeaderElement;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.CookieStore;
import org.apache.http.client.protocol.ClientContext;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.HttpContext;
import org.apache.http.util.EntityUtils;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * HTTP请求管理模块
 *
 * @desc HTTP请求的具体操作是通过"Volley"去完成的。
 *  而VolleyManagerImpl则是对App所有请求的管理。
 *
 */
public class VolleyManagerImpl {
    private static String TAG = "##skywang-VolleyManagerImpl";

    // private static final String HTTP_SERVER = "https://t.ankercam.com/app/";
    private static final String HTTP_SERVER = "http://www.ankerpower.com/app/";         // 服务器地址

    // 请求的方法名
    public static final String REQUEST_ACCOUNT_GET_FACEBOOK_INFO   = "account_Facebook_information_get";
    public static final String REQUEST_ACCOUNT_SIGNUP              = "account_Signup";
    public static final String REQUEST_ACCOUNT_SIGNIN              = "account_Signin";
    public static final String REQUEST_ACCOUNT_SIGNOUT             = "account_Signout";
    public static final String REQUEST_ACCOUNT_UPDATE_PROFILE      = "account_Profile_update";
    public static final String REQUEST_PAYMENT_UPDATE_CARD         = "payment_Card_update";

    private static final String SET_COOKIE_KEY = "Set-Cookie";  // 头部的"Set-Cookie"关键字
    private static final String COOKIE_KEY = "Cookie";          // 头部的"Cookie"关键字
    private static final String SESSION_COOKIE = "sessionid";

    // Volley的消息队列
    private RequestQueue mRequestQueue;
    // 保存Http缓存的SharedPreferences
    private SharedPreferences mPref;

    public static final String TEST_ACCOUNT_EMAIL = "sky.wang@oceanwing.com";
    public static final String TEST_ACCOUNT_NAME = "sky wang";
    public static final String TEST_ACCOUNT_MOBILE_PREFIX = "+86";
    public static final String TEST_ACCOUNT_MOBILE_NUMBER = "18681500637";
    public static final String TEST_ACCOUNT_PASSWORD = "12345678";
    public static final String TEST_CARD_ID = "4242424242424242";
    public static final String TEST_CARD_EXP = "0516";
    public static final String TEST_CARD_CVV = "904";

    public static final String TEST_EDIT_ACCOUNT_NAME = "Editor Wang";
    public static final String TEST_EDIT_ACCOUNT_MOBILE_NUMBER = "13612345678";

    private static VolleyManagerImpl mVolleyManager;

    public static VolleyManagerImpl getInstance() {
        if (mVolleyManager==null) {
            mVolleyManager = new VolleyManagerImpl(MobileApplication.get());
        }

        return mVolleyManager;
    }

    private VolleyManagerImpl(Context context) {
        Log.d(TAG, "initilize RequestQueue");
        mRequestQueue = Volley.newRequestQueue(context);
        mPref = PreferenceManager.getDefaultSharedPreferences(context);
    }

    /**
     * CookieStringRequest请求
     *
     * @desc CookieStringRequest是自定义的Request，它的特点是支持Cookie
     *
     * @param method 请求的方法
     * @param okListener 注册成功的回调函数
     * @param errorListener 注册失败的回调函数
     * @param params Post请求参数
     * @param header Post请求头
     */
    public void postCookieStringRequest(String method
            , Response.Listener<String> okListener , Response.ErrorListener errorListener
            , Map<String,String> params , Map<String, String> headers) {
        if (mRequestQueue != null) {
            String url = HTTP_SERVER + method; 
            Log.d(TAG, "postCookieStringRequest url="+url+"\n params="+params);
            CookieStringRequest request = new CookieStringRequest(Method.POST, url, okListener, errorListener);
            request.setParams(params);
            request.setHeaders(headers);
            mRequestQueue.add(request);
        }
    }

    /**
     * 根据经纬度查询地址
     */
    public void postQueryAddrestRequest(final double latitude, final double longtitude
            , Response.Listener<String> okListener , Response.ErrorListener errorListener
            , Map<String,String> params , Map<String, String> headers) {
        postQueryAddrestRequest(latitude, longtitude, Locale.getDefault(), okListener, errorListener, params, headers);
    }

    /**
     * 根据经纬度查询地址
     *
     * @param latitude 纬度
     * @param longtitude 经度
     * @param locale 地区(决定返回字符串的语言)
     * @param okListener 注册成功的回调函数
     * @param errorListener 注册失败的回调函数
     * @param params Post请求参数
     * @param header Post请求头
     */
    public void postQueryAddrestRequest(final double latitude, final double longtitude, Locale locale
            , Response.Listener<String> okListener , Response.ErrorListener errorListener
            , Map<String,String> params , Map<String, String> headers) {
        if (mRequestQueue != null) {
            String url = getAddressUrl(latitude, longtitude, locale);
            Log.d(TAG, "postCookieStringRequest url="+url);
            StringRequest request = new StringRequest(url, okListener, errorListener);
            request.setParams(params);
            request.setHeaders(headers);
            mRequestQueue.add(request);
        }
    }

    /**
     * 从GoogleMap根据经纬度获取地址的URL
     *
     * @param latitude 纬度
     * @param longtitude 经度
     * @param locale 地区。用于确定返回的json数据的语言类型
     *
     * @reurn 返回URL。示例如下：
     *       http://maps.googleapis.com/maps/api/geocode/json?latlng=15.317285895532908,75.7138879224658&sensor=false&language=zh_CN
     */
    private String getAddressUrl(final double latitude, final double longtitude, Locale locale) {
        StringBuilder sb = new StringBuilder();
        sb.append("http://maps.googleapis.com/maps/api/geocode/json?latlng=")
          .append(latitude)
          .append(",")
          .append(longtitude)
          .append("&sensor=false&language=")
          .append(locale);

        Log.d(TAG, "getAddressUrl: url="+sb.toString());
        return sb.toString();
    }

    public void postQueryLocationRequest(final String address
            , Response.Listener<String> okListener , Response.ErrorListener errorListener
            , Map<String,String> params , Map<String, String> headers) {
        postQueryLocationRequest(address, Locale.getDefault(), okListener, errorListener, params, headers);
    }

    /**
     * 根据地址查询坐标(经纬度)
     */
    public void postQueryLocationRequest(final String address, Locale locale
            , Response.Listener<String> okListener , Response.ErrorListener errorListener
            , Map<String,String> params , Map<String, String> headers) {
        if (mRequestQueue != null) {
            String url = getLocationUrl(address, locale);
            Log.d(TAG, "postQueryLocationRequest url="+url);
            StringRequest request = new StringRequest(url, okListener, errorListener);
            request.setParams(params);
            request.setHeaders(headers);
            mRequestQueue.add(request);
        }
    }

    /**
     * 从GoogleMap根据地址获取经纬度的URL
     *
     * @param address 地址
     *
     * @reurn 返回URL。示例如下：
     *       http://maps.google.com/maps/api/geocode/json?address=杭州&language=zh-CN&sensor=false
     */
    private String getLocationUrl(final String address, final Locale locale) {
        StringBuilder sb = new StringBuilder();
        // sb.append("http://maps.google.com/maps/api/geocode/json?address=")
        //   .append(address)
        //   // .append("&sensor=false");
        //   .append("&sensor=false&language=")
        //   .append(locale);
        if (true) {
        // sb.append("http://maps.google.com/maps/api/geocode/json?address=")
        //   .append(address)
        //   .append("&language=")
        //   .append(locale)
        //   .append("&sensor=false");

        // return sb.toString();
            // return "http://maps.google.com/maps/api/geocode/json?address="+address+"&language="+locale+"&sensor=false";
            return "https://maps.googleapis.com/maps/api/geocode/json?address="+address+"&key="+"AIzaSyCS3oJ5qJJgQKkKHSBBo2WizKyiljXvw1I";
        } else {
            try {
            return "http://maps.google.com/maps/api/geocode/json?" + URLEncoder.encode("address="+ address+"ka&sensor=false", "UTF-8");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }

            return "";
        }
    }

    /**
     * 保存Cookie
     */
    public final void checkSessionCookie(Map<String, String> headers) {
        // Log.d(TAG, "checkSessionCookie: headers="+headers+"\n Set-Cookie="+headers.get(SET_COOKIE_KEY));
        if (headers != null) {
            // 获取"Set-Cookie"对应的值
            String cookieValue = headers.get(SET_COOKIE_KEY);
            if (!TextUtils.isEmpty(cookieValue)) {
                // 将"Set-Cookie"对应的指分为几个部分
                String[] splitCookie = cookieValue.split(Response.SET_COOKIE_SPLIT);
                StringBuilder sb = new StringBuilder();
                for (String splitItem:splitCookie) {
                    // 获取每一个部分的第一个值，并添加到sb中
                    String[] setCookie = splitItem.split(";");
                    sb.append(setCookie[0]).append("; ");
                }

                Log.d(TAG, "checkSessionCookie: cookie="+sb.toString());
                Editor prefEditor = mPref.edit();
                prefEditor.putString(SESSION_COOKIE, sb.toString());
                prefEditor.commit();
            }
        }
    }

    /**
     * 添加Cookie到头部
     */
    public final void addSessionCookie(Map<String, String> headers) {
        String cookie = mPref.getString(SESSION_COOKIE, "");
        Log.d(TAG, "addSessionCookie,  cookie="+cookie);
        if (!TextUtils.isEmpty(cookie)) {
            headers.put(COOKIE_KEY, cookie);
        }
    }

    /**
     * 删除Cookie
     */
    public final void removeSessionCookie() {
        Log.d(TAG, "removeSessionCookie");
        final Editor prefEditor = mPref.edit();
        prefEditor.remove(SESSION_COOKIE);
        prefEditor.commit();
    }

    /**
     * Cookie是否存在
     */
    public final boolean isCookieExist() {
        String cookie = mPref.getString(SESSION_COOKIE, "");
        return (!TextUtils.isEmpty(cookie));
    }
}
