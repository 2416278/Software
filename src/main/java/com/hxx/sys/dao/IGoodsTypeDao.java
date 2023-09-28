package com.hxx.sys.dao;

import com.hxx.sys.bean.SysGoods;
import com.hxx.sys.bean.SysGoodsType;
import com.hxx.sys.utils.PageUtils;

import java.util.List;

public interface IGoodsTypeDao {

    /*接口方法*/
    public List<SysGoodsType> List(SysGoodsType entity);//查询显示所有的SysUser

    public List<SysGoodsType>ListPage(PageUtils pageUtils);//分页查询

    public int save(SysGoodsType entity);//接受添加的SysUser

    public SysGoodsType findById(int id);

    public int updateById(SysGoodsType entity);

    int deleteById(int id);

    int count(PageUtils pageUtils);

}
