package com.hxx.sys.dao.impl;

import com.hxx.sys.bean.SysUser;
import com.hxx.sys.dao.IUserDao;
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

/**
 * 系统用Dao的实现类
 * 连接数据库查询数据
 */
public class IUserDaoImpl implements IUserDao {
    @Override
    public String checkUserName(String userName){
        QueryRunner queryRunner=MyDbUtils.getQueryRunner();
        String sql="select count(1) from sys_user where usename=?";

        try {
            int count=queryRunner.query(sql, new ResultSetHandler<Integer>() {
                @Override
                public Integer handle(ResultSet rs) throws SQLException {
                    rs.next();
                    int count = rs.getInt(1);
                    return count;
                }
            },userName);
            return count==0?"success":"error";
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return "error";
    }


    @Override

    public List<SysUser> List(SysUser user) {
        QueryRunner queryRunner=MyDbUtils.getQueryRunner();
        String sql="select * from sys_user ";
        try {
            //BeanListHandler 只能映射相同的字段和属性，如果字段和属性名称不同无法映射
            //List<SysUser>List=queryRunner.query(sql,new BeanListHandler<SysUser>(SysUser.class));
            List<SysUser>List=queryRunner.query(sql, new ResultSetHandler<java.util.List<SysUser>>() {
                @Override
                public java.util.List<SysUser> handle(ResultSet resultSet) throws SQLException {
                    List<SysUser>list=new ArrayList<>();
                    while(resultSet.next()){
                        SysUser user=new SysUser();
                        user.setUid(resultSet.getInt("uid"));
                        user.setUsename(resultSet.getString("usename"));
                        user.setGrade(resultSet.getString("grade"));
                        user.setPhone(resultSet.getString("phone"));
                        user.setEmail(resultSet.getString("email"));
                        user.setCreateTime(resultSet.getDate("create_time"));
                        user.setTotal(resultSet.getDouble("total"));
                        list.add(user);
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
/*分页查询*/
    @Override
    public List<SysUser> ListPage(PageUtils pageUtils) {
        QueryRunner queryRunner=MyDbUtils.getQueryRunner();
        String sql = "select * from sys_user limit ?,?";
        String sqlRole="select * from sys_role";
        if(StringUtils.isNotEmpty(pageUtils.getKey())){
// 需要带条件查询
            sql = "select * from sys_user where usename like '%"+pageUtils.getKey()+"%' or grade like '%"+pageUtils.getKey()+"%' limit ?,?";
        }

        //计算 分页开始的页数
        int startNo=pageUtils.getStart();
        try {
            //BeanListHandler 只能映射相同的字段和属性，如果字段和属性名称不同无法映射
            //List<SysUser>List=queryRunner.query(sql,new BeanListHandler<SysUser>(SysUser.class));
            List<SysUser>List=queryRunner.query(sql, new ResultSetHandler<java.util.List<SysUser>>() {
                @Override
                public java.util.List<SysUser> handle(ResultSet resultSet) throws SQLException {
                    List<SysUser>list=new ArrayList<>();
                    while(resultSet.next()){
                        SysUser user=new SysUser();
                        user.setUid(resultSet.getInt("uid"));
                        user.setUsename(resultSet.getString("usename"));
                        user.setGrade(resultSet.getString("grade"));
                        user.setPhone(resultSet.getString("phone"));
                        user.setEmail(resultSet.getString("email"));
                        user.setCreateTime(resultSet.getDate("create_time"));
                        user.setTotal(resultSet.getDouble("total"));
                        list.add(user);
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

    /*添加用户具体实现*/
    @Override
    public int save(SysUser user) {
        QueryRunner queryRunner=MyDbUtils.getQueryRunner();//queryRunner对象
        String sql="insert into sys_user(usename,grade,create_time,total,phone,email)values(?,?,?,?,?,?)";
        try {
            return queryRunner.update(sql,user.getUsename(),user.getGrade(),user.getCreateTime(),user.getTotal(),user.getPhone(),user.getEmail());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        System.out.println("插入失败");
        return -1;
    }

    @Override
    public SysUser findById(int id) {
        QueryRunner queryRunner=MyDbUtils.getQueryRunner();
        /*根据编号查询数据库中的数据*/
        String sql="select * from sys_user where uid=?";
        try {
            //BeanListHandler 只能映射相同的字段和属性，如果字段和属性名称不同无法映射
            //List<SysUser>List=queryRunner.query(sql,new BeanListHandler<SysUser>(SysUser.class));
            SysUser user=queryRunner.query(sql, new ResultSetHandler<SysUser>() {
                @Override
                public SysUser handle(ResultSet resultSet) throws SQLException {
                    if(resultSet.next()){
                        SysUser user=new SysUser();
                        user.setUid(resultSet.getInt("uid"));
                        user.setUsename(resultSet.getString("usename"));
                        user.setGrade(resultSet.getString("grade"));
                        user.setPhone(resultSet.getString("phone"));
                        user.setEmail(resultSet.getString("email"));
                        user.setCreateTime(resultSet.getDate("create_time"));
                        user.setTotal(resultSet.getDouble("total"));
                        return user;
                    }
                    return null;
                }
            },id);
            return user;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return null;
    }

    @Override
    public SysUser findByName(String usename) { QueryRunner queryRunner=MyDbUtils.getQueryRunner();
        /*根据编号查询数据库中的数据*/
        String sql="select * from sys_user where usename=?";
        try {
            //BeanListHandler 只能映射相同的字段和属性，如果字段和属性名称不同无法映射
            //List<SysUser>List=queryRunner.query(sql,new BeanListHandler<SysUser>(SysUser.class));
            SysUser user=queryRunner.query(sql, new ResultSetHandler<SysUser>() {
                @Override
                public SysUser handle(ResultSet resultSet) throws SQLException {
                    if(resultSet.next()){
                        SysUser user=new SysUser();
                        user.setUid(resultSet.getInt("uid"));
                        user.setUsename(resultSet.getString("usename"));
                        user.setGrade(resultSet.getString("grade"));
                        user.setPhone(resultSet.getString("phone"));
                        user.setEmail(resultSet.getString("email"));
                        user.setCreateTime(resultSet.getDate("create_time"));
                        user.setTotal(resultSet.getDouble("total"));
                        return user;
                    }
                    return null;
                }
            },usename);
            return user;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return null;
    }

    @Override
    public int update(SysUser user) {
        QueryRunner queryRunner=MyDbUtils.getQueryRunner();//queryRunner对象
        String sql="update sys_user set usename=?,grade=?,create_time=?,total=?,phone=?,email=? where uid=?";
        try {
            return queryRunner.update(sql,user.getUsename(),user.getGrade(),user.getCreateTime(),user.getTotal(),user.getPhone(),user.getEmail(),user.getUid());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        System.out.println("更新失败");
        return -1;
    }

    @Override
    public int deleteById(int id) {
        QueryRunner queryRunner=MyDbUtils.getQueryRunner();//queryRunner对象
        String sql="delete from sys_user where uid=?";
        try {
            return queryRunner.update(sql,id);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        System.out.println("删除失败");
        return -1;
    }

    @Override
    public int count(PageUtils pageUtils) {
        QueryRunner queryRunner=MyDbUtils.getQueryRunner();
        String sql="select count(1) from sys_user";
        if(StringUtils.isNotEmpty(pageUtils.getKey())){
            sql="select count(1) from sys_user where usename like '%"+pageUtils.getKey()+"%' or grade like '%"+pageUtils.getKey()+"%'";

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
