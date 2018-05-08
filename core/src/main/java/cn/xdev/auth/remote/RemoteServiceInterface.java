/**
 * Copyright (c) 2005-2012 https://github.com/zhangkaitao
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 */
package cn.xdev.auth.remote;

import org.apache.shiro.session.Session;

import java.io.Serializable;

/**
 * <p>User: Felix.Hsu
 * <p>Date: 14-3-13
 * <p>Version: 1.0
 */
public interface RemoteServiceInterface {

    Session getSession(String appKey, Serializable sessionId);
    Serializable createSession(Session session);
    void updateSession(String appKey, Session session);
    void deleteSession(String appKey, Session session);

    PermissionContext getPermissions(String appKey, String username);
}
