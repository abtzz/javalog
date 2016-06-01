package com.pajk.consult.commons.btnzlog;

import java.io.Serializable;
import java.util.Map;

/**
 * Created by zhangyong on 2016/5/27.
 * Log类中的方法之间不能相互调用
 */
public class Log implements Serializable {
    private static final long serialVersionUID = -245666194042476969L;
    public String id = ""; //id表示当前栈所处的情景，一般情况下就是函数的名字
    public long time = 0;
    public int distance = 0;

    public static void init(String contextId, String id) {
        LogContextHolder.init(contextId, id);
    }

//    public static void write(String id, Object... params){
//        LogContextHolder.write(id, params);
//    }
    public static void write(String id, Map<String, String> params) {
        LogContextHolder.write(id, params);
    }
}
