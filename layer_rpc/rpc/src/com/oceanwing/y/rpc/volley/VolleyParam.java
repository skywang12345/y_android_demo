package com.oceanwing.y.rpc.volley;

import java.util.HashMap;
import java.util.Map;

public class VolleyParam {

    private Map<String, String> mMap = null;

    private VolleyParam() {
        mMap = new HashMap<String, String>();
        mMap.put("lang", "en");
        mMap.put("version", "1.0");
    }

    public VolleyParam add(String key, String value){
        mMap.put(key, value);
        return this;
    }

    public Map<String, String> getParams() {
        return mMap;
    }

    /**
     * 静态工厂
     *
     * @desc 用于创建VolleyParam的实例
     */
    public static class Factory {
        public static VolleyParam createInstance() {
            return new VolleyParam();
        }
    }
}
