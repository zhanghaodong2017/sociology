package com.zhd.ultimate.sociology.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author: zhanghaodong
 * @description
 * @date: 2019-12-25 16:30
 */
@Controller
public class MainController {

    @RequestMapping("/")
    public String index() {
        return "index";
    }

    @RequestMapping("/showLogin")
    public String showLogin() {
        return "login";
    }
}
