package com.hxx.sys.service;

import com.hxx.sys.bean.SysUser;
import com.hxx.sys.utils.PageUtils;

import java.util.List;

public interface IUserService {
    public List<SysUser>list(SysUser entity);

    public int save(SysUser entity);

    public SysUser findById(int id);

    public SysUser findByName(String usename);

    public int update(SysUser entity);

    public int deleteById(int id);

    public void ListPage(PageUtils pageUtils);

    public int count(PageUtils pageUtils);

    public String checkUserName(String userName);

}
