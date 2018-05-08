package cn.xdev.auth.utils.taglib;

import cn.xdev.auth.app.model.SysApp;
import cn.xdev.auth.app.service.AppService;
import cn.xdev.auth.organization.model.SysOrganization;
import cn.xdev.auth.organization.service.OrganizationService;
import cn.xdev.auth.resource.model.SysResource;
import cn.xdev.auth.resource.service.ResourceService;
import cn.xdev.auth.role.model.SysRole;
import cn.xdev.auth.role.service.RoleService;
import cn.xdev.auth.user.model.SysUser;
import cn.xdev.auth.user.service.UserService;
import cn.xdev.core.util.SpringUtils;
import org.springframework.util.CollectionUtils;

import java.util.Collection;

/**
 * <p>User: Felix.Hsu
 * <p>Date: 14-2-15
 * <p>Version: 1.0
 */
public class Functions {

    public static boolean in(Iterable iterable, Object element) {
        if (iterable == null) {
            return false;
        }
        return CollectionUtils.contains(iterable.iterator(), element);
    }

    public static String username(Integer userId) {
        SysUser user = getUserService().findOne(userId);
        if (user == null) {
            return "";
        }
        return user.getUsername();
    }

    public static String appName(Integer appId) {
        SysApp app = getAppService().findOne(appId);
        if (app == null) {
            return "";
        }
        return app.getName();
    }

    public static String organizationName(Integer organizationId) {
        SysOrganization organization = getOrganizationService().findOne(organizationId);
        if(organization == null) {
            return "";
        }
        return organization.getName();
    }

    public static String organizationNames(Collection<Integer> organizationIds) {
        if(CollectionUtils.isEmpty(organizationIds)) {
            return "";
        }

        StringBuilder s = new StringBuilder();
        for(Integer organizationId : organizationIds) {
            SysOrganization organization = getOrganizationService().findOne(organizationId);
            if(organization == null) {
                return "";
            }
            s.append(organization.getName());
            s.append(",");
        }

        if(s.length() > 0) {
            s.deleteCharAt(s.length() - 1);
        }

        return s.toString();
    }


    public static String roleName(Integer roleId) {
        SysRole role = getRoleService().findOne(roleId);
        if (role == null) {
            return "";
        }
        return role.getDescription();
    }

    public static String roleNames(Collection<Integer> roleIds) {
        if (CollectionUtils.isEmpty(roleIds)) {
            return "";
        }

        StringBuilder s = new StringBuilder();
        for (Integer roleId : roleIds) {
            SysRole role = getRoleService().findOne(roleId);
            if (role == null) {
                return "";
            }
            s.append(role.getDescription());
            s.append(",");
        }

        if (s.length() > 0) {
            s.deleteCharAt(s.length() - 1);
        }

        return s.toString();
    }

    public static String resourceName(Integer resourceId) {
        SysResource resource = getResourceService().findOne(resourceId);
        if (resource == null) {
            return "";
        }
        return resource.getName();
    }

    public static String resourceNames(Collection<Integer> resourceIds) {
        if (CollectionUtils.isEmpty(resourceIds)) {
            return "";
        }

        StringBuilder s = new StringBuilder();
        for (Integer resourceId : resourceIds) {
            SysResource resource = getResourceService().findOne(resourceId);
            if (resource == null) {
                return "";
            }
            s.append(resource.getName());
            s.append(",");
        }

        if (s.length() > 0) {
            s.deleteCharAt(s.length() - 1);
        }

        return s.toString();
    }


    public static Boolean resourceHasChildren(Integer resourceId) {
        return getResourceService().hasChildren(resourceId);
    }

    private static OrganizationService organizationService;
    private static RoleService roleService;
    private static ResourceService resourceService;
    private static UserService userService;
    private static AppService appService;

    public static UserService getUserService() {
        if (userService == null) {
            userService = SpringUtils.getBean(UserService.class);
        }
        return userService;
    }

    public static AppService getAppService() {
        if (appService == null) {
            appService = SpringUtils.getBean(AppService.class);
        }
        return appService;
    }

    public static OrganizationService getOrganizationService() {
        if(organizationService == null) {
            organizationService = SpringUtils.getBean(OrganizationService.class);
        }
        return organizationService;
    }

    public static RoleService getRoleService() {
        if (roleService == null) {
            roleService = SpringUtils.getBean(RoleService.class);
        }
        return roleService;
    }

    public static ResourceService getResourceService() {
        if (resourceService == null) {
            resourceService = SpringUtils.getBean(ResourceService.class);
        }
        return resourceService;
    }
}

