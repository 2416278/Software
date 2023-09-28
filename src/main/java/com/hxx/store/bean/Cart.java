package com.hxx.store.bean;

import com.hxx.sys.bean.BaseClass;
import lombok.Data;

@Data
public class Cart extends BaseClass {

    private String img;

    private Double price;

    private Integer amount;

    private Integer isPay;

    private String username;


}
