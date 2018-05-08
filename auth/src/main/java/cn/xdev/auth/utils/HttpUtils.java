package cn.xdev.auth.utils;

import com.alibaba.fastjson.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by felix on 2017-06-02-0002.
 */
public class HttpUtils {
    public static JSONObject loadJSON(String url) {
        String jsonStr = "";
        try {
            URL wechat = new URL(url);
            HttpURLConnection connection = (HttpURLConnection) wechat.openConnection();

            // 设置连接属性
            connection.setConnectTimeout(3000);
            connection.setDoInput(true);
            connection.setRequestMethod("GET");

            // 获取相应码
            int respCode = connection.getResponseCode();
            if (respCode == 200) {

                // ByteArrayOutputStream相当于内存输出流
                ByteArrayOutputStream out = new ByteArrayOutputStream();
                byte[] buffer = new byte[1024];
                int len = 0;
                // 将输入流转移到内存输出流中
                try {
                    while ((len = connection.getInputStream().read(buffer, 0, buffer.length)) != -1) {
                        out.write(buffer, 0, len);
                    }
                    // 将内存流转换为字符串
                    jsonStr = new String(out.toByteArray());
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return JSONObject.parseObject(jsonStr);
    }
}
