package cn.xdev.auth.authorization.service;

import cn.xdev.auth.authorization.model.SysUserAppRoles;
import cn.xdev.core.generic.GenericService;

import java.util.List;
import java.util.Set;

/**
 * Created by felix on 2015-12-28-0028.
 */
public interface AuthorizationService extends GenericService<SysUserAppRoles, Integer> {

    SysUserAppRoles createAuthorization(SysUserAppRoles authorization);
    SysUserAppRoles updateAuthorization(SysUserAppRoles authorization);
    void deleteAuthorization(Integer authorizationId);

    SysUserAppRoles findOne(Integer authorizationId);
    List<SysUserAppRoles> findAll();

    /**
     * 根据AppKey和用户名查找其角色
     * @param username
     * @return
     */
    Set<String> findRoles(String appKey, String username);

    /**
     * 根据AppKey和用户名查找权限字符串
     * @param username
     * @return
     */
    Set<String> findPermissions(String appKey, String username);
}
