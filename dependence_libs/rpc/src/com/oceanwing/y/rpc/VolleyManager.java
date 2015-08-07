package com.oceanwing.y.rpc;

import java.util.Map;

public class VolleyManager {
    private static String TAG = "##skywang-VolleyManager";

    public static VolleyManager getInstance() {
        return null;
    }

    public void postCookieStringRequest(String url,
        VolleyListener listener, Map<String,String> params, Map<String, String> headers) {
    }

    public void postStringRequest(String url,
        VolleyListener listener, Map<String,String> params, Map<String, String> headers) {
    }


    public final void checkSessionCookie(Map<String, String> headers) {
    }

    public final void addSessionCookie(Map<String, String> headers) {
    }

    public final void removeSessionCookie() {
    }

    public final boolean isCookieExist() {
        return true;
    }
}
