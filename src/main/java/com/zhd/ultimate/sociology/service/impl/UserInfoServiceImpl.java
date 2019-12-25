package com.zhd.ultimate.sociology.service.impl;

import com.zhd.ultimate.sociology.entity.UserInfo;
import com.zhd.ultimate.sociology.mapping.UserInfoMapper;
import com.zhd.ultimate.sociology.service.UserInfoService;
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
        return userInfoMapper.selectByPrimaryKey(userName);
    }
}
