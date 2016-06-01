package com.pajk.consult.commons.log.btnzlogger;

import com.pajk.consult.commons.btnzlog.Log;
import com.pajk.consult.commons.btnzlog.LogMap;

/**
 * Created by zhangsongwei on 2016/5/31.
 */
public class Manager {
    public String managerUser(long userId, Object... params) throws Exception {
        Log.write("managerUser", new LogMap().put("userId", userId + "").getMap());
        String result = userId + "managerUser" + params.toString() + new Mapper().dao();
        Log.write("managerUser", new LogMap().put("result", result).getMap());
        Log.write("managerUser", new LogMap().put("result", result).getMap());
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Log.write("managerUser", new LogMap().put("result", result).getMap());
        if (userId > 0) {
            throw new Exception();
        }
        return result;
    }

    public String managerDoctor(long doctorId, Object... params) {
        Log.write("managerDoctor", new LogMap().put("doctorId", doctorId + "").getMap());
        String result = doctorId + "managerDoctor" + params.toString() + new Mapper().dao();
        Log.write("managerDoctor", new LogMap().put("result", result).getMap());
        return result;
    }
}
