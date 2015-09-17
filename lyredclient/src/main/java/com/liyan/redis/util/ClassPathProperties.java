package com.liyan.redis.util;


import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.log4j.Logger;

/**
 * @author liyan
 *
 */
public class ClassPathProperties {
	protected Logger logger = Logger.getLogger(getClass().getName());
    private String propertyFileName;

    private Properties properties;

    private boolean loaded = false;
    
    public ClassPathProperties() {

    }

    public ClassPathProperties(String propertyFileName) {
        this.propertyFileName = propertyFileName;
    }

    public String getPropertyFileName() {
        return this.propertyFileName;
    }

    public void setPropertyFileName(String propertyFileName) {
        this.propertyFileName = propertyFileName;
    }

    /**
     * 获得属性对象方法
     *
     * @return 返回一个properites文件
     */
    public Properties getProperties() {
        if (!loaded) {
            load();
        }
        return properties;
    }

    /**
     * 根据key获得对应的属性值
     *
     * @param key 属性key
     * @return key对应的属性值
     */
    public String getProperty(String key) {
        if (!loaded) {
            load();
        }
        String value = null;
        if (properties != null) {
            value = (String) properties.get(key);
        }
        return value;

    }

    private void load() {
        try {
        	InputStream is = new FileInputStream(this.getClass().getResource("/").getPath()+this.propertyFileName);
        	properties = new Properties();
            properties.load(is);
            loaded = true;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}