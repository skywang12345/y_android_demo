package com.oceanwing.y.rpc.request;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.oceanwing.y.rpc.VolleyListener;

import java.util.Map;

/**
 * 一个(请求)业务单元
 *
 * @desc 包含了一次请求的地址、参数、解析器等内容。
 */
abstract public class Unit implements Response.ErrorListener, Response.Listener {

    protected String mUrl;
    protected VolleyListener mVolleyListener;
    protected Map<String, String> mParams;
    protected Map<String, String> mHeaders;

    public Unit(String url,
            VolleyListener listener,
            Map<String, String> params,
            Map<String, String> headers) {
        mUrl = url;
        mVolleyListener = listener;
        mParams = params;
        mHeaders = headers;
    }

    abstract public Request buildRequest();

    public void onResponse(Object obj) {
    }

    @Override
    public void onErrorResponse(VolleyError error) {
        if (mVolleyListener != null) {
            mVolleyListener.onFail(error.getMessage());
        }
    }
}
