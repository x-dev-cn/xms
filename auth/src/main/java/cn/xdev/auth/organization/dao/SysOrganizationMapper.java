package cn.xdev.auth.organization.dao;

import cn.xdev.auth.organization.model.SysOrganization;
import cn.xdev.auth.organization.model.SysOrganizationExample;
import cn.xdev.core.generic.GenericDao;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SysOrganizationMapper extends GenericDao<SysOrganization, Integer> {
    int countByExample(SysOrganizationExample example);

    int deleteByExample(SysOrganizationExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(SysOrganization record);

    int insertSelective(SysOrganization record);

    List<SysOrganization> selectByExample(SysOrganizationExample example);

    SysOrganization selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") SysOrganization record, @Param("example") SysOrganizationExample example);

    int updateByExample(@Param("record") SysOrganization record, @Param("example") SysOrganizationExample example);

    int updateByPrimaryKeySelective(SysOrganization record);

    int updateByPrimaryKey(SysOrganization record);

    int move(@Param("target") String targetIds, @Param("source") String sourceIds, @Param("self") String selfIds);

    int diffOrderNoByExample(@Param("example") SysOrganizationExample example);

    int addOrderNoByExample(@Param("example") SysOrganizationExample example);
}