package cn.xdev.auth.authorization.model;

import cn.xdev.core.entity.BaseModel;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class SysUserAppRoles extends BaseModel {
    private Integer id;

    private Integer user_id;

    private Integer app_id;

    private String role_ids;

    private List<Integer> roleIds;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    public Integer getApp_id() {
        return app_id;
    }

    public void setApp_id(Integer app_id) {
        this.app_id = app_id;
    }

    public String getRole_ids() {
        if (role_ids == null || role_ids.isEmpty()) {
            if (CollectionUtils.isEmpty(roleIds)) {
                return role_ids;
            }
            StringBuilder s = new StringBuilder();
            for (Integer roleId : roleIds) {
                s.append(roleId);
                s.append(",");
            }
            role_ids = s.toString();
        }
        return role_ids;
    }

    public void setRole_ids(String role_ids) {
        this.role_ids = role_ids == null ? null : role_ids.trim();
    }

    public List<Integer> getRoleIds() {
        List<Integer> roleIds = new ArrayList<>();
        if (!StringUtils.isEmpty(role_ids)) {
            String[] roleIdStrs = role_ids.split(",");
            for (String roleIdStr : roleIdStrs) {
                if (StringUtils.isEmpty(roleIdStr)) {
                    continue;
                }
                roleIds.add(Integer.valueOf(roleIdStr));
            }
        }
        return roleIds;
    }

    public void setRoleIds(List<Integer> roleIds) {
        this.roleIds = roleIds;
    }
}