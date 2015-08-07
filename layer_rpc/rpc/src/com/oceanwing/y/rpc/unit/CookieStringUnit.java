package com.oceanwing.y.rpc.request;

import com.oceanwing.y.rpc.volley.CookieStringRequest;
import com.oceanwing.y.rpc.VolleyListener;
import com.android.volley.Request;
import com.android.volley.Request.Method;
import com.android.volley.Response;
import com.android.volley.VolleyError;

import java.util.Map;

public class CookieStringUnit extends Unit {

    public CookieStringUnit(String url, 
            VolleyListener listener,
            Map<String, String> params,
            Map<String, String> headers) {
        super(url, listener, params, headers);
    }

    @Override
    public Request buildRequest() {
        return new CookieStringRequest(Method.POST, mUrl, this, this);
    }

    @Override
    public void onResponse(Object response) {
    }
}
