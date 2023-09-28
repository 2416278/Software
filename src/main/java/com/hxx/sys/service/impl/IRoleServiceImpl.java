package com.hxx.sys.service.impl;

import com.hxx.sys.bean.SysRole;
import com.hxx.sys.bean.SysRoleMenu;
import com.hxx.sys.bean.SysUser;
import com.hxx.sys.dao.IRoleDao;
import com.hxx.sys.dao.impl.IRoleDaoImpl;
import com.hxx.sys.service.IRoleService;
import com.hxx.sys.service.IUserService;
import com.hxx.sys.utils.PageUtils;

import java.util.List;

public class IRoleServiceImpl implements IRoleService {

    private IRoleDao dao=new IRoleDaoImpl();

    private IUserService userService=new IUserServiceImpl();

    @Override
    public List<SysRole> list(SysRole entity) {
        return dao.List(entity);
    }

    @Override
    public int save(SysRole entity) {
        return dao.save(entity);
    }

    @Override
    public SysRole findById(int id) {
        return dao.findById(id);
    }

    @Override
    public SysRole findByName(String usename) {

        List<SysRole> list=dao.findByName(usename);
        return list!=null&&list.size()>0?list.get(0):null;
    }

    @Override
    public int updateById(SysRole entity) {
        return dao.updateById(entity);
    }

    public int updateByName(SysRole entity) {
        return dao.updateByName(entity);
    }

    @Override
    public int updatePassword(SysRole entity) {
        return dao.updatePassword(entity);
    }

    @Override
    public int deleteById(int id) {
        return dao.deleteById(id);
    }

    @Override
    public void ListPage(PageUtils pageUtils) {
        //查询分页的数据
        List<SysRole> list = dao.ListPage(pageUtils);
        //查询满足查询条件的记录数
        int count=dao.count(pageUtils);
        //封装分页的数据
        pageUtils.setList(list);
        pageUtils.setTotalCount(count);


    }

    @Override
    public int count(PageUtils pageUtils) {
        return dao.count(pageUtils);
    }

    @Override
    public boolean checkRoleDispatch(int roleId) {
        SysUser entity=new SysUser();
        entity.setUid(roleId);
        return userService.list(entity).size()==0?true:false;

    }

    @Override
    public void deleteMenuByRoleId(int id) {
        dao.deleteMenuByRoleId(id);
    }

    @Override
    public void saveDispatchMenu(int roleId, String menuId) {
        dao.saveDispatchMenu(roleId,menuId);

    }

    @Override
    public List<SysRoleMenu> queryByRoleId(int roleId) {

        return dao.queryByRoleId(roleId);
    }

    @Override
    public String checkUserName(String userName) {
        return dao.checkUserName(userName);
    }

    @Override
    public void addCount(String name) {
        dao.addCount(name);
    }

    @Override
    public void delCount(SysRole sysRole) {
        dao.delCount(sysRole);
    }

    @Override
    public void lock(SysRole sysRole) {
        dao.lock(sysRole);
    }

    @Override
    public void unLock(SysRole sysRole) {
        dao.unLock(sysRole);
    }

    @Override
    public void deleteByName(String name) {
         dao.deleteByNmae(name);
    }

}
