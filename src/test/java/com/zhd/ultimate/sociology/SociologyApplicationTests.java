package com.zhd.ultimate.sociology;

import com.alibaba.fastjson.JSON;
import com.zhd.ultimate.sociology.entity.UserInfo;
import com.zhd.ultimate.sociology.service.UserInfoService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = SociologyApplication.class)
class SociologyApplicationTests {

    @Autowired
    private UserInfoService userInfoService;

    @Test
    void queryUser() {
        UserInfo userInfo = userInfoService.queryUser("zhangsan");
        System.out.println(JSON.toJSONString(userInfo));
    }

}
