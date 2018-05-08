package cn.xdev.auth.user.service.impl;

import cn.xdev.auth.authorization.service.AuthorizationService;
import cn.xdev.auth.user.dao.SysUserMapper;
import cn.xdev.auth.user.model.SysUser;
import cn.xdev.auth.user.model.SysUserExample;
import cn.xdev.auth.user.service.UserService;
import cn.xdev.core.generic.GenericDao;
import cn.xdev.core.generic.GenericServiceImpl;
import cn.xdev.core.util.GeneralHelper;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 用户Service实现类
 *
 * @author Felix.Hsu
 * @since 2015-08-18 16:17
 */
@Service
public class UserServiceImpl extends GenericServiceImpl<SysUser, Integer> implements UserService {

    @Resource
    private SysUserMapper sysUserMapper;

    @Resource
    private AuthorizationService authorizationService;

    @Override
    public SysUser authentication(SysUser user) {
        SysUser sysUser = selectByUsername(user.getUsername());
        if (sysUser != null) {
            user.setPassword(new Md5Hash(user.getPassword(), sysUser.getCredentialsSalt(), 2).toString());
            return sysUserMapper.authentication(user);
        }
        return null;
    }

    @Override
    public SysUser selectByUsername(String username) {
        SysUserExample example = new SysUserExample();
        example.createCriteria().andUsernameEqualTo(username);
        final List<SysUser> list = sysUserMapper.selectByExample(example);
        if (list.size() > 0)
            return list.get(0);
        else
            return new SysUser();
    }

    @Override
    public SysUser createUser(SysUser user) {
        sysUserMapper.insertSelective(user);
        return user;
    }

    @Override
    public SysUser updateUser(SysUser user) {
        sysUserMapper.updateByPrimaryKeySelective(user);
        return user;
    }

    @Override
    public void deleteUser(Integer userId) {
        sysUserMapper.deleteByPrimaryKey(userId);
    }

    @Override
    public void changePassword(Integer userId, String newPassword) {
        SysUser user = sysUserMapper.selectByPrimaryKey(userId);
        user.setPassword(new Md5Hash(newPassword, user.getCredentialsSalt(), 2).toString());
        sysUserMapper.updateByPrimaryKeySelective(user);
    }

    @Override
    public SysUser findOne(Integer userid) {
        SysUserExample example = new SysUserExample();
        example.createCriteria().andIdEqualTo(userid);
        final List<SysUser> list = sysUserMapper.selectByExample(example);
        if (list.size() > 0)
            return list.get(0);
        else
            return new SysUser();
    }

    @Override
    public List<SysUser> findAll() {
        return sysUserMapper.selectByExample(new SysUserExample());
    }

    @Override
    public List<SysUser> selectLikeUsername(String username) {
        SysUserExample example = new SysUserExample();
        example.createCriteria().andUsernameLike("%" + username + "%");
        if (GeneralHelper.isStrNumeric(username)) {
            example.or(example.createCriteria().andIdEqualTo(Integer.valueOf(username)));
        }
        final List<SysUser> list = sysUserMapper.selectByExampleNoPwd(example);
        return list;
    }

    @Override
    public GenericDao<SysUser, Integer> getDao() {
        return sysUserMapper;
    }
}
