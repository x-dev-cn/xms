package cn.xdev.auth.app.controller;

import cn.xdev.auth.app.model.SysApp;
import cn.xdev.auth.app.service.AppService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.annotation.Resource;
import java.util.UUID;

/**
 * Created by felix on 2015-12-29-0029.
 */
@Controller
@RequestMapping("/app")
public class AppController {

    @Resource
    private AppService appService;

    @RequiresPermissions("sys:app:view")
    @RequestMapping(method = RequestMethod.GET)
    public String list(Model model) {
        Subject subject = SecurityUtils.getSubject();
        model.addAttribute("appList", appService.findAll());
        return "/app/list";
    }

    @RequiresPermissions("sys:app:create")
    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public String showCreateForm(Model model) {
        SysApp app = new SysApp();
        app.setApp_key(UUID.randomUUID().toString());
        app.setApp_secret(UUID.randomUUID().toString());
        model.addAttribute("app", app);
        model.addAttribute("op", "新增");
        return "/app/edit";
    }

    @RequiresPermissions("sys:app:create")
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String create(SysApp app, RedirectAttributes redirectAttributes) {
        appService.createApp(app);
        redirectAttributes.addFlashAttribute("msg", "新增成功");
        return "redirect:/app";
    }

    @RequiresPermissions("sys:app:update")
    @RequestMapping(value = "/{id}/update", method = RequestMethod.GET)
    public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
        model.addAttribute("app", appService.findOne(id));
        model.addAttribute("op", "修改");
        return "/app/edit";
    }

    @RequiresPermissions("sys:app:update")
    @RequestMapping(value = "/{id}/update", method = RequestMethod.POST)
    public String update(SysApp app, RedirectAttributes redirectAttributes) {
        appService.updateApp(app);
        redirectAttributes.addFlashAttribute("msg", "修改成功");
        return "redirect:/app";
    }

    @RequiresPermissions("sys:app:delete")
    @RequestMapping(value = "/{id}/delete", method = RequestMethod.GET)
    public String showDeleteForm(@PathVariable("id") Integer id, Model model) {
        model.addAttribute("app", appService.findOne(id));
        model.addAttribute("op", "删除");
        return "/app/edit";
    }

    @RequiresPermissions("sys:app:delete")
    @RequestMapping(value = "/{id}/delete", method = RequestMethod.POST)
    public String delete(@PathVariable("id") Integer id, RedirectAttributes redirectAttributes) {
        appService.deleteApp(id);
        redirectAttributes.addFlashAttribute("msg", "删除成功");
        return "redirect:/app";
    }
}
