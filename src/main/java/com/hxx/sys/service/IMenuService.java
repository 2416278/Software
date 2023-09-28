package com.hxx.sys.service;

import com.hxx.sys.bean.SysMenu;
import com.hxx.sys.utils.PageUtils;

import java.util.List;

public interface IMenuService {

    public List<SysMenu> list(SysMenu entity);

    public int save(SysMenu entity);

    public SysMenu findById(int id);

    public int update(SysMenu entity);

    public int deleteById(int id);

    public void ListPage(PageUtils pageUtils);

    public int count(PageUtils pageUtils);

    public List<SysMenu>getAllParent();

    public boolean isDispatcher(int id);

    public  boolean haveSubMenu(int id);

    List<SysMenu> findByRoleId(Integer roleId);
}
