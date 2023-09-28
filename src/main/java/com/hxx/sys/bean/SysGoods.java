package com.hxx.sys.bean;

import lombok.Data;

import java.util.Date;

@Data
public class SysGoods extends BaseClass {

    private String make;
    private Date maketime;
    private String size;
    private Double buyprice;
    private Double sellprice;
    private Integer count;
    private String img;
    private Integer typeId;


}
