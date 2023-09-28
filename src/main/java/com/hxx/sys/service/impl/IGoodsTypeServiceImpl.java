package com.hxx.sys.service.impl;

import com.hxx.sys.bean.SysGoodsType;
import com.hxx.sys.dao.IGoodsDao;
import com.hxx.sys.dao.IGoodsTypeDao;
import com.hxx.sys.dao.impl.IGoodsDaoImpl;
import com.hxx.sys.dao.impl.IGoodsTypeDaoImpl;
import com.hxx.sys.service.IGoodsService;
import com.hxx.sys.service.IGoodsTypeService;
import com.hxx.sys.utils.PageUtils;

import java.util.List;

public class IGoodsTypeServiceImpl implements IGoodsTypeService {
    private IGoodsTypeDao dao=new IGoodsTypeDaoImpl();

    @Override
    public List<SysGoodsType> list(SysGoodsType entity) {
        return dao.List(entity);
    }

    @Override
    public void ListPage(PageUtils pageUtils) {
        List<SysGoodsType> SysGoodsType = dao.ListPage(pageUtils);
        int count = dao.count(pageUtils);
        pageUtils.setList(SysGoodsType);
        pageUtils.setTotalCount(count);

    }

    @Override
    public int count(PageUtils pageUtils) {
        return dao.count(pageUtils);
    }

    @Override
    public int save(SysGoodsType entity) {
        return dao.save(entity);
    }

    @Override
    public SysGoodsType findById(int id) {
        return dao.findById(id);
    }

    @Override
    public int updateById(SysGoodsType entity) {
        return dao.updateById(entity);
    }

    @Override
    public int deleteById(int id) {
        return dao.deleteById(id);
    }


}
