package cn.xdev.auth.session.model;

import cn.xdev.core.entity.BaseModel;

public class Sessions extends BaseModel {
    private String id;

    private String session;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getSession() {
        return session;
    }

    public void setSession(String session) {
        this.session = session == null ? null : session.trim();
    }
}