package cn.xdev.auth.authorization.controller;

import cn.xdev.auth.app.service.AppService;
import cn.xdev.auth.authorization.model.SysUserAppRoles;
import cn.xdev.auth.authorization.service.AuthorizationService;
import cn.xdev.auth.role.service.RoleService;
import cn.xdev.auth.user.service.UserService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.annotation.Resource;

/**
 * Created by felix on 2015-12-30-0030.
 */
@Controller
@RequestMapping("/authorization")
public class AuthorizationController {

    @Resource
    private AuthorizationService authorizationService;

    @Resource
    private UserService userService;

    @Resource
    private AppService appService;

    @Resource
    private RoleService roleService;

    @RequiresPermissions("sys:authorization:view")
    @RequestMapping(method = RequestMethod.GET)
    public String list(Model model) {
        model.addAttribute("authorizationList", authorizationService.findAll());
        return "/authorization/list";
    }

    @RequiresPermissions("sys:authorization:create")
    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public String showCreateForm(Model model) {
        setCommonData(model);
        SysUserAppRoles authorization = new SysUserAppRoles();
        model.addAttribute("authorization", authorization);
        model.addAttribute("op", "新增");
        return "/authorization/edit";
    }

    @RequiresPermissions("sys:authorization:create")
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String create(SysUserAppRoles authorization, RedirectAttributes redirectAttributes) {
        authorizationService.createAuthorization(authorization);
        redirectAttributes.addFlashAttribute("msg", "新增成功");
        return "redirect:/authorization";
    }

    @RequiresPermissions("sys:authorization:update")
    @RequestMapping(value = "/{id}/update", method = RequestMethod.GET)
    public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
        setCommonData(model);
        model.addAttribute("authorization", authorizationService.findOne(id));
        model.addAttribute("op", "修改");
        return "/authorization/edit";
    }

    @RequiresPermissions("sys:authorization:update")
    @RequestMapping(value = "/{id}/update", method = RequestMethod.POST)
    public String update(SysUserAppRoles authorization, RedirectAttributes redirectAttributes) {
        authorizationService.updateAuthorization(authorization);
        redirectAttributes.addFlashAttribute("msg", "修改成功");
        return "redirect:/authorization";
    }

    @RequiresPermissions("sys:authorization:delete")
    @RequestMapping(value = "/{id}/delete", method = RequestMethod.GET)
    public String showDeleteForm(@PathVariable("id") Integer id, Model model) {
        setCommonData(model);
        model.addAttribute("authorization", authorizationService.findOne(id));
        model.addAttribute("op", "删除");
        return "/authorization/edit";
    }

    @RequiresPermissions("sys:authorization:delete")
    @RequestMapping(value = "/{id}/delete", method = RequestMethod.POST)
    public String delete(@PathVariable("id") Integer id, RedirectAttributes redirectAttributes) {
        authorizationService.deleteAuthorization(id);
        redirectAttributes.addFlashAttribute("msg", "删除成功");
        return "redirect:/authorization";
    }

    private void setCommonData(Model model) {
        model.addAttribute("userList", userService.findAll());
        model.addAttribute("roleList", roleService.findAll());
        model.addAttribute("appList", appService.findAll());
    }
}
