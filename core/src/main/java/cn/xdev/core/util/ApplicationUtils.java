package cn.xdev.core.util;

import org.apache.commons.codec.digest.DigestUtils;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

/**
 * ApplicationUtils : 程序工具类，提供大量的便捷方法
 *
 * @author Felix.Hsu
 * @since 2015-08-18 16:17
 */
public class ApplicationUtils {

    /**
     * 产生一个36个字符的UUID
     *
     * @return UUID
     */
    public static String randomUUID() {
        return UUID.randomUUID().toString();
    }

    /**
     * md5加密
     *
     * @param value 要加密的值
     * @return md5加密后的值
     */
    public static String md5Hex(String value) {
        return DigestUtils.md5Hex(value);
    }

    /**
     * sha1加密
     *
     * @param value 要加密的值
     * @return sha1加密后的值
     */
    public static String sha1Hex(String value) {
        return DigestUtils.sha1Hex(value);
    }

    /**
     * sha256加密
     *
     * @param value 要加密的值
     * @return sha256加密后的值
     */
    public static String sha256Hex(String value) {
        return DigestUtils.sha256Hex(value);
    }

    public static String getFirstLetterUpper(String s) {
        String first = s.substring(0, 1);
        String tmp = s.substring(1, s.length());

        return first.toUpperCase() + tmp;
    }

    public static Object setModelCharset(Object obj) throws Exception {
        Class entityClassType = obj.getClass();
        Field[] entityFields = entityClassType.getDeclaredFields();

        for (int i = 0; i < entityFields.length; i++) {
            Field field = entityFields[i];
            String fieldName = field.getName();
            String firstLetter = fieldName.substring(0, 1).toUpperCase();

            String setMethodName = "set" + firstLetter + fieldName.substring(1);

            String getMethodName = "get" + firstLetter + fieldName.substring(1);

            Field getEntityField = entityClassType.getDeclaredField(fieldName);

            Method getMethod = entityClassType.getMethod(getMethodName,
                    new Class[0]);
            Object value = getMethod.invoke(obj, new Object[0]);
            Method setMethod = entityClassType.getMethod(setMethodName,
                    new Class[]{getEntityField.getType()});

            Class type = getEntityField.getType();
            if ((value != null) && (!"".equals(value.toString().trim()))) {
                Object result = "";
                if (type.equals(Date.class)) {
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                    result = sdf.format(value);
                } else if (type.equals(String.class)) {
                    result = (String) value;
                    result = new String(result.toString().getBytes("ISO-8859-1"), "UTF-8");
                } else {
                    result = value;
                }
                setMethod.invoke(obj, new Object[]{result});
            }
        }
        return obj;
    }

    /**
     * 将元数据前补零，补后的总长度为指定的长度，以字符串的形式返回
     *
     * @param sourceDate
     * @param formatLength
     * @return 重组后的数据
     */
    public static String frontCompWithZore(int sourceDate, int formatLength) {
        /*
       * 0 指前面补充零
       * formatLength 字符总长度为 formatLength
       * d 代表为正数。
       */
        String newString = String.format("%0" + formatLength + "d", sourceDate);
        return newString;
    }

    public static String encoder(String source) {
        return encoder(source, "UTF-8");
    }

    public static String encoder(String source, String charset) {
        try {
            return URLEncoder.encode(source, charset);
        } catch (UnsupportedEncodingException e) {
            return "";
        }
    }

    public static String decoder(String source) {
        return decoder(source, "UTF-8");
    }

    public static String decoder(String source, String charset) {
        try {
            return URLDecoder.decode(source, charset);
        } catch (UnsupportedEncodingException e) {
            return "";
        }
    }
}
