package cn.xdev.auth.resource.dao;

import cn.xdev.auth.resource.model.SysResource;
import cn.xdev.auth.resource.model.SysResourceExample;
import cn.xdev.core.generic.GenericDao;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SysResourceMapper extends GenericDao<SysResource, Integer> {
    int countByExample(SysResourceExample example);

    int deleteByExample(SysResourceExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(SysResource record);

    int insertSelective(SysResource record);

    List<SysResource> selectByExample(SysResourceExample example);

    SysResource selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") SysResource record, @Param("example") SysResourceExample example);

    int updateByExample(@Param("record") SysResource record, @Param("example") SysResourceExample example);

    int updateByPrimaryKeySelective(SysResource record);

    int updateByPrimaryKey(SysResource record);
}