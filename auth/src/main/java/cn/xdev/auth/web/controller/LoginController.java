package cn.xdev.auth.web.controller;

import cn.xdev.auth.authorization.service.AuthorizationService;
import cn.xdev.auth.resource.model.SysResource;
import cn.xdev.auth.resource.service.ResourceService;
import cn.xdev.auth.security.enums.LoginType;
import cn.xdev.auth.security.token.CustomizedToken;
import cn.xdev.auth.user.model.SysUser;
import cn.xdev.auth.user.service.UserService;
import cn.xdev.auth.utils.Constants;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;
import java.util.Set;

/**
 * Created by felix on 2015-12-29-0029.
 */
@Controller
public class LoginController {

    @Resource
    private UserService userService;

    @Resource
    private ResourceService resourceService;

    @Resource
    private AuthorizationService authorizationService;

    private static final String USER_LOGIN_TYPE = LoginType.ADMIN.toString();

    /**
     * 用户登录
     *
     * @param user
     * @param result
     * @return
     */
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(@Valid SysUser user, BindingResult result, Model model, HttpServletRequest request) {

        String rememberMe = ServletRequestUtils.getStringParameter(request, "rememberMe", "");

        Subject subject = SecurityUtils.getSubject();
        // 已登陆则 跳到首页
        String backUrl = (String) subject.getSession().getAttribute("authc.backUrl");
        if (StringUtils.isEmpty(backUrl)) {
            backUrl = "/";
        }

        try {
            // 已登陆则 跳到首页
            if (subject.isAuthenticated()) {
                return backUrl;
            }
            if (result.hasErrors()) {
                model.addAttribute("error", "参数错误！");
                return "login";
            }

            boolean remember = false;
            if (rememberMe.equals("true")) {
                remember = true;
            }

            // 身份验证
            CustomizedToken customizedToken = new CustomizedToken(user.getUsername(), user.getPassword(), USER_LOGIN_TYPE);
            customizedToken.setRememberMe(remember);
            subject.login(customizedToken);
            // 验证成功在Session中保存用户信息
            final SysUser authUserInfo = userService.selectByUsername(user.getUsername());
            request.getSession().setAttribute("userInfo", JSONObject.parseObject(JSONObject.toJSONString(authUserInfo)));
            request.getSession().setAttribute("CKFinder_UserRole", "admin");

            Set<String> permissions = authorizationService.findPermissions(Constants.SERVER_APP_KEY, authUserInfo.getUsername());
            List<SysResource> menus = resourceService.findMenus(permissions);
            for (int i = 0; i < menus.size(); i++) {
                menus.get(i).setAvailable(resourceService.hasChildren(menus.get(i).getId()));
            }
            request.getSession().setAttribute("menus", JSONArray.parseArray(JSONArray.toJSONString(menus)));

        } catch (AuthenticationException e) {
            // 身份验证失败
            model.addAttribute("error", "用户名或密码错误 ！");
            return "login";
        }
        return "redirect:" + backUrl;
    }
}
