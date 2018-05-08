package cn.xdev.auth.resource.controller;

import cn.xdev.auth.resource.model.SysResource;
import cn.xdev.auth.resource.service.ResourceService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by felix on 2015-12-30-0030.
 */
@Controller
@RequestMapping("/resource")
public class ResourceController {

    @Resource
    private ResourceService resourceService;

    @ModelAttribute("types")
    public String[] resourceTypes() {
        String[] result = {"menu", "button", "menu2"};
        return result;
    }

    @RequiresPermissions("sys:resource:view")
    @RequestMapping(method = RequestMethod.GET)
    public String list(Model model) {
        List<SysResource> resourceList = resourceService.findAll();
        List<SysResource> menuList = new ArrayList<>();
        List<SysResource> buttonList = new ArrayList<>();
        for (SysResource resource : resourceList) {
            if (resource.getType().equals("menu")) {
                menuList.add(resource);
            } else {
                buttonList.add(resource);
            }
        }
        model.addAttribute("resourceList", resourceList);
        model.addAttribute("menuList", menuList);
        model.addAttribute("buttonList", buttonList);
        return "/resource/list";
    }

    @RequiresPermissions("sys:resource:create")
    @RequestMapping(value = "/{parentId}/appendChild", method = RequestMethod.GET)
    public String showAppendChildForm(@PathVariable("parentId") Integer parentId, Model model) {
        SysResource parent = resourceService.findOne(parentId);
        model.addAttribute("parent", parent);
        SysResource child = new SysResource();
        child.setParent_id(parentId);
        child.setParent_ids(parent.makeSelfAsParentIds());
        model.addAttribute("resource", child);
        model.addAttribute("op", "新增子节点");
        return "/resource/edit";
    }

    @RequiresPermissions("sys:resource:create")
    @RequestMapping(value = "/{parentId}/appendChild", method = RequestMethod.POST)
    public String create(SysResource resource, RedirectAttributes redirectAttributes) {
        resourceService.createResource(resource);
        redirectAttributes.addFlashAttribute("msg", "新增子节点成功");
        return "redirect:/resource";
    }

    @RequiresPermissions("sys:resource:update")
    @RequestMapping(value = "/{id}/update", method = RequestMethod.GET)
    public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
        model.addAttribute("resource", resourceService.findOne(id));
        model.addAttribute("op", "修改");
        return "/resource/edit";
    }

    @RequiresPermissions("sys:resource:update")
    @RequestMapping(value = "/{id}/update", method = RequestMethod.POST)
    public String update(SysResource resource, RedirectAttributes redirectAttributes) {
        resourceService.updateResource(resource);
        redirectAttributes.addFlashAttribute("msg", "修改成功");
        return "redirect:/resource";
    }

    @RequiresPermissions("sys:resource:delete")
    @RequestMapping(value = "/{id}/delete", method = RequestMethod.GET)
    public String delete(@PathVariable("id") Integer id, RedirectAttributes redirectAttributes) {
        resourceService.deleteResource(id);
        redirectAttributes.addFlashAttribute("msg", "删除成功");
        return "redirect:/resource";
    }

}
