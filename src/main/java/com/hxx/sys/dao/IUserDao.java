package com.hxx.sys.dao;

import com.hxx.sys.bean.SysUser;
import com.hxx.sys.utils.PageUtils;

import java.util.List;

/*
Dao层的接口对象
 */
public interface IUserDao {
    /*接口方法*/
    public List<SysUser> List(SysUser entity);//查询显示所有的SysUser

    public List<SysUser>ListPage(PageUtils pageUtils);//分页查询

    public int save(SysUser entity);//接受添加的SysUser

    public SysUser findById(int id);

    public SysUser findByName(String usename);

    public int update(SysUser entity);

    int deleteById(int id);

    int count(PageUtils pageUtils);

    public String checkUserName(String userName);
}
