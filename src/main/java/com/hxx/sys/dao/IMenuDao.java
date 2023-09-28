package com.hxx.sys.dao;

import com.hxx.sys.bean.SysMenu;
import com.hxx.sys.utils.PageUtils;

import java.util.List;

public interface IMenuDao {

    /*接口方法*/
    public List<SysMenu> List(SysMenu entity);//查询显示所有的SysUser

    public List<SysMenu>ListPage(PageUtils pageUtils);//分页查询

    public int save(SysMenu entity);//接受添加的SysUser

    public SysMenu findById(int id);

    public SysMenu findByName(String usename);

    public int update(SysMenu entity);

    int deleteById(int id);

    int count(PageUtils pageUtils);

    List<SysMenu> getAllParent();

    public boolean isDispatcher(int id);

   public boolean havaSubMenu(int id);

   public List<SysMenu> findByRoleId(Integer roleId);
}
