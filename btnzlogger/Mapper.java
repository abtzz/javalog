package com.pajk.consult.commons.log.btnzlogger;

import com.pajk.consult.commons.btnzlog.Log;
import com.pajk.consult.commons.btnzlog.LogMap;

/**
 * Created by zhangsongwei on 2016/5/31.
 */
public class Mapper {
    public String dao(){
        Log.write("Mapper.dao", new LogMap().put("desc", "in mapper dao").getMap());
        return "mapper";
    }
}
