package com.zhd.ultimate.sociology.service;

import com.zhd.ultimate.sociology.entity.UserInfo;

import java.util.List;


public interface UserInfoService {

    List<UserInfo> queryAllUserInfo();

    int add(UserInfo userInfo);

    int update(UserInfo userInfo);

    UserInfo queryUserInfo(String guid);

    int delete(String guid);

    UserInfo queryUser(String userName);

    UserInfo login(String userName, String passWord);

    void saveHeadImg();
}
