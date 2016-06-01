package com.pajk.consult.commons.btnzlog;

import java.util.Stack;

/**
 * Created by zhangsongwei on 2016/5/31.
 */
public class LogContext {
    public Stack<Log> stack = new Stack();
    public int contextCount = 0;
    public String contextId = "";
}
