package com.hxx.sys.service;

import com.hxx.sys.bean.SysRole;
import com.hxx.sys.bean.SysRoleMenu;
import com.hxx.sys.utils.PageUtils;

import java.util.List;

public interface IRoleService {

    public List<SysRole> list(SysRole entity);

    public int save(SysRole entity);

    public SysRole findById(int id);

    public SysRole findByName(String usename);

    public int updateById(SysRole entity);

    public int updateByName(SysRole entity);

    public int updatePassword(SysRole entity);

    public int deleteById(int id);

    public void ListPage(PageUtils pageUtils);

    public int count(PageUtils pageUtils);

    public boolean checkRoleDispatch(int id);

    public void deleteMenuByRoleId(int parseInt);

    public void saveDispatchMenu(int roleId, String menuId);

    public List<SysRoleMenu> queryByRoleId(int roleId);

    public String checkUserName(String userName);

    public void addCount(String name);

    public void delCount(SysRole sysRole);

    void lock(SysRole sysRole);

    void unLock(SysRole sysRole);

    void deleteByName(String name);
}
