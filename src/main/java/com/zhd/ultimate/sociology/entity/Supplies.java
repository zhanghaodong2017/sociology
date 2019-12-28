package com.zhd.ultimate.sociology.entity;

import lombok.Data;

import java.util.Date;

@Data
public class Supplies {
    private String guid;

    private String name;

    private Integer type;

    private Integer stock;

    private String remarks;

    private Date createTime;

    private Date updateTime;

}