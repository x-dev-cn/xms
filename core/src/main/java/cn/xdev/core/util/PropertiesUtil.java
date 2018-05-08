package cn.xdev.core.util;

import java.io.*;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * PropertiesUtil.java
 *
 * @desc properties 资源文件解析工具
 * @datatime Apr 7, 2016 09:02:37 PM
 */
public class PropertiesUtil {
    private Properties props;
    private URI uri;

    public PropertiesUtil(String fileName) {
        try {
            uri = this.getClass().getResource(fileName).toURI();

        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        readProperties(fileName);
    }

    private void readProperties(String fileName) {
        try {
            props = new Properties();
            InputStreamReader fis = new InputStreamReader(getClass().getResourceAsStream(fileName), "UTF-8");
            props.load(fis);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取某个属性
     */
    public String getProperty(String key) {
        return props.getProperty(key);
    }

    /**
     * 获取所有属性，返回一个map,不常用
     * 可以试试props.putAll(t)
     */
    public Map getAllProperty() {
        Map map = new HashMap();
        Enumeration enu = props.propertyNames();
        while (enu.hasMoreElements()) {
            String key = (String) enu.nextElement();
            String value = props.getProperty(key);
            map.put(key, value);
        }
        return map;
    }

    /**
     * 在控制台上打印出所有属性，调试时用。
     */
    public void printProperties() {
        props.list(System.out);
    }

    /**
     * 写入properties信息
     */
    public void writeProperties(String key, String value) {
        try {
            OutputStream fos = new FileOutputStream(new File(uri));
            props.setProperty(key, value);
            // 将此 Properties 表中的属性列表（键和元素对）写入输出流
            props.store(fos, "『comments』Update key：" + key);
        } catch (IOException e) {
        }
    }

    public static void main(String[] args) {
        PropertiesUtil util = new PropertiesUtil("/config.properties");
        System.out.println("ip=" + util.getProperty("ip"));
        util.writeProperties("key", "value0");
    }
}
