package com.hxx.sys.bean;

import lombok.Data;

import java.util.Date;
import java.time.LocalDate;

/*
系统用户的实体对象
 */
@Data
public class SysUser {
    private Integer uid;

    private Integer roleId;

    private String usename;

    private String password;

    private String grade;

    private String phone;

    private String email;

    private double total;

    private Date createTime;

}
