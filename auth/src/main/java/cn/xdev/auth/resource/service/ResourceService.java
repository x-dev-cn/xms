package cn.xdev.auth.resource.service;


import cn.xdev.auth.resource.model.SysResource;
import cn.xdev.core.generic.GenericService;

import java.util.List;
import java.util.Set;

/**
 * Created by felix on 2015-12-28-0028.
 */
public interface ResourceService extends GenericService<SysResource, Integer> {

    SysResource createResource(SysResource resource);

    SysResource updateResource(SysResource resource);

    void deleteResource(Integer resourceId);

    SysResource findOne(Integer resourceId);

    List<SysResource> findAll();

    /**
     * 得到资源对应的权限字符串
     *
     * @param resourceIds
     * @return
     */
    Set<String> findPermissions(Set<Integer> resourceIds);

    /**
     * 根据用户权限得到菜单
     *
     * @param permissions
     * @return
     */
    List<SysResource> findMenus(Set<String> permissions);

    Boolean hasChildren(Integer resourceId);

    List<SysResource> selectListByAllParents(List<Integer> ids);

    List<SysResource> selectChildrenById(int id);

    List<SysResource> selectLastChildren(int cmsId);
}
