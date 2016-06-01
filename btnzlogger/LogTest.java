package com.pajk.consult.commons.log.btnzlogger;

import org.junit.Test;

/**
 * Created by zhangsongwei on 2016/5/31.
 */
public class LogTest {
    @Test
    public void test(){
        new Service().doService(123, 321, "params");
    }
}
