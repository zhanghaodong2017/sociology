package com.zhd.ultimate.sociology.mybaits;

import com.alibaba.fastjson.JSON;
import com.zhd.ultimate.sociology.entity.UserInfo;
import com.zhd.ultimate.sociology.mapping.PersonMapper;

/**
 * @author: zhanghaodong
 * @description
 * @date: 2020-01-13 19:36
 */
public class BatisTest {


    public static void main(String[] args) throws Exception {
        doWork2();
    }

    private static void doWork2() throws Exception {
        MybtisFactory factory = MybtisFactory.newInstance();
        String fileName = "mybatis/mapper/PersonMapper.xml";

        factory.init(new String[]{fileName});

        PersonMapper personMapper = factory.getMapper(PersonMapper.class);

        UserInfo userInfo = personMapper.queryUserByGuid("scdsadcsvxvsdsdsd");

        System.out.println(JSON.toJSONString(userInfo));

    }

    public String getName() {
        return "BatisTest";
    }


}
