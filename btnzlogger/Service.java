package com.pajk.consult.commons.log.btnzlogger;

import com.pajk.consult.commons.btnzlog.Log;
import com.pajk.consult.commons.btnzlog.LogMap;

/**
 * Created by zhangsongwei on 2016/5/31.
 */
public class Service {

    public void doService(long userId, long doctorId, Object... params) {
        try {
            Log.init("dubboUUID00", "doService");
            String result;
            Log.write("分割符", new LogMap().put("xingXing", "***********************************************").getMap());
            result = new Manager().managerUser(userId, params);
            Log.write("managerUserResult", new LogMap().put("result", result).getMap());
            Log.write("分割符", new LogMap().put("fangFang", "###############################################").getMap());
            result = new Manager().managerDoctor(doctorId, params);
            Log.write("managerDoctorResult", new LogMap().put("result", result).getMap());
        } catch (Exception e) {
            Log.write("doService", new LogMap().put("desc", "发生异常").put("exception", e.toString()).getMap());
        }
    }
}
