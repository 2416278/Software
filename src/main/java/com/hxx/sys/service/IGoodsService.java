package com.hxx.sys.service;

import com.hxx.sys.bean.SysGoods;
import com.hxx.sys.bean.SysMenu;
import com.hxx.sys.utils.PageUtils;

import java.util.List;

public interface IGoodsService  {


    public List<SysGoods> list(SysGoods entity);

    public void ListPage(PageUtils pageUtils);

    public int count(PageUtils pageUtils);

    public int save(SysGoods entity);

    public SysGoods findById(int id);

    public int update(SysGoods entity);

    public int deleteById(int id);

    SysGoods findByName(String name);

    List<SysGoods> listByTypeId(SysGoods good);

    void updateAmountByName(Integer amount, String name);

    void updateNew(SysGoods good);
}
