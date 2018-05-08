package cn.xdev.auth.role.service.impl;

import cn.xdev.auth.resource.service.ResourceService;
import cn.xdev.auth.role.dao.SysRoleMapper;
import cn.xdev.auth.role.model.SysRole;
import cn.xdev.auth.role.model.SysRoleExample;
import cn.xdev.auth.role.service.RoleService;
import cn.xdev.core.generic.GenericDao;
import cn.xdev.core.generic.GenericServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by felix on 2015-12-28-0028.
 */
@Service
public class RoleServiceImpl extends GenericServiceImpl<SysRole, Integer> implements RoleService {

    @Resource
    private SysRoleMapper sysRoleMapper;

    @Autowired
    private ResourceService resourceService;

    @Override
    public GenericDao<SysRole, Integer> getDao() {
        return sysRoleMapper;
    }

    @Override
    public SysRole createRole(SysRole role) {
        role.setAvailable(Boolean.TRUE);
        sysRoleMapper.insertSelective(role);
        return role;
    }

    @Override
    public SysRole updateRole(SysRole role) {
        sysRoleMapper.updateByPrimaryKeySelective(role);
        return role;
    }

    @Override
    public void deleteRole(Integer roleId) {
        sysRoleMapper.deleteByPrimaryKey(roleId);
    }

    @Override
    public SysRole findOne(Integer roleId) {
        return sysRoleMapper.selectByPrimaryKey(roleId);
    }

    @Override
    public List<SysRole> findAll() {
        return sysRoleMapper.selectByExample(new SysRoleExample());
    }

    @Override
    public Set<String> findRoles(Integer... roleIds) {
        Set<String> roles = new HashSet<String>();
        for(Integer roleId : roleIds) {
            SysRole role = findOne(roleId);
            if(role != null) {
                roles.add(role.getRole());
            }
        }
        return roles;
    }

    @Override
    public Set<String> findPermissions(Integer[] roleIds) {
        Set<Integer> resourceIds = new HashSet<Integer>();
        for(Integer roleId : roleIds) {
            SysRole role = findOne(roleId);
            if(role != null) {
                resourceIds.addAll(role.getResourceIds());
            }
        }
        return resourceService.findPermissions(resourceIds);
    }
}
