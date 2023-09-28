package com.hxx.sys.dao.impl;
import java.sql.*;

import com.hxx.sys.bean.SysRole;
import com.hxx.sys.bean.SysRoleMenu;
import com.hxx.sys.bean.SysUser;
import com.hxx.sys.dao.IRoleDao;
import com.hxx.sys.utils.MyDbUtils;
import com.hxx.sys.utils.PageUtils;
import com.hxx.sys.utils.StringUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;
public class IRoleDaoImpl implements IRoleDao {

    @Override
    public List<SysRole> List(SysRole entity) {
        QueryRunner queryRunner= MyDbUtils.getQueryRunner();
        /*
        查询数据库中的数据
         */
        String sql="select * from sys_role";
        String sqlUser="select *from sys_user";
        try {
            //BeanListHandler 只能映射相同的字段和属性，如果字段和属性名称不同无法映射
            //List<SysUser>List=queryRunner.query(sql,new BeanListHandler<SysUser>(SysUser.class));
            List<SysRole>List=queryRunner.query(sql, new ResultSetHandler<java.util.List<SysRole>>() {
                //判断是否在用户数据库中
                @Override
                public java.util.List<SysRole> handle(ResultSet resultSet) throws SQLException {

                    List<SysRole>list=new ArrayList<>();
                    while(resultSet.next()){
                        SysRole entity=new SysRole();
                        entity.setId(resultSet.getInt("id"));
                        entity.setName(resultSet.getString("name"));
                        entity.setNotes(resultSet.getString("notes"));
                        entity.setPassword(resultSet.getString("password"));
                        entity.setCreateTime(resultSet.getDate("time"));
                        entity.setLoginErrorCount(resultSet.getInt("login_error_count"));
                        entity.setLateLoginErrorTime(resultSet.getDate("last_login_error_time"));
                        entity.setIsLocked(resultSet.getInt("isLocked"));
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
    public List<SysRole> ListPage(PageUtils pageUtils) {
        QueryRunner queryRunner=MyDbUtils.getQueryRunner();
        String sql = "select * from sys_role limit ?,?";
        if(StringUtils.isNotEmpty(pageUtils.getKey())){
// 需要带条件查询
             sql = "select * from sys_role where name like '%"+pageUtils.getKey()+"%' or notes like '%"+pageUtils.getKey()+"%' limit ?,?";
       }

        //计算 分页开始的页数
        int startNo=pageUtils.getStart();
        try {
            //BeanListHandler 只能映射相同的字段和属性，如果字段和属性名称不同无法映射
            //List<SysUser>List=queryRunner.query(sql,new BeanListHandler<SysUser>(SysUser.class));
            List<SysRole>List=queryRunner.query(sql, new ResultSetHandler<java.util.List<SysRole>>() {
                @Override
                public java.util.List<SysRole> handle(ResultSet resultSet) throws SQLException {
                    List<SysRole>list=new ArrayList<>();
                    while(resultSet.next()){
                        SysRole entity=new SysRole();
                        entity.setId(resultSet.getInt("id"));
                        entity.setName(resultSet.getString("name"));
                        entity.setNotes(resultSet.getString("notes"));
                        entity.setPassword(resultSet.getString("password"));
                        entity.setCreateTime(resultSet.getDate("time"));
                        entity.setLoginErrorCount(resultSet.getInt("login_error_count"));
                        entity.setLateLoginErrorTime(resultSet.getDate("last_login_error_time"));
                        entity.setIsLocked(resultSet.getInt("isLocked"));
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
    public int save(SysRole entity) {
        QueryRunner queryRunner= MyDbUtils.getQueryRunner();
        String sql="insert into sys_role (name,notes,password)values(?,?,?) ";
        try {
           return  queryRunner.update(sql,entity.getName(),entity.getNotes(),entity.getPassword());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return 0;
    }

    @Override
    public SysRole findById(int id) {
        QueryRunner queryRunner= MyDbUtils.getQueryRunner();
        /*
        查询数据库中的数据
         */
        String sql="select * from sys_role where id=?";
        try {
            //BeanListHandler 只能映射相同的字段和属性，如果字段和属性名称不同无法映射
            //List<SysUser>List=queryRunner.query(sql,new BeanListHandler<SysUser>(SysUser.class));
            return queryRunner.query(sql, new ResultSetHandler<SysRole>() {
                @Override
                public SysRole handle(ResultSet resultSet) throws SQLException {
                    if(resultSet.next()){
                        SysRole entity=new SysRole();
                        entity.setId(resultSet.getInt("id"));
                        entity.setName(resultSet.getString("name"));
                        entity.setNotes(resultSet.getString("notes"));
                        if ("超级管理员".equals(resultSet.getString("notes"))) {
                            // 正常显示
                            entity.setPassword(resultSet.getString("password"));
                        } else {
                            entity.setPassword("********");
                        }
                        entity.setCreateTime(resultSet.getDate("time"));

                        entity.setLoginErrorCount(resultSet.getInt("login_error_count"));
                        entity.setLateLoginErrorTime(resultSet.getDate("last_login_error_time"));
                        entity.setIsLocked(resultSet.getInt("isLocked"));
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
    public List<SysRole> findByName(String usename) {
        QueryRunner queryRunner= MyDbUtils.getQueryRunner();
        /*
        查询数据库中的数据
         */
        String sql="select * from sys_role where name=?";
        try {
            //BeanListHandler 只能映射相同的字段和属性，如果字段和属性名称不同无法映射
            //List<SysUser>List=queryRunner.query(sql,new BeanListHandler<SysUser>(SysUser.class));
            return queryRunner.query(sql, new ResultSetHandler< List<SysRole>>() {
                @Override
                public  List<SysRole> handle(ResultSet resultSet) throws SQLException {
                    List<SysRole>list=new ArrayList<>();
                    while(resultSet.next()){
                        SysRole entity=new SysRole();
                        entity.setId(resultSet.getInt("id"));
                        entity.setName(resultSet.getString("name"));
                        entity.setNotes(resultSet.getString("notes"));
                        entity.setPassword(resultSet.getString("password"));
                        entity.setCreateTime(resultSet.getDate("time"));

                        entity.setLoginErrorCount(resultSet.getInt("login_error_count"));
                        entity.setLateLoginErrorTime(resultSet.getDate("last_login_error_time"));
                        entity.setIsLocked(resultSet.getInt("isLocked"));
                       list.add(entity);
                    }
                    return list;
                }
            },usename);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return null;
    }

    @Override
    public int updateById(SysRole entity) {
        QueryRunner queryRunner= MyDbUtils.getQueryRunner();

        if(entity.getPassword().equals("********")){
            //密码如果未修改不更新
            String sql="update sys_role set name= ?,notes=? where id=?";
            try {
                return  queryRunner.update(sql,entity.getName(),entity.getNotes(),entity.getId());
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }

        }else{
            //修改了密码
            String sql="update sys_role set name= ?,notes=?,password=?where id=?";
            try {
                return  queryRunner.update(sql,entity.getName(),entity.getNotes(),entity.getPassword(),entity.getId());
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }

        }

        return 0;
    }

    @Override
    public int updateByName(SysRole entity) {
        QueryRunner queryRunner= MyDbUtils.getQueryRunner();
        String sql="update sys_role set last_login_error_time=?,login_error_count=?,isLocked=? where name=?";
        try {
            return  queryRunner.update(sql,entity.getLateLoginErrorTime(),entity.getLoginErrorCount(),entity.getIsLocked(),entity.getName());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return 0;
    }

    @Override
    public int updatePassword(SysRole entity) {
        QueryRunner queryRunner= MyDbUtils.getQueryRunner();
        String sql="update sys_role set password=? where name=?";
        try {
            return  queryRunner.update(sql,entity.getPassword(),entity.getName());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return 0;
    }

    @Override
    public int deleteById(int id) {
        QueryRunner queryRunner= MyDbUtils.getQueryRunner();
        String sql="delete from sys_role where id=?";
        try {
            return  queryRunner.update(sql,id);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return 0;
    }

    @Override
    public int count(PageUtils pageUtils) {
        QueryRunner queryRunner=MyDbUtils.getQueryRunner();
        String sql="select count(1) from sys_role";
        if(StringUtils.isNotEmpty(pageUtils.getKey())){
            sql="select count(1) from sys_role where name like '%"+pageUtils.getKey()+"%' or  notes like '%"+pageUtils.getKey()+"%'";

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
    public String checkUserName(String userName) {
        QueryRunner queryRunner=MyDbUtils.getQueryRunner();

        String sql="select count(1) from sys_role where name=?";

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
    public void deleteMenuByRoleId(int id) {
        QueryRunner queryRunner= MyDbUtils.getQueryRunner();
        String sql="delete from sys_role_menu where role_id=?";
        try {
            queryRunner.update(sql,id);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public void saveDispatchMenu(int roleId, String menuId) {
        QueryRunner queryRunner= MyDbUtils.getQueryRunner();
        String sql="insert into sys_role_menu(role_id,menu_id)values(?,?)";
        try {
            queryRunner.update(sql,roleId,menuId);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public List<SysRoleMenu> queryByRoleId(int roleId) {
        QueryRunner queryRunner= MyDbUtils.getQueryRunner();
        String sql="select * from sys_role_menu where role_id=?";
        try {
            //BeanListHandler 只能映射相同的字段和属性，如果字段和属性名称不同无法映射
            //List<SysUser>List=queryRunner.query(sql,new BeanListHandler<SysUser>(SysUser.class));
            List<SysRoleMenu>List=queryRunner.query(sql, new ResultSetHandler<java.util.List<SysRoleMenu>>() {
                @Override
                public java.util.List<SysRoleMenu> handle(ResultSet resultSet) throws SQLException {
                    List<SysRoleMenu>list=new ArrayList<>();
                    while(resultSet.next()){
                        SysRoleMenu entity=new SysRoleMenu();
                        entity.setId(resultSet.getInt("id"));
                        entity.setMenuId(resultSet.getInt("menu_id"));
                        entity.setRoleId(resultSet.getInt("role_id"));
                        list.add(entity);
                    }
                    return list;
                }
            },roleId);
            return List;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return null;
    }

    @Override
    public void addCount(String name) {
        QueryRunner queryRunner = MyDbUtils.getQueryRunner(); // queryRunner对象
        String sql = "update sys_role set login_error_count = COALESCE(login_error_count, 0) + ? where name = ?";
        try {
            queryRunner.update(sql, 1, name);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public void delCount(SysRole sysRole) {
        QueryRunner queryRunner = MyDbUtils.getQueryRunner(); // queryRunner对象
        String sql = "update sys_role set login_error_count = 0 where name = ?";
        try {
            queryRunner.update(sql, sysRole.getName());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public void lock(SysRole sysRole) {
        QueryRunner queryRunner = MyDbUtils.getQueryRunner(); // queryRunner对象
        String sql = "update sys_role set isLocked = 1,last_login_error_time = ? where name = ?";

        // 获取当前时间
        Date currentDate = new Date();
        Timestamp currentTimestamp = new Timestamp(currentDate.getTime());

        try {
            queryRunner.update(sql, currentTimestamp, sysRole.getName());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public void unLock(SysRole sysRole) {
        QueryRunner queryRunner = MyDbUtils.getQueryRunner(); // queryRunner对象
        String sql = "update sys_role set isLocked = 0,last_login_error_time = null? where name = ?";

        // 获取当前时间
        Date currentDate = new Date();
        Timestamp currentTimestamp = new Timestamp(currentDate.getTime());

        try {
            queryRunner.update(sql, currentTimestamp, sysRole.getName());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public void deleteByNmae(String name) {
        QueryRunner queryRunner= MyDbUtils.getQueryRunner();
        String sql="delete from sys_role where name=?";
        try {
            queryRunner.update(sql,name);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }


}
