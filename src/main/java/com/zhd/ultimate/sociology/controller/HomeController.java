package com.zhd.ultimate.sociology.controller;

import com.zhd.ultimate.sociology.entity.UserInfo;
import com.zhd.ultimate.sociology.service.UserInfoService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * @author: zhanghaodong
 * @description
 * @date: 2019-12-25 16:30
 */
@Controller
@RequestMapping("/home")
public class HomeController {

    @Autowired
    private UserInfoService userInfoService;

    @RequestMapping("/index")
    public String index() {
        return "index";
    }

    @RequestMapping("/showLogin")
    public String showLogin() {
        return "login";
    }

    @RequestMapping("/logout")
    public String logout(HttpServletRequest request) {
        request.getSession().removeAttribute("opUserName");
        return "login";
    }

    @RequestMapping(path = "/login")
    @ResponseBody
    public Boolean login(@RequestParam("userName") String userName, @RequestParam("passWord") String passWord, HttpServletRequest request) {
        if (StringUtils.isBlank(userName) || StringUtils.isBlank(passWord)) {
            return false;
        }
        UserInfo userInfo = userInfoService.login(userName, passWord);
        if (userInfo == null) {
            return false;
        }
        request.getSession().setAttribute("opUserName", userInfo.getUserName());
        return true;
    }
}
