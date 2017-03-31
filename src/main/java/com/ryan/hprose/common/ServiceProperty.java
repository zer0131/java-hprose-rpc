package com.ryan.hprose.common;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ResourceBundle;

/**
 * service properties 调用类
 *
 * @author zhangenrui
 */
public class ServiceProperty {

    private static final Logger logger = LoggerFactory.getLogger(ServiceProperty.class);

    private static volatile ServiceProperty instance = null;

    private String redisHostname;
    private int redisPort;
    private boolean isDev = false;

    private ServiceProperty () {
        try {
            ResourceBundle resourceBundler = ResourceBundle.getBundle("config/service");

            if (resourceBundler != null) {
                this.redisHostname = resourceBundler.getString("redis.hostname").trim();
                logger.info("execute service property success, redis.hostname: {}", this.redisHostname);

                String port = resourceBundler.getString("redis.port").trim();
                this.redisPort = Integer.parseInt(port);
                logger.info("execute service property success, redis.port: {}", port);

                String dev = resourceBundler.getString("isdev").trim();
                if ("YES".equals(dev)) {
                    this.isDev = true;
                }
                logger.info("execute service property success, isdev: {}", dev);
            }
        } catch (Exception e) {
            logger.error("execute service property error, message: {}", e.getMessage());
        }
    }

    public String getRedisHostname() {
        return redisHostname;
    }

    public void setRedisHostname(String redisHostname) {
        this.redisHostname = redisHostname;
    }

    public int getRedisPort() {
        return redisPort;
    }

    public void setRedisPort(int redisPort) {
        this.redisPort = redisPort;
    }

    public boolean isDev() {
        return isDev;
    }

    public void setDev(boolean dev) {
        isDev = dev;
    }
}
