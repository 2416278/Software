package com.hxx.sys.bean;

import lombok.Data;

import java.util.Date;
@Data
public class SysRole extends BaseClass{

    private String notes;

    private  String password;

    private Date createTime;

    private Date lateLoginErrorTime;

    private int loginErrorCount;

    private Boolean isAdmin;

    private int isLocked;

}
