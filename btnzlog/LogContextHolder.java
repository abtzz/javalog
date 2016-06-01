package com.pajk.consult.commons.btnzlog;

import com.alibaba.fastjson.JSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by zhangsongwei on 2016/5/31.
 * LogContextHolder类中向外暴露出的方法，必须在第一时间计算当前栈的层次，只有栈的层极计算完毕之后，才能调用其它的方法
 */
public class LogContextHolder {
    private static final Logger logger = LoggerFactory.getLogger(Log.class);
    private static final ThreadLocal<LogContext> holder = new ThreadLocal<>();
    private static final int startCount = 100000;

    private static long jvmStackInitTime = 0;
    private static int jvmStackInitLevel = 0;
    private static int jvmStackPrevLevel = 0;
    private static int jvmStackCurrLevel = 0;

    public static void init(String contextId, String id) {
        jvmStackInitLevel = new Throwable().getStackTrace().length;
        jvmStackPrevLevel = jvmStackInitLevel;
        jvmStackInitTime = System.currentTimeMillis();
        Log log = new Log();
        log.id = id;
        log.distance = 1;
        log.time = jvmStackInitTime;
        LogContext logContext = new LogContext();
        logContext.stack.push(log);
        logContext.contextCount = 0;
        logContext.contextId = contextId;
        holder.set(logContext);

        writeLog(holder.get().contextCount + startCount, id, new HashMap<>(), 0);
    }

    /**
     * 参数params是由key和value组成的数组
     * @param id
     * @param params
     */
//    public static void write(String id, String... params){
//        Map<String, String> map = new HashMap<>();
//        for(int i = 0; i < params.length; i = i+2){
//            map.put(params[i].toString(), params[i+1]);
//        }
//
//        holder.get().contextCount++;
//        jvmStackCurrLevel = new Throwable().getStackTrace().length;
//        Log log = new Log();
//        log.id = id;
//        log.distance = jvmStackCurrLevel - jvmStackInitLevel + 1;
//        log.time = System.currentTimeMillis();
//
//        long elsp = calcElsp(log);
//
//        writeLog(holder.get().contextCount + startCount, map, elsp);
//
//        jvmStackPrevLevel = jvmStackCurrLevel;
//    }

    public static void write(String id, Map<String, String> params) {
        holder.get().contextCount++;
        jvmStackCurrLevel = new Throwable().getStackTrace().length;
        Log log = new Log();
        log.id = id;
        log.distance = jvmStackCurrLevel - jvmStackInitLevel + 1;
        log.time = System.currentTimeMillis();

        long elsp = calcElsp(log);

        writeLog(holder.get().contextCount + startCount, id, params, elsp);

        jvmStackPrevLevel = jvmStackCurrLevel;
    }

    private static long calcElsp(Log log){

        long elsp = 0;
        if (jvmStackCurrLevel == jvmStackPrevLevel && log.distance == holder.get().stack.peek().distance) {
            //在同一级并且有入栈
            elsp = log.time - holder.get().stack.peek().time;
        } else if (jvmStackCurrLevel > jvmStackPrevLevel) {
            //发生了函数调用
            holder.get().stack.push(log);
        } else {
            //发生了函数返回
            Log tempLog;
            do {
                tempLog = holder.get().stack.pop();
            } while (tempLog.distance > log.distance);
            holder.get().stack.push(tempLog);
            if (tempLog.distance == log.distance) {
                //存在入栈
                elsp = log.time - tempLog.time;
            }
        }
        return elsp;
    }

    private static void writeLog(int contextCount, String id, Map<String, String> map, long elsp){
        StringBuffer sb = new StringBuffer();
        sb.append(holder.get().contextId);
        sb.append("_").append(contextCount);
        holder.get().stack.stream().forEach(s -> sb.append("_").append(s.id));
        sb.append(" ").append(id);
        sb.append(" ").append(JSON.toJSONString(map));
        sb.append(" ").append("stackTime:").append(elsp);
        logger.info(sb.toString());
    }
}
