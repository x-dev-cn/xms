package cn.xdev.auth.organization.controller;

import cn.xdev.auth.organization.model.SysOrganization;
import cn.xdev.auth.organization.service.OrganizationService;
import com.alibaba.fastjson.JSONObject;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/organization")
public class OrganizationController {

    @Autowired
    private OrganizationService organizationService;

    @RequiresPermissions("sys:organization:view")
    @RequestMapping(method = RequestMethod.GET)
    public String index(Model model) {
        return "/organization/index";
    }

    @RequiresPermissions("sys:organization:view")
    @RequestMapping(value = "/tree", method = RequestMethod.GET)
    @ResponseBody
    public String showTree(Model model) {
        return JSONObject.toJSONString(organizationService.findAll());
    }

    @RequiresPermissions("sys:organization:create")
    @RequestMapping(value = "/{parent_id}/appendChild", method = RequestMethod.POST)
    @ResponseBody
    public String create(SysOrganization organization) {
        organizationService.createOrganization(organization);
        Map<String, Object> result = new HashMap<>();
        result.put("result", "success");
        result.put("id", organization.getId());
        return JSONObject.toJSONString(result);
    }

    @RequiresPermissions("sys:organization:update")
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    @ResponseBody
    public String update(SysOrganization organization) {
        organizationService.updateOrganization(organization);
        Map<String, Object> result = new HashMap<>();
        result.put("result", "success");
        result.put("id", organization.getId());
        return JSONObject.toJSONString(result);
    }

    @RequiresPermissions("sys:organization:delete")
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public String delete(@PathVariable("id") Integer id) {
        organizationService.deleteOrganization(id);
        Map<String, Object> result = new HashMap<>();
        result.put("result", "success");
        return JSONObject.toJSONString(result);
    }

    @RequiresPermissions("sys:organization:update")
    @RequestMapping(value = "/{source_id}/move", method = RequestMethod.POST)
    @ResponseBody
    public String move(
            @PathVariable("source_id") Integer source_id,
            @RequestParam("target_id") Integer target_id,
            @RequestParam("position") Integer position) {
        organizationService.updateMove(source_id, target_id, position);
        Map<String, Object> result = new HashMap<>();
        result.put("result", "success");
        return JSONObject.toJSONString(result);
    }

}
