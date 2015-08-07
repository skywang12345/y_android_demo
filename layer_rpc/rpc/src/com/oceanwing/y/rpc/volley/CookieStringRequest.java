package com.oceanwing.y.rpc.volley;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.toolbox.StringRequest;
import com.oceanwing.y.rpc.VolleyManager;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class CookieStringRequest extends StringRequest {

    /**
     * @param method
     * @param url
     * @param params
     *            A {@link HashMap} to post with the request. Null is allowed
     *            and indicates no parameters will be posted along with request.
     * @param listener
     * @param errorListener
     */
    public CookieStringRequest(int method, String url, 
            Listener<String> listener, ErrorListener errorListener) {
        super(method, url, listener, errorListener);
    }

    /* (non-Javadoc)
     * @see com.android.volley.toolbox.CookieStringRequest#parseNetworkResponse(com.android.volley.NetworkResponse)
     */
    @Override
    protected Response<String> parseNetworkResponse(NetworkResponse response) {
        // since we don't know which of the two underlying network vehicles
        // will Volley use, we have to handle and store session cookies manually
        VolleyManager.getInstance().checkSessionCookie(response.headers);

        return super.parseNetworkResponse(response);
    }

    /* (non-Javadoc)
     * @see com.android.volley.Request#getHeaders()
     */
    @Override
    public Map<String, String> getHeaders() throws AuthFailureError {
        Map<String, String> headers = super.getHeaders();

        if (headers==null || headers.equals(Collections.emptyMap())) {
            headers = new HashMap<String, String>();
        }

        VolleyManager.getInstance().addSessionCookie(headers);

        return headers;
    }
}
