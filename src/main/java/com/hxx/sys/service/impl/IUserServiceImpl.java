package com.hxx.sys.service.impl;

import com.hxx.sys.bean.SysUser;
import com.hxx.sys.dao.IUserDao;
import com.hxx.sys.dao.impl.IUserDaoImpl;
import com.hxx.sys.service.IUserService;
import com.hxx.sys.utils.PageUtils;

import java.util.List;

public class IUserServiceImpl implements IUserService {
    /**
     * 完成具体的增删改查
     * 调用dao处理数据库的对象
     * @param user
     * @return
     */
    private IUserDao dao=new IUserDaoImpl();

    @Override
    public List<SysUser> list(SysUser user) {
        return dao.List(user);
    }

    @Override
    public int save(SysUser user) {
        return dao.save(user);

    }

    @Override
    public SysUser findById(int id) {

        return dao.findById(id);
    }

    @Override
    public SysUser findByName(String usename) {
        return dao.findByName(usename);
    }

    @Override
    public int update(SysUser user) {
        return dao.update(user);
    }

    @Override
    public int deleteById(int id) {
        return dao.deleteById(id);
    }

    @Override
    public void ListPage(PageUtils pageUtils) {
        List<SysUser> list = dao.ListPage(pageUtils);
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
    public String checkUserName(String userName) {
        return dao.checkUserName(userName);
    }


}
