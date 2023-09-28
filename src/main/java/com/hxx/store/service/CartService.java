package com.hxx.store.service;

import com.hxx.store.bean.Cart;
import com.hxx.sys.bean.SysRole;
import com.hxx.sys.utils.PageUtils;

import java.util.List;

public interface CartService  {


    public List<Cart> list(Cart entity);

    public void ListPage(PageUtils pageUtils,SysRole user);

    public int count(PageUtils pageUtils,SysRole user);

    public int save(Cart entity);

    public Cart findById(int id);

    public Cart findByName(String name);

    public int delete();

    public int deleteById(int id);

    public int deleteByName(String Name);


    Cart updateAmount(Integer id, String amount);

    void saveStateByName(String name);

    void saveStateById(int id);

    void saveAmountByName(Integer amount,String name,String username);

    Cart findNotPayByName(String name,String username);


    void saveUserName(String userName, String name);
}
