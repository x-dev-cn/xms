package cn.xdev.xms.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Felix.Hsu (felix@x-dev.cn)
 * @date 2018/5/8 11:13
 */
@Controller
public class DemoController {

    @RequestMapping("/")
    public String index() {
        return "index";
    }
}
