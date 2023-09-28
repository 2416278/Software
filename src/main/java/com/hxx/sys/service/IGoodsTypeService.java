package com.hxx.sys.service;

import com.hxx.sys.bean.SysGoodsType;
import com.hxx.sys.utils.PageUtils;

import java.util.List;

public interface IGoodsTypeService {


    public List<SysGoodsType> list(SysGoodsType entity);

    public void ListPage(PageUtils pageUtils);

    public int count(PageUtils pageUtils);

    public int save(SysGoodsType entity);

    public SysGoodsType findById(int id);

    public int updateById(SysGoodsType entity);

    public int deleteById(int id);


}
