package com.zhd.ultimate.sociology.entity.enums;


public enum StuSexEnum {
    MAN(0, "男"),
    WOMAN(1, "女");


    public int code;
    public String msg;

    StuSexEnum(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public String getMsg(int code) {
        StuSexEnum[] values = values();
        for (StuSexEnum stuSexEnum : values) {
            if (stuSexEnum.code == code) {
                return stuSexEnum.msg;
            }
        }
        return "";
    }

}
