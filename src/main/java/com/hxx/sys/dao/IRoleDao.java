package com.hxx.sys.dao;
import com.hxx.sys.bean.SysRole;
import com.hxx.sys.bean.SysRoleMenu;
import com.hxx.sys.utils.PageUtils;

import java.util.List;

public interface IRoleDao {

    /*接口方法*/
    public List<SysRole> List(SysRole entity);//查询显示所有的SysUser

    public List<SysRole>ListPage(PageUtils pageUtils);//分页查询

    public int save(SysRole entity);//接受添加的SysUser

    public SysRole findById(int id);

    public List<SysRole> findByName(String usename);

    public int updateById(SysRole entity);

    public int updateByName(SysRole entity);

    public int updatePassword(SysRole entity);

    int deleteById(int id);

    int count(PageUtils pageUtils);

    public String checkUserName(String userName);

   public void deleteMenuByRoleId(int id);

    public void saveDispatchMenu(int roleId, String menuId);

    public List<SysRoleMenu> queryByRoleId(int roleId);

     public void addCount(String name);

    void delCount(SysRole sysRole);

    void lock(SysRole sysRole);

    void unLock(SysRole sysRole);

    void deleteByNmae(String name);
}
