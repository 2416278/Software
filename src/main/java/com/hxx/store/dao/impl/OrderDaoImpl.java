package com.hxx.store.dao.impl;

import com.hxx.store.bean.Cart;
import com.hxx.store.bean.Order;
import com.hxx.store.dao.CartDao;
import com.hxx.store.dao.OrderDao;
import com.hxx.sys.bean.SysRole;
import com.hxx.sys.utils.MyDbUtils;
import com.hxx.sys.utils.PageUtils;
import com.hxx.sys.utils.StringUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class OrderDaoImpl implements OrderDao {
    @Override
    public List<Order> List(Order entity) {
        QueryRunner queryRunner= MyDbUtils.getQueryRunner();
        String sql = "select * from user_order";

        try {

            List<Order>List=queryRunner.query(sql, new ResultSetHandler<java.util.List<Order>>() {
                @Override
                public java.util.List<Order> handle(ResultSet resultSet) throws SQLException {
                    List<Order>list=new ArrayList<>();
                    while(resultSet.next()){
                        Order entity=new Order();
                        entity.setId(resultSet.getInt("id"));
                        entity.setTime(resultSet.getDate("time"));
                        entity.setImg(resultSet.getString("img"));
                        entity.setGoodsName(resultSet.getString("goodsName"));
                        entity.setTotal(resultSet.getDouble("total"));
                        entity.setAmount(resultSet.getInt("amount"));

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
    public List<Order> ListPage(PageUtils pageUtils, SysRole user) {
        QueryRunner queryRunner= MyDbUtils.getQueryRunner();
        String sql = "select * from user_order where 1=1";
        if(StringUtils.isNotEmpty(pageUtils.getKey())){
            // 需要带条件查询
            sql+= " and goodsName like '%"+pageUtils.getKey()+"%' ";
        }
        if(user!=null&&user.getIsAdmin()==false){
            //不是管理员拼接用户名
            sql+=" and name= '"+user.getName()+"'";

        }
        sql+=" limit ?,? ";
        //计算 分页开始的页数
        int startNo=pageUtils.getStart();
        try {
            List<Order>List=queryRunner.query(sql, new ResultSetHandler<java.util.List<Order>>() {
                @Override
                public java.util.List<Order> handle(ResultSet resultSet) throws SQLException {
                    List<Order>list=new ArrayList<>();
                    while(resultSet.next()){
                        Order entity=new Order();
                        entity.setId(resultSet.getInt("id"));
                        entity.setTime(resultSet.getDate("time"));

                        entity.setImg(resultSet.getString("img"));
                        entity.setGoodsName(resultSet.getString("goodsName"));
                        entity.setTotal(resultSet.getDouble("total"));
                        entity.setAmount(resultSet.getInt("amount"));

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
    public int save(Order entity) {
        QueryRunner queryRunner=MyDbUtils.getQueryRunner();//连接池queryRunner对象
        String sql="insert into user_order(img,goodsName,total,amount)values(?,?,?,?)";
        try {
            return queryRunner.update(sql,entity.getImg(),entity.getGoodsName(),entity.getTotal(),entity.getAmount());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        System.out.println("插入失败");
        return -1;
    }

    @Override
    public int saveName(Order entity) {
        QueryRunner queryRunner=MyDbUtils.getQueryRunner();//连接池queryRunner对象
        String sql="insert into user_order(img,goodsName,total,amount,name)values(?,?,?,?,?)";
        try {
            return queryRunner.update(sql,entity.getImg(),entity.getGoodsName(),entity.getTotal(),entity.getAmount(),entity.getName());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        System.out.println("插入失败");
        return -1;
    }

    @Override
    public Order findById(int id) {
        QueryRunner queryRunner= MyDbUtils.getQueryRunner();
        String sql="select * from user_order where id=?";
        try {
          return queryRunner.query(sql, new ResultSetHandler<Order>() {
                @Override
                public Order handle(ResultSet resultSet) throws SQLException {
                    if(resultSet.next()){
                        Order entity=new Order();
                        entity.setId(resultSet.getInt("id"));
                        entity.setTime(resultSet.getDate("time"));
                        entity.setImg(resultSet.getString("img"));
                        entity.setGoodsName(resultSet.getString("goodsName"));
                        entity.setTotal(resultSet.getDouble("total"));
                        entity.setAmount(resultSet.getInt("amount"));
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



    public int delete() {
        QueryRunner queryRunner = MyDbUtils.getQueryRunner(); // 连接池queryRunner对象
        String sql = "delete from user_order where amount=?";
        try {
            return queryRunner.update(sql, 0); // 将参数amount为0
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return 0;
    }

    @Override
    public int count(PageUtils pageUtils,SysRole user) {
        QueryRunner queryRunner=MyDbUtils.getQueryRunner();
        String sql="select count(1) from user_order where 1=1";
        if(StringUtils.isNotEmpty(pageUtils.getKey())){
// 需要带条件查询
            sql+= " and goodsName like '%"+pageUtils.getKey()+"%' ";
        }
        if(user!=null&&user.getIsAdmin()==false){
            //不是管理员拼接用户名
            sql+= " and name= '"+user.getName()+"'";

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
    public int deleteByName(String name) {
        QueryRunner queryRunner = MyDbUtils.getQueryRunner(); // 连接池queryRunner对象
        String sql = "delete from user_order where name=?";
        try {
            return queryRunner.update(sql, name); // 将参数amount为0
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return 0;
    }

    @Override
    public int deleteById(int id) {
        QueryRunner queryRunner = MyDbUtils.getQueryRunner(); // 连接池queryRunner对象
        String sql = "delete from user_order where id=?";
        try {
            return queryRunner.update(sql, id); // 将参数amount为0
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return 0;
    }

      @Override
    public Order findByName(String name) {
        QueryRunner queryRunner= MyDbUtils.getQueryRunner();
        String sql="select * from user_order where name=?";
        try {
            return queryRunner.query(sql, new ResultSetHandler<Order>() {
                @Override
                public Order handle(ResultSet resultSet) throws SQLException {
                    if(resultSet.next()){
                        Order entity=new Order();
                        entity.setId(resultSet.getInt("id"));
                        entity.setTime(resultSet.getDate("time"));
                        entity.setImg(resultSet.getString("img"));
                        entity.setGoodsName(resultSet.getString("goodsName"));
                        entity.setTotal(resultSet.getDouble("total"));
                        entity.setAmount(resultSet.getInt("amount"));
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


}
