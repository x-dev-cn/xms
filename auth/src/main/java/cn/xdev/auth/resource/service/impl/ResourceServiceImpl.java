package cn.xdev.auth.resource.service.impl;


import cn.xdev.auth.resource.dao.SysResourceMapper;
import cn.xdev.auth.resource.model.SysResource;
import cn.xdev.auth.resource.model.SysResourceExample;
import cn.xdev.auth.resource.service.ResourceService;
import cn.xdev.core.generic.GenericDao;
import cn.xdev.core.generic.GenericServiceImpl;
import org.apache.shiro.authz.permission.WildcardPermission;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by felix on 2015-12-28-0028.
 */
@Service
public class ResourceServiceImpl extends GenericServiceImpl<SysResource, Integer> implements ResourceService {

    @Resource
    private SysResourceMapper sysResourceMapper;

    @Override
    public GenericDao<SysResource, Integer> getDao() {
        return sysResourceMapper;
    }

    @Override
    public SysResource createResource(SysResource resource) {
        resource.setAvailable(Boolean.TRUE);
        sysResourceMapper.insertSelective(resource);
        return resource;
    }

    @Override
    public SysResource updateResource(SysResource resource) {
        sysResourceMapper.updateByPrimaryKeySelective(resource);
        return resource;
    }

    @Override
    public void deleteResource(Integer resourceId) {
        SysResource sysResource = findOne(resourceId);

        sysResourceMapper.deleteByPrimaryKey(resourceId);
        SysResourceExample example = new SysResourceExample();
        example.createCriteria().andParent_idsLike(sysResource.makeSelfAsParentIds() + "%");
        sysResourceMapper.deleteByExample(example);
    }

    @Override
    public SysResource findOne(Integer resourceId) {
        return sysResourceMapper.selectByPrimaryKey(resourceId);
    }

    @Override
    public List<SysResource> findAll() {
        SysResourceExample example = new SysResourceExample();
        example.createCriteria().andAvailableEqualTo(Boolean.TRUE);
        example.setOrderByClause("id");
        return sysResourceMapper.selectByExample(example);
    }

    @Override
    public Set<String> findPermissions(Set<Integer> resourceIds) {
        Set<String> permissions = new HashSet<String>();
        for (Integer resourceId : resourceIds) {
            SysResource resource = findOne(resourceId);
            if (resource != null && !StringUtils.isEmpty(resource.getPermission())) {
                permissions.add(resource.getPermission());
            }
        }
        return permissions;
    }

    @Override
    public List<SysResource> findMenus(Set<String> permissions) {
        List<SysResource> allResources = findAll();
        List<SysResource> menus = new ArrayList<>();
        for (SysResource resource : allResources) {
            if (resource.isRootNode()) {
                continue;
            }
            if (!resource.getType().equals("menu")) {
                continue;
            }
            if (!hasPermission(permissions, resource)) {
                continue;
            }
            menus.add(resource);
        }
        return menus;
    }

    @Override
    public Boolean hasChildren(Integer resourceId) {
        SysResourceExample example = new SysResourceExample();
        example.createCriteria().andAvailableEqualTo(Boolean.TRUE)
                .andParent_idEqualTo(resourceId).andTypeEqualTo("menu");
        List<?> list = sysResourceMapper.selectByExample(example);
        if (list.size() > 0)
            return true;
        return false;
    }

    @Override
    public List<SysResource> selectListByAllParents(List<Integer> ids) {
        SysResourceExample example = new SysResourceExample();
        example.createCriteria().andAvailableEqualTo(Boolean.TRUE)
                .andIdIn(ids).andTypeEqualTo("menu");
        List<SysResource> list = sysResourceMapper.selectByExample(example);
        return list;
    }

    @Override
    public List<SysResource> selectChildrenById(int id) {
        SysResourceExample example = new SysResourceExample();
        example.createCriteria().andAvailableEqualTo(Boolean.TRUE)
                .andParent_idEqualTo(id).andTypeEqualTo("menu");
        List<SysResource> list = sysResourceMapper.selectByExample(example);
        return list;
    }

    @Override
    public List<SysResource> selectLastChildren(int cmsId) {
        SysResourceExample example = new SysResourceExample();
        example.createCriteria().andAvailableEqualTo(Boolean.TRUE)
                .andTypeEqualTo("menu").andParent_idsLike("%/" + cmsId + "%");
        example.setOrderByClause("id");
        List<SysResource> list = sysResourceMapper.selectByExample(example);
        List<SysResource> result = new ArrayList<>();
        for (SysResource resource : list) {
            if (!hasChildren(resource.getId())) {
                String[] ids = resource.getParent_ids().split("/");
                List<Integer> colIds = new ArrayList<>();
                for (String id : ids) {
                    if (!id.isEmpty() && Integer.valueOf(id) > cmsId)
                        colIds.add(Integer.valueOf(id));
                }
                if (colIds.size() > 0) {
                    List<SysResource> parents = selectListByAllParents(colIds);
                    resource.setParent_ids("");
                    for (SysResource parent : parents) {
                        resource.setParent_ids(resource.getParent_ids() + "/" + parent.getName());
                    }
                }else{
                    resource.setParent_ids("");
                }
                result.add(resource);
            }
        }
        return result;
    }

    private boolean hasPermission(Set<String> permissions, SysResource resource) {
        if (StringUtils.isEmpty(resource.getPermission())) {
            return true;
        }
        for (String permission : permissions) {
            WildcardPermission p1 = new WildcardPermission(permission);
            WildcardPermission p2 = new WildcardPermission(resource.getPermission());
            if (p1.implies(p2) || p2.implies(p1)) {
                return true;
            }
        }
        return false;
    }
}
