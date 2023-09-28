package com.hxx.sys.service.impl;

import com.hxx.sys.bean.SysMenu;
import com.hxx.sys.bean.SysUser;
import com.hxx.sys.dao.IMenuDao;
import com.hxx.sys.dao.impl.IMenuDaoImpl;
import com.hxx.sys.service.IMenuService;
import com.hxx.sys.service.IUserService;
import com.hxx.sys.utils.PageUtils;

import java.util.List;

public class IMenuServiceImpl implements IMenuService {
    private IMenuDao dao=new IMenuDaoImpl();


    @Override
    public List<SysMenu> list(SysMenu entity) {
        return dao.List(entity);
    }

    @Override
    public int save(SysMenu entity) {
        return dao.save(entity);
    }

    @Override
    public SysMenu findById(int id) {
        return dao.findById(id);
    }

    @Override
    public int update(SysMenu entity) {
        return dao.update(entity);
    }

    @Override
    public int deleteById(int id) {
        return dao.deleteById(id);
    }

    @Override
    public void ListPage(PageUtils pageUtils) {
        //查询分页的数据
        List<SysMenu> list = dao.ListPage(pageUtils);
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
    public List<SysMenu> getAllParent() {

        return dao.getAllParent();
    }

    @Override
    public boolean isDispatcher(int id) {

        return dao.isDispatcher(id);
    }

    @Override
    public boolean haveSubMenu(int id) {

        return dao.havaSubMenu(id);
    }

    @Override
    public List<SysMenu> findByRoleId(Integer roleId) {

        return dao.findByRoleId(roleId);
    }

}
