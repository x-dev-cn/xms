package cn.xdev.auth.vip.dao;

import cn.xdev.auth.vip.model.SysVip;
import cn.xdev.auth.vip.model.SysVipExample;
import cn.xdev.core.generic.GenericDao;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SysVipMapper extends GenericDao<SysVip, Integer> {
    int countByExample(SysVipExample example);

    int deleteByExample(SysVipExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(SysVip record);

    int insertSelective(SysVip record);

    List<SysVip> selectByExample(SysVipExample example);

    SysVip selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") SysVip record, @Param("example") SysVipExample example);

    int updateByExample(@Param("record") SysVip record, @Param("example") SysVipExample example);

    int updateByPrimaryKeySelective(SysVip record);

    int updateByPrimaryKey(SysVip record);

    /**
     * 用户登录验证查询
     *
     * @param record
     * @return
     */
    SysVip authentication(@Param("record") SysVip record);
}