package com.oceanwing.y.rpc.request;

import android.util.Log;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.oceanwing.y.rpc.VolleyListener;

import java.util.Map;

public class StringUnit extends Unit {
    private static final String TAG = "#skywang-StringUnit";

    public StringUnit(String url,
            VolleyListener listener,
            Map<String, String> params,
            Map<String, String> headers) {
        super(url, listener, params, headers);
    }

    @Override
    public Request buildRequest() {
        return new StringRequest(mUrl, this, this);
    }

    @Override
    public void onResponse(Object obj) {
        Log.d(TAG, "onResponse: obj="+obj);
    }
}
