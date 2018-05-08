package cn.xdev.auth.security.realm;

import cn.xdev.auth.security.token.CustomizedToken;
import cn.xdev.auth.vip.model.SysVip;
import cn.xdev.auth.vip.service.SysVipService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;

import javax.annotation.Resource;

/**
 * Created by felix on 2017-06-12-0012.
 */
public class UserRealm extends AuthorizingRealm {

    @Resource
    private SysVipService sysVipService;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
//        Collection collection = principalCollection.fromRealm(getName());
//        if (collection.size() == 1) {
//            SysVip vip = (SysVip) principalCollection.getPrimaryPrincipal();
//            authorizationInfo.setRoles(sysVipService.selectRoles(vip.getId()));
//        }

        return authorizationInfo;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        // 通过数据库进行验证
        CustomizedToken customizedToken = (CustomizedToken) authenticationToken;
        String mobile = String.valueOf(customizedToken.getPrincipal());
        final SysVip vip = sysVipService.selectByMobile(mobile);
        if (vip == null) {
            throw new UnknownAccountException();//没找到帐号
        }

        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(
                vip, //用户名
                vip.getPassword(), //密码
                ByteSource.Util.bytes(vip.getCredentialsSalt()),//salt=username+salt
                getName()  //realm name
        );
        return authenticationInfo;
    }

    @Override
    public void clearCachedAuthorizationInfo(PrincipalCollection principals) {
        super.clearCachedAuthorizationInfo(principals);
    }

    @Override
    public void clearCachedAuthenticationInfo(PrincipalCollection principals) {
        super.clearCachedAuthenticationInfo(principals);
    }

    @Override
    public void clearCache(PrincipalCollection principals) {
        super.clearCache(principals);
    }

    public void clearAllCachedAuthorizationInfo() {
        getAuthorizationCache().clear();
    }

    public void clearAllCachedAuthenticationInfo() {
        getAuthenticationCache().clear();
    }

    public void clearAllCache() {
        clearAllCachedAuthenticationInfo();
        clearAllCachedAuthorizationInfo();
    }
}
