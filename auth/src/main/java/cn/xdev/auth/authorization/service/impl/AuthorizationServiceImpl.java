package cn.xdev.auth.authorization.service.impl;

import cn.xdev.auth.app.service.AppService;
import cn.xdev.auth.authorization.dao.SysUserAppRolesMapper;
import cn.xdev.auth.authorization.model.SysUserAppRoles;
import cn.xdev.auth.authorization.model.SysUserAppRolesExample;
import cn.xdev.auth.authorization.service.AuthorizationService;
import cn.xdev.auth.role.service.RoleService;
import cn.xdev.auth.user.model.SysUser;
import cn.xdev.auth.user.service.UserService;
import cn.xdev.core.generic.GenericDao;
import cn.xdev.core.generic.GenericServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.List;
import java.util.Set;

/**
 * Created by felix on 2015-12-28-0028.
 */
@Service
public class AuthorizationServiceImpl extends GenericServiceImpl<SysUserAppRoles, Integer> implements AuthorizationService {

    @Resource
    private SysUserAppRolesMapper sysUserAppRolesMapper;

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private AppService appService;

    @Override
    public GenericDao<SysUserAppRoles, Integer> getDao() {
        return sysUserAppRolesMapper;
    }

    @Override
    public SysUserAppRoles createAuthorization(SysUserAppRoles authorization) {
        sysUserAppRolesMapper.insertSelective(authorization);
        return authorization;
    }

    @Override
    public SysUserAppRoles updateAuthorization(SysUserAppRoles authorization) {
        sysUserAppRolesMapper.updateByPrimaryKeySelective(authorization);
        return authorization;
    }

    @Override
    public void deleteAuthorization(Integer authorizationId) {
        sysUserAppRolesMapper.deleteByPrimaryKey(authorizationId);
    }

    @Override
    public SysUserAppRoles findOne(Integer authorizationId) {
        return sysUserAppRolesMapper.selectByPrimaryKey(authorizationId);
    }

    @Override
    public List<SysUserAppRoles> findAll() {
        return sysUserAppRolesMapper.selectByExample(new SysUserAppRolesExample());
    }

    @Override
    public Set<String> findRoles(String appKey, String username) {
        SysUser user = userService.selectByUsername(username);
        if (user == null) {
            return Collections.EMPTY_SET;
        }
        Integer appId = appService.findAppIdByAppKey(appKey);
        if (appId == null) {
            return Collections.EMPTY_SET;
        }
        SysUserAppRolesExample example = new SysUserAppRolesExample();
        example.createCriteria().andApp_idEqualTo(appId).andUser_idEqualTo(user.getId());
        List<SysUserAppRoles> list = sysUserAppRolesMapper.selectByExample(example);
        SysUserAppRoles sysUserAppRoles = new SysUserAppRoles();
        if (list.size() > 0) {
            sysUserAppRoles = list.get(0);
            if (sysUserAppRoles == null) {
                return Collections.EMPTY_SET;
            }
        }
        return roleService.findRoles(sysUserAppRoles.getRoleIds().toArray(new Integer[0]));
    }

    @Override
    public Set<String> findPermissions(String appKey, String username) {
        SysUser user = userService.selectByUsername(username);
        if(user == null) {
            return Collections.EMPTY_SET;
        }
        Integer appId = appService.findAppIdByAppKey(appKey);
        if(appId == null) {
            return Collections.EMPTY_SET;
        }
        SysUserAppRolesExample example = new SysUserAppRolesExample();
        example.createCriteria().andApp_idEqualTo(appId).andUser_idEqualTo(user.getId());
        List<SysUserAppRoles> list = sysUserAppRolesMapper.selectByExample(example);
        SysUserAppRoles sysUserAppRoles = new SysUserAppRoles();
        if (list.size() > 0) {
            sysUserAppRoles = list.get(0);
            if (sysUserAppRoles == null) {
                return Collections.EMPTY_SET;
            }
        }
        return roleService.findPermissions(sysUserAppRoles.getRoleIds().toArray(new Integer[0]));
    }
}
