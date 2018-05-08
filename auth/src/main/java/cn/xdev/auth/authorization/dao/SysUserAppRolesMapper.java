package cn.xdev.auth.authorization.dao;

import cn.xdev.auth.authorization.model.SysUserAppRoles;
import cn.xdev.auth.authorization.model.SysUserAppRolesExample;
import cn.xdev.core.generic.GenericDao;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SysUserAppRolesMapper extends GenericDao<SysUserAppRoles, Integer> {
    int countByExample(SysUserAppRolesExample example);

    int deleteByExample(SysUserAppRolesExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(SysUserAppRoles record);

    int insertSelective(SysUserAppRoles record);

    List<SysUserAppRoles> selectByExample(SysUserAppRolesExample example);

    SysUserAppRoles selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") SysUserAppRoles record, @Param("example") SysUserAppRolesExample example);

    int updateByExample(@Param("record") SysUserAppRoles record, @Param("example") SysUserAppRolesExample example);

    int updateByPrimaryKeySelective(SysUserAppRoles record);

    int updateByPrimaryKey(SysUserAppRoles record);
}