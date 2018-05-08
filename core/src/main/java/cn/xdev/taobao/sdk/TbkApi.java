package cn.xdev.taobao.sdk;

import cn.xdev.core.util.PropertiesUtil;

public class TbkApi {

    private PropertiesUtil propertiesUtil;

    private String api_url;
    private String api_url_sanbox;
    private String app_key;
    private String app_secret;
    private Long adzone_id;

    public PropertiesUtil getPropertiesUtil() {
        if (propertiesUtil == null) {
            propertiesUtil = new PropertiesUtil("/tbk.properties");
        }
        return propertiesUtil;
    }

    public String getApi_url() {
        if (api_url == null) {
            api_url = getPropertiesUtil().getProperty("apiHttpUrl");
        }
        return api_url;
    }

    public String getApi_url_sanbox() {
        if (api_url_sanbox == null) {
            api_url_sanbox = getPropertiesUtil().getProperty("apiSandboxUrl");
        }
        return api_url_sanbox;
    }

    public String getApp_key() {
        if (app_key == null) {
            app_key = getPropertiesUtil().getProperty("appKeyId");
        }
        return app_key;
    }

    public String getApp_secret() {
        if (app_secret == null) {
            app_secret = getPropertiesUtil().getProperty("appKeySecret");
        }
        return app_secret;
    }

    public Long getAdzone_id() {
        if (adzone_id == null) {
            adzone_id = Long.valueOf(getPropertiesUtil().getProperty("adzone_id"));
        }
        return adzone_id;
    }

    public void setAdzone_id(Long adzone_id) {
        this.adzone_id = adzone_id;
    }
}
