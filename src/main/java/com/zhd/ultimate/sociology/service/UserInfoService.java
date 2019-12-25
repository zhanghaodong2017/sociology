package com.zhd.ultimate.sociology.service;

import com.zhd.ultimate.sociology.entity.UserInfo;

/**
 * @author: zhanghaodong
 * @description
 * @date: 2019-12-25 13:03
 */
public interface UserInfoService {
    UserInfo queryUser(String userName);
}
