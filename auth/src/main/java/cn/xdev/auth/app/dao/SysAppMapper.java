package cn.xdev.auth.app.dao;

import cn.xdev.auth.app.model.SysApp;
import cn.xdev.auth.app.model.SysAppExample;
import cn.xdev.core.generic.GenericDao;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SysAppMapper extends GenericDao<SysApp, Integer> {
    int countByExample(SysAppExample example);

    int deleteByExample(SysAppExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(SysApp record);

    int insertSelective(SysApp record);

    List<SysApp> selectByExample(SysAppExample example);

    SysApp selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") SysApp record, @Param("example") SysAppExample example);

    int updateByExample(@Param("record") SysApp record, @Param("example") SysAppExample example);

    int updateByPrimaryKeySelective(SysApp record);

    int updateByPrimaryKey(SysApp record);
}