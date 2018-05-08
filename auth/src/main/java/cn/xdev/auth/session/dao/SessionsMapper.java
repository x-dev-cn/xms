package cn.xdev.auth.session.dao;

import cn.xdev.auth.session.model.Sessions;
import cn.xdev.auth.session.model.SessionsExample;
import cn.xdev.core.generic.GenericDao;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SessionsMapper extends GenericDao<Sessions, String> {
    int countByExample(SessionsExample example);

    int deleteByExample(SessionsExample example);

    int deleteByPrimaryKey(String id);

    int insert(Sessions record);

    int insertSelective(Sessions record);

    List<Sessions> selectByExampleWithBLOBs(SessionsExample example);

    List<Sessions> selectByExample(SessionsExample example);

    Sessions selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") Sessions record, @Param("example") SessionsExample example);

    int updateByExampleWithBLOBs(@Param("record") Sessions record, @Param("example") SessionsExample example);

    int updateByExample(@Param("record") Sessions record, @Param("example") SessionsExample example);

    int updateByPrimaryKeySelective(Sessions record);

    int updateByPrimaryKeyWithBLOBs(Sessions record);
}