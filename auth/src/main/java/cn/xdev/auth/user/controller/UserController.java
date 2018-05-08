package cn.xdev.auth.user.controller;

import cn.xdev.auth.organization.model.SysOrganization;
import cn.xdev.auth.organization.service.OrganizationService;
import cn.xdev.auth.user.model.SysUser;
import cn.xdev.auth.user.service.UserService;
import cn.xdev.core.util.ApplicationUtils;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * 用户控制器
 *
 * @author Felix.Hsu
 * @since 2015-08-18 16:17
 **/
@Controller
@RequestMapping(value = "/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Resource
    private OrganizationService organizationService;

    @ModelAttribute
    public void setUserList(Model model) {
        List<SysOrganization> organizationList = organizationService.findAll();
        for (SysOrganization organization : organizationList) {
            if (organization.getId() == 1) {
                organizationList.remove(organization);
                break;
            }
        }
        model.addAttribute("orgList", organizationList);
    }

    @RequiresPermissions("sys:user:view")
    @RequestMapping(method = RequestMethod.GET)
    public String list(Model model) {
        model.addAttribute("userList", userService.findAll());
        return "/user/list";
    }

    @RequiresPermissions("sys:user:create")
    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public String showCreateForm(Model model) {
        model.addAttribute("user", new SysUser());
        model.addAttribute("op", "新增");
        return "/user/edit";
    }

    @RequiresPermissions("sys:user:create")
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String create(SysUser user, RedirectAttributes redirectAttributes) {
//        user.setCreate_date(DateUtil.format(new Date(), "yyyy-MM-dd HH:mm:ss"));
        user.setSalt(ApplicationUtils.randomUUID().replace("-", ""));
        user.setPassword(new Md5Hash(user.getPassword(), user.getCredentialsSalt(), 2).toString());
        user.setLocked(false);
        userService.createUser(user);
        redirectAttributes.addFlashAttribute("msg", "新增成功");
        return "redirect:/user";
    }

    @RequiresPermissions("sys:user:update")
    @RequestMapping(value = "/{id}/update", method = RequestMethod.GET)
    public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
        model.addAttribute("user", userService.findOne(id));
        model.addAttribute("op", "修改");
        return "/user/edit";
    }

    @RequiresPermissions("sys:user:update")
    @RequestMapping(value = "/{id}/update", method = RequestMethod.POST)
    public String update(SysUser user, RedirectAttributes redirectAttributes) {
        userService.updateUser(user);
        redirectAttributes.addFlashAttribute("msg", "修改成功");
        return "redirect:/user";
    }

    @RequiresPermissions("sys:user:delete")
    @RequestMapping(value = "/{id}/delete", method = RequestMethod.GET)
    public String showDeleteForm(@PathVariable("id") Integer id, Model model) {
        model.addAttribute("user", userService.findOne(id));
        model.addAttribute("op", "删除");
        return "/user/edit";
    }

    @RequiresPermissions("sys:user:delete")
    @RequestMapping(value = "/{id}/delete", method = RequestMethod.POST)
    public String delete(@PathVariable("id") Integer id, RedirectAttributes redirectAttributes) {
        userService.deleteUser(id);
        redirectAttributes.addFlashAttribute("msg", "删除成功");
        return "redirect:/user";
    }


    @RequiresPermissions("sys:user:update")
    @RequestMapping(value = "/{id}/changePassword", method = RequestMethod.GET)
    public String showChangePasswordForm(@PathVariable("id") Integer id, Model model) {
        model.addAttribute("user", userService.findOne(id));
        model.addAttribute("op", "修改密码");
        return "/user/changePassword";
    }

    @RequiresPermissions("sys:user:update")
    @RequestMapping(value = "/{id}/changePassword", method = RequestMethod.POST)
    public String changePassword(@PathVariable("id") Integer id, String newPassword, RedirectAttributes redirectAttributes) {
        userService.changePassword(id, newPassword);
        redirectAttributes.addFlashAttribute("msg", "修改密码成功");
        return "redirect:/user";
    }

    @RequestMapping(value = "/select")
    @ResponseBody
    public JSONArray doHumresSelect(HttpServletRequest request, Model model, HttpSession session) {
        String humresName = ServletRequestUtils.getStringParameter(request, "name", "");
//        try {
//            humresName = new String(humresName.getBytes("ISO-8859-1"), "UTF-8");
//        } catch (UnsupportedEncodingException e) {
//            e.printStackTrace();
//        }
        List<SysUser> list = userService.selectLikeUsername(humresName);
        return JSONArray.parseArray(JSONObject.toJSONString(list));
    }

    @RequestMapping(value = "/select/{id}")
    @ResponseBody
    public JSONObject doHumresSelectInit(@PathVariable int id, HttpServletRequest request, Model model, HttpSession session) {
        SysUser user = userService.selectById(id);
        return JSONObject.parseObject(JSONObject.toJSONString(user));
    }

}
