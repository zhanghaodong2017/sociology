package com.zhd.ultimate.sociology.mapping;

import com.zhd.ultimate.sociology.entity.UserInfo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserInfoMapper {

    int insert(UserInfo record);

    UserInfo selectByPrimaryKey(String guid);

    UserInfo queryUserByName(@Param("userName") String userName);

    int updateByPrimaryKey(UserInfo record);
}