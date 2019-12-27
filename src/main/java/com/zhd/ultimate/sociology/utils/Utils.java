package com.zhd.ultimate.sociology.utils;

import java.util.UUID;


public class Utils {

    /**
     * 生成UUID
     *
     * @return
     */
    public static String uuid() {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }

    public static String random8() {
        String[] split = UUID.randomUUID().toString().split("-");
        return split[split.length - 2] + split[split.length - 1];
    }
}
