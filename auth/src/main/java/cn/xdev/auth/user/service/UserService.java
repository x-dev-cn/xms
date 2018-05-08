package cn.xdev.auth.user.service;


import cn.xdev.auth.user.model.SysUser;
import cn.xdev.core.generic.GenericService;

import java.util.List;

/**
 * 用户 业务 接口
 *
 * @Viewauthor Felix.Hsu
 * @since 2015-08-18 16:17
 **/
public interface UserService extends GenericService<SysUser, Integer> {

    /**
     * 用户验证
     *
     * @param user
     * @return
     */
    SysUser authentication(SysUser user);

    /**
     * 根据用户名查询用户
     *
     * @param username
     * @return
     */
    SysUser selectByUsername(String username);

    /**
     * 创建用户
     *
     * @param user
     */
    SysUser createUser(SysUser user);

    SysUser updateUser(SysUser user);

    void deleteUser(Integer userId);

    /**
     * 修改密码
     *
     * @param userId
     * @param newPassword
     */
    void changePassword(Integer userId, String newPassword);

    SysUser findOne(Integer userId);

    List<SysUser> findAll();

    List<SysUser> selectLikeUsername(String username);

}
