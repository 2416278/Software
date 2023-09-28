package com.hxx.sys.bean;

import lombok.Data;

import java.util.List;

@Data
public class SysGoodsType extends BaseClass{


    private String notes;

    private List<SysGoods> goods;//当前类型的商品列表
}
