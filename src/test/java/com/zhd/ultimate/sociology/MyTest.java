package com.zhd.ultimate.sociology;

import com.zhd.ultimate.sociology.utils.MD5Utils;
import com.zhd.ultimate.sociology.utils.Utils;

/**
 * @author: zhanghaodong
 * @description
 * @date: 2019-12-25 20:15
 */
public class MyTest {

    public static void main(String[] args) {
        String temp = MD5Utils.MD5Encode("123", "utf-8");
        String random8 = Utils.random8();
        System.out.println(random8);
        String temp2 = MD5Utils.MD5Encode(temp + random8, "utf-8");
        System.out.println(temp2);
    }
}
