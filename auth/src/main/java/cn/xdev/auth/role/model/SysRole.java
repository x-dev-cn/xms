package cn.xdev.auth.role.model;

import cn.xdev.core.entity.BaseModel;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class SysRole extends BaseModel {
    private Integer id;

    private String role;

    private String description;

    private String resource_ids;

    private List<Integer> resourceIds;

    private Boolean available;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role == null ? null : role.trim();
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    public String getResource_ids() {
        if (resource_ids == null || resource_ids.isEmpty()) {

            if (CollectionUtils.isEmpty(resourceIds)) {
                return resource_ids;
            }

            StringBuilder s = new StringBuilder();
            for (Integer resourceId : resourceIds) {
                s.append(resourceId);
                s.append(",");
            }

            resource_ids = s.toString();
        }
        return resource_ids;
    }

    public void setResource_ids(String resource_ids) {
        this.resource_ids = resource_ids == null ? null : resource_ids.trim();
    }

    public Boolean getAvailable() {
        return available;
    }

    public void setAvailable(Boolean available) {
        this.available = available;
    }

    public List<Integer> getResourceIds() {
        List<Integer> resourceIds = new ArrayList<>();
        if (!StringUtils.isEmpty(resource_ids)) {
            String[] resourceIdStrs = resource_ids.split(",");
            for (String resourceIdStr : resourceIdStrs) {
                if (StringUtils.isEmpty(resourceIdStr)) {
                    continue;
                }
                resourceIds.add(Integer.valueOf(resourceIdStr));
            }
        }
        return resourceIds;
    }

    public void setResourceIds(List<Integer> resourceIds) {
        this.resourceIds = resourceIds;
    }
}