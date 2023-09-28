package com.hxx.store.dao;


import com.hxx.store.bean.Cart;
import com.hxx.sys.bean.SysGoods;

import com.hxx.sys.bean.SysRole;
import com.hxx.sys.utils.PageUtils;

import java.util.List;

public interface CartDao {

    /*接口方法*/
    public List<Cart> List(Cart entity);//查询显示所有的SysUser

    public List<Cart>ListPage(PageUtils pageUtils,SysRole user);//分页查询

    public int save(Cart entity);//接受添加的SysUser

    public Cart findById(int id);

    public int delete();

    int count(PageUtils pageUtils,SysRole user);

    int deleteByName(String name);

    int deleteById(int id);

    void updateAmount(Integer id, String amount);

    Cart findByName(String name);

    void saveStateByName(String name);

    void saveAmountByName(Integer amount, String name,String username);

    void saveUserName(String userName, String name);

    Cart findNotPayByName(String name,String username);

    void saveStateById(int id);
}