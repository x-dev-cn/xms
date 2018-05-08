package cn.xdev.auth.app.service.impl;

import cn.xdev.auth.app.dao.SysAppMapper;
import cn.xdev.auth.app.model.SysApp;
import cn.xdev.auth.app.model.SysAppExample;
import cn.xdev.auth.app.service.AppService;
import cn.xdev.core.generic.GenericDao;
import cn.xdev.core.generic.GenericServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by felix on 2015-12-28-0028.
 */
@Service
public class AppServiceImpl extends GenericServiceImpl<SysApp, Integer> implements AppService {

    @Resource
    private SysAppMapper sysAppMapper;

    @Override
    public SysApp createApp(SysApp app) {
        app.setAvailable(Boolean.TRUE);
        sysAppMapper.insertSelective(app);
        return app;
    }

    @Override
    public SysApp updateApp(SysApp app) {
        sysAppMapper.updateByPrimaryKeySelective(app);
        return app;
    }

    @Override
    public void deleteApp(Integer appId) {
        sysAppMapper.deleteByPrimaryKey(appId);
    }

    @Override
    public SysApp findOne(Integer appId) {
        return sysAppMapper.selectByPrimaryKey(appId);
    }

    @Override
    public List<SysApp> findAll() {
        return sysAppMapper.selectByExample(new SysAppExample());
    }

    @Override
    public Integer findAppIdByAppKey(String appKey) {
        SysAppExample example = new SysAppExample();
        example.createCriteria().andApp_keyEqualTo(appKey).andAvailableEqualTo(Boolean.TRUE);
        List<SysApp> list = sysAppMapper.selectByExample(example);
        if (list.size() == 0) {
            return null;
        }
        return list.get(0).getId();
    }

    @Override
    public GenericDao<SysApp, Integer> getDao() {
        return sysAppMapper;
    }
}
