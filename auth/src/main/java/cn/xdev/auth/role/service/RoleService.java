package cn.xdev.auth.role.service;

import cn.xdev.auth.role.model.SysRole;
import cn.xdev.core.generic.GenericService;

import java.util.List;
import java.util.Set;

/**
 * Created by felix on 2015-12-28-0028.
 */
public interface RoleService extends GenericService<SysRole, Integer> {

    SysRole createRole(SysRole role);

    public SysRole updateRole(SysRole role);

    public void deleteRole(Integer roleId);

    public SysRole findOne(Integer roleId);

    public List<SysRole> findAll();

    /**
     * 根据角色编号得到角色标识符列表
     *
     * @param roleIds
     * @return
     */
    Set<String> findRoles(Integer... roleIds);

    /**
     * 根据角色编号得到权限字符串列表
     *
     * @param roleIds
     * @return
     */
    Set<String> findPermissions(Integer[] roleIds);
}
