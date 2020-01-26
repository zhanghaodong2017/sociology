package com.zhd.ultimate.sociology.entity;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;

@Data
public class UserInfo {
    private String guid;

    private String userName;

    private String passWord;

    private String salt;

    private String realName;

    private String address;

    private Integer sex;

    private String headImg;

    private MultipartFile headImgFile;

    private String email;

    private Integer level;

    private Date createTime;

    private Date updateTime;

}