package com.ryan.hprose.daemon;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 定时任务测试类
 *
 * @author zhangenrui
 */
public class TestTask {

    public void run() {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println(df.format(new Date()));
    }
}
