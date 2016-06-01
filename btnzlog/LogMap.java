package com.pajk.consult.commons.btnzlog;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by zhangsongwei on 2016/6/1.
 */
public class LogMap {
    private Map<String, String> map = new HashMap<>();

    public LogMap put(String key, String value) {
        map.put(key, value);
        return this;
    }

    public Map<String, String> getMap() {
        return map;
    }
}
