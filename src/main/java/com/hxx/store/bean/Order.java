package com.hxx.store.bean;

import java.util.Date;

import com.hxx.sys.bean.BaseClass;
import lombok.Data;

@Data
public class Order extends BaseClass {


    private String goodsName;

    private String img;

    private Integer amount;

    private Double total;

    private Date time;

    private String phone;

    private String address;
}
