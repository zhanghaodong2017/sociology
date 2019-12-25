package com.zhd.ultimate.sociology.mapping;

import com.zhd.ultimate.sociology.entity.UserInfo;
import org.springframework.stereotype.Repository;

@Repository
public interface UserInfoMapper {

    int deleteByPrimaryKey(String guid);

    int insert(UserInfo record);

    int insertSelective(UserInfo record);

    UserInfo selectByPrimaryKey(String guid);

    int updateByPrimaryKeySelective(UserInfo record);

    int updateByPrimaryKey(UserInfo record);
}