package cn.xdev.auth.web.controller;

import cn.xdev.auth.bind.annotation.CurrentUser;
import cn.xdev.auth.user.model.SysUser;
import cn.xdev.auth.user.service.UserService;
import com.alibaba.fastjson.JSONObject;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * 视图控制器,返回jsp视图给前端
 *
 * @author Felix.Hsu
 * @since 2015-08-18 16:17
 **/
@Controller
public class PageController {

    @Resource
    private UserService userService;

    /**
     * 登录页
     */
    @RequestMapping("/login")
    public String login(HttpServletRequest request, Model model) {
        Subject subject = SecurityUtils.getSubject();
        // 已登陆则 跳到首页
        String backUrl = (String) subject.getSession().getAttribute("authc.backUrl");
        if (StringUtils.isEmpty(backUrl)) {
            backUrl = "/";//.getSuccessUrl();
        }
        if (subject.isAuthenticated()) {
            return "redirect:" + backUrl;
        }

        if (subject.isRemembered()) {
            String userName = subject.getPrincipal().toString();
            final SysUser authUserInfo =  userService.selectByUsername(userName);
            request.getSession().setAttribute("userInfo", JSONObject.parseObject(JSONObject.toJSONString(authUserInfo)));
//            return "redirect:" + backUrl;
        }

        String exceptionClassName = (String) request.getAttribute("shiroLoginFailure");
        String error = null;
        if (UnknownAccountException.class.getName().equals(exceptionClassName)) {
            error = "用户名/密码错误";
        } else if (IncorrectCredentialsException.class.getName().equals(exceptionClassName)) {
            error = "用户名/密码错误";
        } else if (exceptionClassName != null) {
            error = "其他错误：" + exceptionClassName;
        }
        model.addAttribute("error", error);
        return "/login";
    }

    /**
     * 首页
     *
     * @param session
     * @param model
     * @return
     */
    @RequestMapping("/")
    public String index(HttpSession session, Model model) {

        return "/index";
    }

    @RequestMapping(value = "/profile", method = RequestMethod.GET)
    public String profile(HttpSession session, Model model) {
        return "profile";
    }

    @RequestMapping(value = "/profile", method = RequestMethod.POST)
    public String changePWD(HttpServletRequest request, Model model) {
        String oldPwd = ServletRequestUtils.getStringParameter(request, "password", "");
        String newPwd = ServletRequestUtils.getStringParameter(request, "newPassword", "");

        Subject subject = SecurityUtils.getSubject();
        SysUser user = new SysUser();
        user.setUsername(subject.getPrincipal().toString());
        user.setPassword(oldPwd);
        user = userService.authentication(user);
        if (user != null) {
            userService.changePassword(user.getId(), newPwd);
            model.addAttribute("msg", "密码修改成功");
        } else {
            model.addAttribute("msg", "原密码错误");
        }
        return "/profile";
    }

    /**
     * 404页
     */
    @RequestMapping("/404")
    public String error404() {
        return "404";
    }

    /**
     * 401页
     */
    @RequestMapping("/401")
    public String error401() {
        return "401";
    }

    /**
     * 500页
     */
    @RequestMapping("/500")
    public String error500() {
        return "500";
    }

}