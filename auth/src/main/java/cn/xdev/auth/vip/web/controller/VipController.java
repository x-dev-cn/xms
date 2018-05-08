package cn.xdev.auth.vip.web.controller;

import cn.xdev.auth.vip.service.SysVipService;
import cn.xdev.auth.vo.datatables.DTParameter;
import cn.xdev.auth.vo.datatables.DTResult;
import com.alibaba.fastjson.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping(value = "/vip")
public class VipController {

    @Resource
    private SysVipService vipService;

    @RequestMapping(method = RequestMethod.GET)
    public String listPage(Model model) {
        return "/vip/list";
    }

    @RequestMapping(method = RequestMethod.GET, headers = "table=true")
    @ResponseBody
    public JSONObject list(HttpServletRequest request) {
        String mobile = ServletRequestUtils.getStringParameter(request, "mobile", "");

        DTParameter dtParameter = new DTParameter(request);
        DTResult result = vipService.selectListByDataTables(dtParameter, mobile, false);

        return JSONObject.parseObject(JSONObject.toJSONString(result));
    }

    @RequestMapping(value = "/{vip_id}/disable", method = RequestMethod.POST)
    @ResponseBody
    public String disable(@PathVariable int vip_id) {
        int result = vipService.updateByDisable(vip_id);

        Map<String, Object> map = new HashMap<>();
        if (result == 1) {
            map.put("success", true);
        } else {
            map.put("success", false);
        }

        return JSONObject.toJSONString(map);
    }

    @RequestMapping(value = "/{vip_id}/enable", method = RequestMethod.POST)
    @ResponseBody
    public String enable(@PathVariable int vip_id) {
        int result = vipService.updateByEnable(vip_id);

        Map<String, Object> map = new HashMap<>();
        if (result == 1) {
            map.put("success", true);
        } else {
            map.put("success", false);
        }

        return JSONObject.toJSONString(map);
    }
}
