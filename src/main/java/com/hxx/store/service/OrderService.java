package com.hxx.store.service;

import com.hxx.store.bean.Order;
import com.hxx.sys.bean.SysRole;
import com.hxx.sys.utils.PageUtils;

import java.util.List;

public interface OrderService {


    public List<Order> list(Order entity);

    public void ListPage(PageUtils pageUtils, SysRole user);

    public int count(PageUtils pageUtils,SysRole user);

    public int save(Order entity);

    public int saveName(Order entity);

    public Order findById(int id);

    public Order findByName(String name);

    public int delete();


}
