package com.hxx.sys.dao.impl;

import com.hxx.sys.bean.SysGoods;
import com.hxx.sys.bean.SysGoodsType;
import com.hxx.sys.dao.IGoodsDao;
import com.hxx.sys.dao.IGoodsTypeDao;
import com.hxx.sys.utils.MyDbUtils;
import com.hxx.sys.utils.PageUtils;
import com.hxx.sys.utils.StringUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class IGoodsTypeDaoImpl implements IGoodsTypeDao {

    @Override
    public List<SysGoodsType> List(SysGoodsType entity) {
        QueryRunner queryRunner= MyDbUtils.getQueryRunner();
        String sql = "select * from sys_goodsType";

        try {
            //BeanListHandler 只能映射相同的字段和属性，如果字段和属性名称不同无法映射
            //List<SysUser>List=queryRunner.query(sql,new BeanListHandler<SysUser>(SysUser.class));
            List<SysGoodsType>List=queryRunner.query(sql, new ResultSetHandler<java.util.List<SysGoodsType>>() {
                @Override
                public java.util.List<SysGoodsType> handle(ResultSet resultSet) throws SQLException {
                    List<SysGoodsType>list=new ArrayList<>();
                    while(resultSet.next()){
                        SysGoodsType entity=new SysGoodsType();
                        entity.setId(resultSet.getInt("id"));
                        entity.setName(resultSet.getString("name"));
                        entity.setNotes(resultSet.getString("notes"));
                        list.add(entity);
                    }
                    return list;
                }
            });
            return List;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return null;
    }

    @Override
    public List<SysGoodsType> ListPage(PageUtils pageUtils) {
        QueryRunner queryRunner= MyDbUtils.getQueryRunner();
        String sql = "select * from sys_goodsType limit ?,?";
        if(StringUtils.isNotEmpty(pageUtils.getKey())){
// 需要带条件查询
            sql = "select * from sys_goodsType where name like '%"+pageUtils.getKey()+"%' limit ?,?";
        }
        //计算 分页开始的页数
        int startNo=pageUtils.getStart();
        try {
            //BeanListHandler 只能映射相同的字段和属性，如果字段和属性名称不同无法映射
            //List<SysUser>List=queryRunner.query(sql,new BeanListHandler<SysUser>(SysUser.class));
            List<SysGoodsType>List=queryRunner.query(sql, new ResultSetHandler<java.util.List<SysGoodsType>>() {
                @Override
                public java.util.List<SysGoodsType> handle(ResultSet resultSet) throws SQLException {
                    List<SysGoodsType>list=new ArrayList<>();
                    while(resultSet.next()){
                        SysGoodsType entity=new SysGoodsType();
                        entity.setId(resultSet.getInt("id"));
                        entity.setName(resultSet.getString("name"));
                        entity.setNotes(resultSet.getString("notes"));
                        list.add(entity);
                    }
                    return list;
                }
            },startNo,pageUtils.getPageSize());
            return List;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return null;
    }

    @Override
    public int save(SysGoodsType entity) {
        QueryRunner queryRunner=MyDbUtils.getQueryRunner();//连接池queryRunner对象
        String sql="insert into sys_goodsType(name,notes)values(?,?)";
        try {
            return queryRunner.update(sql,entity.getName(),entity.getNotes());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        System.out.println("插入失败");
        return -1;
    }

    @Override
    public SysGoodsType findById(int id) {
        QueryRunner queryRunner= MyDbUtils.getQueryRunner();
        String sql="select * from sys_goodsType where id=?";
        try {
            //BeanListHandler 只能映射相同的字段和属性，如果字段和属性名称不同无法映射
            //List<SysUser>List=queryRunner.query(sql,new BeanListHandler<SysUser>(SysUser.class));
            return queryRunner.query(sql, new ResultSetHandler<SysGoodsType>() {
                @Override
                public SysGoodsType handle(ResultSet resultSet) throws SQLException {
                    if(resultSet.next()){
                        SysGoodsType entity=new SysGoodsType();
                        entity.setId(resultSet.getInt("id"));
                        entity.setName(resultSet.getString("name"));
                        entity.setNotes(resultSet.getString("notes"));
                        return entity;
                    }
                    return null;
                }
            },id);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return null;
    }


    @Override
    public int updateById(SysGoodsType entity) {
        QueryRunner queryRunner=MyDbUtils.getQueryRunner();//连接池queryRunner对象
        String sql="update sys_goodsType set name=?,notes=? where id=?";
        try {
            return queryRunner.update(sql,entity.getName(),entity.getNotes(),entity.getId());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return 0;
    }



    @Override
    public int deleteById(int id) {
        QueryRunner queryRunner=MyDbUtils.getQueryRunner();//连接池queryRunner对象
        String sql="delete from sys_goodsType where id=?";
        try {
            return queryRunner.update(sql,id);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return 0;
    }

    @Override
    public int count(PageUtils pageUtils) {
        QueryRunner queryRunner=MyDbUtils.getQueryRunner();
        String sql="select count(1) from sys_goodsType";
        if(StringUtils.isNotEmpty(pageUtils.getKey())){
            sql="select count(1) from sys_goodsType where name like '%"+pageUtils.getKey()+"%' ";

        }
        try {
            return queryRunner.query(sql,new ResultSetHandler<Integer>(){

                @Override
                public Integer handle(ResultSet resultSet) throws SQLException {
                    resultSet.next();
                    return resultSet.getInt(1);
                }
            });
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return 0;
    }

}
