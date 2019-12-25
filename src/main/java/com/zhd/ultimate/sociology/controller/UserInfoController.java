package com.zhd.ultimate.sociology.controller;

import com.zhd.ultimate.sociology.entity.UserInfo;
import com.zhd.ultimate.sociology.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: zhanghaodong
 * @description
 * @date: 2019-12-25 13:02
 */
@RestController
@RequestMapping(path = "/user")
public class UserInfoController {

    @Autowired
    private UserInfoService UserInfoService;

    @RequestMapping(path = "/queryUser")
    public UserInfo queryUser(@RequestParam("userName") String userName) {
        return UserInfoService.queryUser(userName);
    }
}
