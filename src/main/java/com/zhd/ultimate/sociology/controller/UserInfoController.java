package com.zhd.ultimate.sociology.controller;

import com.zhd.ultimate.sociology.entity.UserInfo;
import com.zhd.ultimate.sociology.service.UserInfoService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @author: zhanghaodong
 * @description
 * @date: 2019-12-25 13:02
 */
@RestController
@RequestMapping(path = "/user")
public class UserInfoController {

    @Autowired
    private UserInfoService userInfoService;

    @RequestMapping(path = "/queryUser")
    public UserInfo queryUser(@RequestParam("userName") String userName) {
        return userInfoService.queryUser(userName);
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
        request.setAttribute("userName", userInfo.getUserName());
        return true;
    }

}
