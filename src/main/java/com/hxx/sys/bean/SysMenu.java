package com.hxx.sys.bean;

import lombok.Data;
import java.util.Date;

@Data
public class SysMenu extends BaseClass {

    private String url;

    private Integer parentId;

    private int seq;

    private Date createTime;

    private boolean check=false;

}
