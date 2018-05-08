package cn.xdev.auth.organization.service.impl;

import cn.xdev.auth.organization.dao.SysOrganizationMapper;
import cn.xdev.auth.organization.model.SysOrganization;
import cn.xdev.auth.organization.model.SysOrganizationExample;
import cn.xdev.auth.organization.service.OrganizationService;
import cn.xdev.core.generic.GenericDao;
import cn.xdev.core.generic.GenericServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by felix on 2016-06-23-0023.
 */
@Service
public class OrganizationServiceImpl extends GenericServiceImpl<SysOrganization, Integer> implements OrganizationService {

    @Resource
    private SysOrganizationMapper sysOrganizationMapper;

    @Override
    public SysOrganization createOrganization(SysOrganization sysOrganization) {
        sysOrganization.setAvailable(Boolean.TRUE);
        sysOrganizationMapper.insertSelective(sysOrganization);
        return sysOrganization;
    }

    @Override
    public SysOrganization updateOrganization(SysOrganization sysOrganization) {
        sysOrganizationMapper.updateByPrimaryKeySelective(sysOrganization);
        return sysOrganization;
    }

    @Override
    public void deleteOrganization(int organizationId) {
        SysOrganization sysOrganization = findOne(organizationId);

        sysOrganizationMapper.deleteByPrimaryKey(organizationId);

        SysOrganizationExample example = new SysOrganizationExample();
        example.createCriteria().andParent_idsLike(sysOrganization.makeSelfAsParentIds() + "%");
        sysOrganizationMapper.deleteByExample(example);
    }

    @Override
    public SysOrganization findOne(int organizationId) {
        return sysOrganizationMapper.selectByPrimaryKey(organizationId);
    }

    @Override
    public List<SysOrganization> findAll() {
        return sysOrganizationMapper.selectByExample(new SysOrganizationExample());
    }

    @Override
    public List<SysOrganization> findAllWithExclude(SysOrganization excludeOraganization) {
        SysOrganizationExample example = new SysOrganizationExample();
        example.createCriteria().andIdNotEqualTo(excludeOraganization.getId()).andParent_idsNotLike(excludeOraganization.makeSelfAsParentIds() + "%");
        return sysOrganizationMapper.selectByExample(example);
    }

    @Override
    public void updateMove(SysOrganization source, SysOrganization target) {
        SysOrganization organization = new SysOrganization();
        organization.setId(source.getId());
        organization.setParent_id(target.getId());
        organization.setParent_ids(target.getParent_ids());
        organization.setPosition(target.getPosition() + 1);
        sysOrganizationMapper.updateByPrimaryKeySelective(organization);

        sysOrganizationMapper.move(target.makeSelfAsParentIds(), source.makeSelfAsParentIds(), source.makeSelfAsParentIds() + "%");
    }

    @Override
    public int updateMove(int id, int newParent, int newPosition) {
        SysOrganization organization = sysOrganizationMapper.selectByPrimaryKey(id);

        SysOrganization parent = sysOrganizationMapper.selectByPrimaryKey(newParent);

        int result = 0;
        String option = "";
        SysOrganizationExample example = new SysOrganizationExample();

        if (organization.getParent_id() == newParent) {
            if (organization.getPosition() > newPosition) {
                option = "add";
            } else if (organization.getPosition() < newPosition) {
                option = "diff";
            }

            if (option.equals("diff")) {
                example.createCriteria().andParent_idEqualTo(newParent).andPositionBetween(organization.getPosition(), newPosition);
                result = sysOrganizationMapper.diffOrderNoByExample(example);
            } else if (option.equals("add")) {
                example.createCriteria().andParent_idEqualTo(newParent).andPositionBetween(newPosition, organization.getPosition());
                result = sysOrganizationMapper.addOrderNoByExample(example);
            }

            if (result >= 0) {
                organization.setPosition(newPosition);
                result = sysOrganizationMapper.updateByPrimaryKeySelective(organization);
            }

        } else {
            example.createCriteria().andParent_idEqualTo(organization.getParent_id()).andPositionGreaterThan(organization.getPosition());
            result = sysOrganizationMapper.diffOrderNoByExample(example);

            if (result >= 0) {
                example = new SysOrganizationExample();
                example.createCriteria().andParent_idEqualTo(newParent).andPositionGreaterThanOrEqualTo(newPosition);
                result = sysOrganizationMapper.addOrderNoByExample(example);
            }

            if (result >= 0) {
                String source = organization.makeSelfAsParentIds();
                organization.setParent_id(newParent);
                organization.setPosition(newPosition);
                organization.setParent_ids(parent.makeSelfAsParentIds());
                result = sysOrganizationMapper.updateByPrimaryKey(organization);
                String target = organization.makeSelfAsParentIds();

                sysOrganizationMapper.move(target, source, source + "%");
            }
        }

        return result;
    }

    @Override
    public GenericDao<SysOrganization, Integer> getDao() {
        return sysOrganizationMapper;
    }
}
