package com.hxx.store.dao;

import com.hxx.store.bean.Order;
import com.hxx.sys.bean.SysRole;
import com.hxx.sys.utils.PageUtils;

import java.util.List;

public interface OrderDao {

    /*接口方法*/
    public List<Order > List(Order  entity);//查询显示所有的SysUser

    public List<Order >ListPage(PageUtils pageUtils, SysRole user);//分页查询

    public int save(Order  entity);//接受添加的SysUser

    public int saveName(Order  entity);

    public Order  findById(int id);

    public int delete();

    int count(PageUtils pageUtils,SysRole user);

    int deleteByName(String name);

    int deleteById(int id);

    Order findByName(String name);
}