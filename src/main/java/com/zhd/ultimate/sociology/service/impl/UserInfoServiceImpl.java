package com.zhd.ultimate.sociology.service.impl;

import com.zhd.ultimate.sociology.entity.UserInfo;
import com.zhd.ultimate.sociology.mapping.UserInfoMapper;
import com.zhd.ultimate.sociology.service.UserInfoService;
import com.zhd.ultimate.sociology.utils.MD5Utils;
import com.zhd.ultimate.sociology.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Date;
import java.util.List;


@Service
public class UserInfoServiceImpl implements UserInfoService {

    @Value("${file.upload-dir}")
    private String uploadDir;

    @Autowired
    private UserInfoMapper userInfoMapper;

    @Override
    public List<UserInfo> queryAllUserInfo() {
        return userInfoMapper.queryAllUserInfo();
    }

    @Override
    public int add(UserInfo userInfo) {
        userInfo.setGuid(Utils.uuid());
        userInfo.setCreateTime(new Date());
        userInfo.setUpdateTime(userInfo.getCreateTime());
        userInfo.setSalt(Utils.random8());
        String passWord = userInfo.getPassWord();
        String passTemp = MD5Utils.encode(MD5Utils.encode(passWord) + userInfo.getSalt());
        userInfo.setPassWord(passTemp);
        return userInfoMapper.insert(userInfo);
    }

    @Override
    public int update(UserInfo userInfo) {
        userInfo.setUpdateTime(new Date());
        return userInfoMapper.updateByPrimaryKey(userInfo);
    }

    @Override
    public UserInfo queryUserInfo(String guid) {
        return userInfoMapper.selectByPrimaryKey(guid);
    }

    @Override
    public int delete(String guid) {
        return userInfoMapper.deleteUser(guid);
    }

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

    @Override
    public void saveHeadImg() {

    }
}
