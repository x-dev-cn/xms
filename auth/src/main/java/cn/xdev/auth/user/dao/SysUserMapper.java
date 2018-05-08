package cn.xdev.auth.user.dao;

import cn.xdev.auth.user.model.SysUser;
import cn.xdev.auth.user.model.SysUserExample;
import cn.xdev.core.generic.GenericDao;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SysUserMapper extends GenericDao<SysUser, Integer> {
    int countByExample(SysUserExample example);

    int deleteByExample(SysUserExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(SysUser record);

    int insertSelective(SysUser record);

    List<SysUser> selectByExample(SysUserExample example);

    List<SysUser> selectByExampleNoPwd(SysUserExample example);

    SysUser selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") SysUser record, @Param("example") SysUserExample example);

    int updateByExample(@Param("record") SysUser record, @Param("example") SysUserExample example);

    int updateByPrimaryKeySelective(SysUser record);

    int updateByPrimaryKey(SysUser record);

    /**
     * 用户登录验证查询
     *
     * @param record
     * @return
     */
    SysUser authentication(@Param("record") SysUser record);
}