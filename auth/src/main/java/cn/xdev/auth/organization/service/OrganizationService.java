package cn.xdev.auth.organization.service;

import cn.xdev.auth.organization.model.SysOrganization;
import cn.xdev.core.generic.GenericService;

import java.util.List;

/**
 * Created by felix on 2016-06-23-0023.
 */
public interface OrganizationService extends GenericService<SysOrganization, Integer> {

    SysOrganization createOrganization(SysOrganization sysOrganization);

    SysOrganization updateOrganization(SysOrganization sysOrganization);

    void deleteOrganization(int organizationId);

    SysOrganization findOne(int organizationId);

    List<SysOrganization> findAll();

    List<SysOrganization> findAllWithExclude(SysOrganization excludeOraganization);

    void updateMove(SysOrganization source, SysOrganization target);

    int updateMove(int id, int newParent, int newPosition);
}
