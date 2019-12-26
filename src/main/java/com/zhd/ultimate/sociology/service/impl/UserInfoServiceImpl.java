package com.zhd.ultimate.sociology.service.impl;

import com.zhd.ultimate.sociology.entity.UserInfo;
import com.zhd.ultimate.sociology.mapping.UserInfoMapper;
import com.zhd.ultimate.sociology.service.UserInfoService;
import com.zhd.ultimate.sociology.utils.MD5Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author: zhanghaodong
 * @description
 * @date: 2019-12-25 13:04
 */
@Service
public class UserInfoServiceImpl implements UserInfoService {

    @Autowired
    private UserInfoMapper userInfoMapper;

    @Override
    public UserInfo queryUser(String userName) {
        return userInfoMapper.queryUserByName(userName);
    }

    @Override
    public UserInfo login(String userName, String passWord) {
        UserInfo userInfo = userInfoMapper.queryUserByName(userName);
        if (userInfo == null) {
            return null;
        }
        String passTemp = MD5Utils.encode(MD5Utils.encode(passWord) + userInfo.getSalt());
        if (passTemp.equals(userInfo.getPassWord())) {
            return userInfo;
        }
        return null;
    }
}
