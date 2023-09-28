package com.hxx.sys.service.impl;

import com.hxx.sys.bean.SysGoods;
import com.hxx.sys.dao.IGoodsDao;
import com.hxx.sys.dao.impl.IGoodsDaoImpl;
import com.hxx.sys.service.IGoodsService;
import com.hxx.sys.utils.PageUtils;

import java.util.List;

public class IGoodsServiceImpl implements IGoodsService {
    private IGoodsDao dao=new IGoodsDaoImpl();

    @Override
    public List<SysGoods> list(SysGoods entity) {
        return dao.List(entity);
    }

    @Override
    public void ListPage(PageUtils pageUtils) {
        List<SysGoods> sysGoods = dao.ListPage(pageUtils);
        int count = dao.count(pageUtils);
        pageUtils.setList(sysGoods);
        pageUtils.setTotalCount(count);

    }

    @Override
    public int count(PageUtils pageUtils) {
        return dao.count(pageUtils);
    }

    @Override
    public int save(SysGoods entity) {
        return dao.save(entity);
    }

    @Override
    public SysGoods findById(int id) {
        return dao.findById(id);
    }

    @Override
    public SysGoods findByName(String name) { return dao.findByName(name); }

    @Override
    public List<SysGoods> listByTypeId(SysGoods good) {
        return dao.listByTypeId(good);
    }

    @Override
    public void updateAmountByName(Integer amount, String name) {
        dao.updateAmountByName(amount,name);
    }

    @Override
    public void updateNew(SysGoods good) {
       dao.updateNew(good);
    }

    @Override
    public int update(SysGoods entity) {
        return dao.update(entity);
    }

    @Override
    public int deleteById(int id) {
        return dao.deleteById(id);
    }


}
