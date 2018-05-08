package cn.xdev.auth.vip.service;

import cn.xdev.auth.vip.model.SysVip;
import cn.xdev.auth.vo.datatables.DTParameter;
import cn.xdev.auth.vo.datatables.DTResult;
import cn.xdev.core.generic.GenericService;

import java.util.List;

/**
 * @Author Felix.Hsu (felix@x-dev.cn)
 * @Date 2017/9/1910:18
 */
public interface SysVipService extends GenericService<SysVip, Integer> {

    /**
     * 用户验证
     *
     * @param user
     * @return
     */
    SysVip authentication(SysVip user);

    /**
     * 根据用户名查询用户
     *
     * @param mobile
     * @return
     */
    SysVip selectByMobile(String mobile);

    /**
     * 创建用户
     *
     * @param vip
     */
    SysVip createUser(SysVip vip);

    SysVip updateUser(SysVip vip);

    void deleteUser(Integer vipId);

    /**
     * 修改密码
     *
     * @param userId
     * @param newPassword
     *
     * @return
     */
    int changePassword(Integer userId, String oldPassword, String newPassword);

    SysVip findOne(Integer userId);

    List<SysVip> findAll();

    int updateByDisable(Integer id);

    int updateByEnable(Integer id);

    DTResult selectListByDataTables(DTParameter parameter, String mobile, boolean locked);
}
