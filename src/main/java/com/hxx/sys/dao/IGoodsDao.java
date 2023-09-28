package com.hxx.sys.dao;

import com.hxx.sys.bean.SysGoods;

import com.hxx.sys.utils.PageUtils;

import java.util.List;

public interface IGoodsDao {

    /*接口方法*/
    public List<SysGoods> List(SysGoods entity);//查询显示所有的SysUser

    public List<SysGoods>ListPage(PageUtils pageUtils);//分页查询

    public int save(SysGoods entity);//接受添加的SysUser

    public SysGoods findById(int id);

    public int update(SysGoods entity);

    int deleteById(int id);

    int count(PageUtils pageUtils);

    SysGoods findByName(String name);

    List<SysGoods> listByTypeId(SysGoods good);

    void updateAmountByName(Integer amount, String name);

    void updateNew(SysGoods good);
}
