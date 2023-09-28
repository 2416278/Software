package com.hxx.sys.dao.impl;

import com.hxx.sys.bean.SysGoods;
import com.hxx.sys.bean.SysMenu;
import com.hxx.sys.bean.SysRole;
import com.hxx.sys.dao.IGoodsDao;
import com.hxx.sys.utils.MyDbUtils;
import com.hxx.sys.utils.PageUtils;
import com.hxx.sys.utils.StringUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class IGoodsDaoImpl implements IGoodsDao {
    @Override
    public List<SysGoods> List(SysGoods entity) {
        QueryRunner queryRunner= MyDbUtils.getQueryRunner();
        String sql = "select * from sys_goods";

        try {
            //BeanListHandler 只能映射相同的字段和属性，如果字段和属性名称不同无法映射
            //List<SysUser>List=queryRunner.query(sql,new BeanListHandler<SysUser>(SysUser.class));
            List<SysGoods>List=queryRunner.query(sql, new ResultSetHandler<java.util.List<SysGoods>>() {
                @Override
                public java.util.List<SysGoods> handle(ResultSet resultSet) throws SQLException {
                    List<SysGoods>list=new ArrayList<>();
                    while(resultSet.next()){
                        SysGoods entity=new SysGoods();
                        entity.setId(resultSet.getInt("id"));
                        entity.setName(resultSet.getString("name"));
                        entity.setMake(resultSet.getString("make"));
                        entity.setMaketime(resultSet.getDate("maketime"));
                        entity.setImg(resultSet.getString("img"));
                        entity.setSize(resultSet.getString("size"));
                        entity.setBuyprice(resultSet.getDouble("buyprice"));
                        entity.setSellprice(resultSet.getDouble("sellprice"));
                        entity.setCount(resultSet.getInt("count"));
                        entity.setTypeId(resultSet.getInt("type_id"));
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
    public List<SysGoods> ListPage(PageUtils pageUtils) {
        QueryRunner queryRunner= MyDbUtils.getQueryRunner();
        String sql = "select * from sys_goods limit ?,?";
        if(StringUtils.isNotEmpty(pageUtils.getKey())){
// 需要带条件查询
            sql = "select * from sys_goods where name like '%"+pageUtils.getKey()+"%' limit ?,?";
        }
        //计算 分页开始的页数
        int startNo=pageUtils.getStart();
        try {
            //BeanListHandler 只能映射相同的字段和属性，如果字段和属性名称不同无法映射
            //List<SysUser>List=queryRunner.query(sql,new BeanListHandler<SysUser>(SysUser.class));
            List<SysGoods>List=queryRunner.query(sql, new ResultSetHandler<java.util.List<SysGoods>>() {
                @Override
                public java.util.List<SysGoods> handle(ResultSet resultSet) throws SQLException {
                    List<SysGoods>list=new ArrayList<>();
                    while(resultSet.next()){
                        SysGoods entity=new SysGoods();
                        entity.setId(resultSet.getInt("id"));
                        entity.setName(resultSet.getString("name"));
                        entity.setMake(resultSet.getString("make"));
                        entity.setMaketime(resultSet.getDate("maketime"));
                        entity.setImg(resultSet.getString("img"));
                        entity.setSize(resultSet.getString("size"));
                        entity.setBuyprice(resultSet.getDouble("buyprice"));
                        entity.setSellprice(resultSet.getDouble("sellprice"));
                        entity.setCount(resultSet.getInt("count"));
                        entity.setTypeId(resultSet.getInt("type_id"));
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
    public int save(SysGoods entity) {
        QueryRunner queryRunner=MyDbUtils.getQueryRunner();//连接池queryRunner对象
        String sql="insert into sys_goods(name,make,maketime,img,size,buyprice,sellprice,count,type_id)values(?,?,?,?,?,?,?,?,?)";
        try {
            return queryRunner.update(sql,entity.getName(),entity.getMake(),entity.getMaketime(),entity.getImg(),entity.getSize(),entity.getBuyprice(),entity.getSellprice(),entity.getCount(),entity.getTypeId());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        System.out.println("插入失败");
        return -1;
    }

    @Override
    public SysGoods findById(int id) {
        QueryRunner queryRunner= MyDbUtils.getQueryRunner();
        String sql="select * from sys_goods where id=?";
        try {
            //BeanListHandler 只能映射相同的字段和属性，如果字段和属性名称不同无法映射
            //List<SysUser>List=queryRunner.query(sql,new BeanListHandler<SysUser>(SysUser.class));
            return queryRunner.query(sql, new ResultSetHandler<SysGoods>() {
                @Override
                public SysGoods handle(ResultSet resultSet) throws SQLException {
                    if(resultSet.next()){
                        SysGoods entity=new SysGoods();
                        entity.setId(resultSet.getInt("id"));
                        entity.setName(resultSet.getString("name"));
                        entity.setMake(resultSet.getString("make"));
                        entity.setMaketime(resultSet.getDate("maketime"));
                        entity.setImg(resultSet.getString("img"));
                        entity.setSize(resultSet.getString("size"));
                        entity.setBuyprice(resultSet.getDouble("buyprice"));
                        entity.setSellprice(resultSet.getDouble("sellprice"));
                        entity.setCount(resultSet.getInt("count"));
                        entity.setTypeId(resultSet.getInt("type_id"));
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
    public int update(SysGoods entity) {
        QueryRunner queryRunner=MyDbUtils.getQueryRunner();//连接池queryRunner对象
        String sql="update sys_goods set name=?,make=?,makeTime=?,img=?,size=?,buyprice=?,sellprice=?,count=?,type_id=? where id=?";
        try {
            return queryRunner.update(sql,entity.getName(),entity.getMake(),entity.getMaketime(),entity.getImg(),entity.getSize(),entity.getBuyprice(),entity.getSellprice(),entity.getCount(),entity.getTypeId(),entity.getId());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return 0;
    }
    @Override
    public void updateNew(SysGoods entity) {
        QueryRunner queryRunner=MyDbUtils.getQueryRunner();//连接池queryRunner对象
        String sql="update sys_goods set name=?,make=?,makeTime=?,size=?,buyprice=?,sellprice=?,count=?,type_id=? where id=?";
        try {
            queryRunner.update(sql,entity.getName(),entity.getMake(),entity.getMaketime(),entity.getSize(),entity.getBuyprice(),entity.getSellprice(),entity.getCount(),entity.getTypeId(),entity.getId());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public int deleteById(int id) {
        QueryRunner queryRunner=MyDbUtils.getQueryRunner();//连接池queryRunner对象
        String sql="delete from sys_goods where id=?";
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
        String sql="select count(1) from sys_goods";
        if(StringUtils.isNotEmpty(pageUtils.getKey())){
            sql="select count(1) from sys_goods where name like '%"+pageUtils.getKey()+"%' ";

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

    @Override
    public SysGoods findByName(String name) {
        QueryRunner queryRunner= MyDbUtils.getQueryRunner();
        String sql="select * from sys_goods where name=?";
        try {
            //BeanListHandler 只能映射相同的字段和属性，如果字段和属性名称不同无法映射
            //List<SysUser>List=queryRunner.query(sql,new BeanListHandler<SysUser>(SysUser.class));
            return queryRunner.query(sql, new ResultSetHandler<SysGoods>() {
                @Override
                public SysGoods handle(ResultSet resultSet) throws SQLException {
                    if(resultSet.next()){
                        SysGoods entity=new SysGoods();
                        entity.setId(resultSet.getInt("id"));
                        entity.setName(resultSet.getString("name"));
                        entity.setMake(resultSet.getString("make"));
                        entity.setMaketime(resultSet.getDate("maketime"));
                        entity.setImg(resultSet.getString("img"));
                        entity.setSize(resultSet.getString("size"));
                        entity.setBuyprice(resultSet.getDouble("buyprice"));
                        entity.setSellprice(resultSet.getDouble("sellprice"));
                        entity.setCount(resultSet.getInt("count"));
                        entity.setTypeId(resultSet.getInt("type_id"));
                        return entity;
                    }
                    return null;
                }
            },name);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return null;
    }

    @Override
    public List<SysGoods> listByTypeId(SysGoods good) {
        QueryRunner queryRunner= MyDbUtils.getQueryRunner();
        String sql="select * from sys_goods where 1 = 1";
        if(good!=null){
            if(good.getTypeId()!=null&&good.getTypeId()>0){
                sql+=" and type_id= "+good.getTypeId();
            }
        }
        try {
            //BeanListHandler 只能映射相同的字段和属性，如果字段和属性名称不同无法映射
            //List<SysUser>List=queryRunner.query(sql,new BeanListHandler<SysUser>(SysUser.class));
            List<SysGoods>List=queryRunner.query(sql, new ResultSetHandler<java.util.List<SysGoods>>() {
                @Override
                public java.util.List<SysGoods> handle(ResultSet resultSet) throws SQLException {
                    List<SysGoods>list=new ArrayList<>();
                    while(resultSet.next()){
                        SysGoods entity=new SysGoods();
                        entity.setId(resultSet.getInt("id"));
                        entity.setName(resultSet.getString("name"));
                        entity.setMake(resultSet.getString("make"));
                        entity.setMaketime(resultSet.getDate("maketime"));
                        entity.setImg(resultSet.getString("img"));
                        entity.setSize(resultSet.getString("size"));
                        entity.setBuyprice(resultSet.getDouble("buyprice"));
                        entity.setSellprice(resultSet.getDouble("sellprice"));
                        entity.setCount(resultSet.getInt("count"));
                        entity.setTypeId(resultSet.getInt("type_id"));
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
    public void updateAmountByName(Integer amount, String name) {
        QueryRunner queryRunner = MyDbUtils.getQueryRunner(); // 连接池queryRunner对象
        String sql = "update sys_goods set count = count - ? where name = ?";
        try {
            queryRunner.update(sql, amount, name);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
