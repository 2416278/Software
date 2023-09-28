package com.hxx.store.service.impl;

import com.hxx.store.bean.Order;

import com.hxx.store.dao.OrderDao;

import com.hxx.store.dao.impl.OrderDaoImpl;

import com.hxx.store.service.OrderService;
import com.hxx.sys.bean.SysRole;
import com.hxx.sys.utils.PageUtils;

import java.util.List;

public class OrderServiceImpl implements OrderService {
    private OrderDao dao=new OrderDaoImpl();

    @Override
    public List<Order> list(Order entity) {
        return dao.List(entity);
    }

    @Override
    public void ListPage(PageUtils pageUtils, SysRole user) {
        List<Order> orders = dao.ListPage(pageUtils,user);
        int count = dao.count(pageUtils,user);
        pageUtils.setList(orders);
        pageUtils.setTotalCount(count);


    }

    @Override
    public int count(PageUtils pageUtils,SysRole user) {
        return dao.count(pageUtils,user);
    }

    @Override
    public int save(Order entity) {
        return dao.save(entity);
    }

    @Override
    public int saveName(Order entity) {
        return dao.saveName(entity);
    }

    @Override
    public Order findById(int id) {
        return dao.findById(id);
    }

    @Override
    public Order findByName(String name) {
        return dao.findByName(name);
    }

    @Override
    public int delete() {
        return dao.delete();
    }


}
