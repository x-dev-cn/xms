package cn.xdev.auth.app.service;

import cn.xdev.auth.app.model.SysApp;
import cn.xdev.core.generic.GenericService;

import java.util.List;

/**
 * Created by felix on 2015-12-28-0028.
 */
public interface AppService extends GenericService<SysApp, Integer> {

    SysApp createApp(SysApp app);

    SysApp updateApp(SysApp app);

    void deleteApp(Integer appId);

    SysApp findOne(Integer appId);

    List<SysApp> findAll();

    /**
     * 根据appKey查找AppId
     *
     * @param appKey
     * @return
     */
    Integer findAppIdByAppKey(String appKey);
}
