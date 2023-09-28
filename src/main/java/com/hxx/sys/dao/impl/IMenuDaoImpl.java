package com.hxx.sys.dao.impl;

import com.hxx.sys.bean.SysMenu;
import com.hxx.sys.bean.SysRole;
import com.hxx.sys.dao.IMenuDao;
import com.hxx.sys.utils.MyDbUtils;
import com.hxx.sys.utils.PageUtils;
import com.hxx.sys.utils.StringUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class IMenuDaoImpl implements IMenuDao {
    @Override
    public List<SysMenu> List(SysMenu entity) {
        QueryRunner queryRunner= MyDbUtils.getQueryRunner();
        /*
        查询数据库中的数据
         */
        String sql="select * from sys_menu order by seq asc";
        try {
            //BeanListHandler 只能映射相同的字段和属性，如果字段和属性名称不同无法映射
            //List<SysUser>List=queryRunner.query(sql,new BeanListHandler<SysUser>(SysUser.class));
            List<SysMenu>List=queryRunner.query(sql, new ResultSetHandler<java.util.List<SysMenu>>() {
                @Override
                public java.util.List<SysMenu> handle(ResultSet resultSet) throws SQLException {
                    List<SysMenu>list=new ArrayList<>();
                    while(resultSet.next()){
                        SysMenu entity=new SysMenu();
                        entity.setId(resultSet.getInt("id"));
                        entity.setName(resultSet.getString("name"));
                        entity.setUrl(resultSet.getString("url"));
                        entity.setParentId(resultSet.getInt("parent_Id"));
                        entity.setSeq(resultSet.getInt("seq"));
                        entity.setCreateTime(resultSet.getDate("create_time"));
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
    public List<SysMenu> ListPage(PageUtils pageUtils) {
        QueryRunner queryRunner=MyDbUtils.getQueryRunner();
        String sql = "select * from sys_menu  where parent_id=-1 limit ?,?";
        if(StringUtils.isNotEmpty(pageUtils.getKey())){
            //根据父菜单查询
            sql = "select * from sys_menu where parent_id=-1 and name like '%"+pageUtils.getKey()+"%'  limit ?";
        }

        //计算 分页开始的页数
        int startNo=pageUtils.getStart();
        try {
            //BeanListHandler 只能映射相同的字段和属性，如果字段和属性名称不同无法映射
            //List<SysUser>List=queryRunner.query(sql,new BeanListHandler<SysUser>(SysUser.class));
            List<SysMenu>List=queryRunner.query(sql, new ResultSetHandler<java.util.List<SysMenu>>() {
                @Override
                public java.util.List<SysMenu> handle(ResultSet resultSet) throws SQLException {
                    List<SysMenu>list=new ArrayList<>();
                    while(resultSet.next()){
                        SysMenu entity=new SysMenu();
                        entity.setId(resultSet.getInt("id"));
                        entity.setName(resultSet.getString("name"));
                        entity.setUrl(resultSet.getString("url"));
                        entity.setParentId(resultSet.getInt("parent_Id"));
                        entity.setSeq(resultSet.getInt("seq"));
                        entity.setCreateTime(resultSet.getDate("create_time"));
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
    public int save(SysMenu entity) {
        QueryRunner queryRunner=MyDbUtils.getQueryRunner();
        String sql="insert into sys_menu(name,url,parent_id,seq)value(?,?,?,?)";
        try {
            return queryRunner.update(sql,entity.getName(),entity.getUrl(),entity.getParentId(),entity.getSeq());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return 0;
    }

    @Override
    public SysMenu findById(int id) {
        QueryRunner queryRunner= MyDbUtils.getQueryRunner();
        /*
        查询数据库中的数据
         */
        String sql="select * from sys_menu where id=?";
        try {
            //BeanListHandler 只能映射相同的字段和属性，如果字段和属性名称不同无法映射
            //List<SysUser>List=queryRunner.query(sql,new BeanListHandler<SysUser>(SysUser.class));
            return queryRunner.query(sql, new ResultSetHandler<SysMenu>() {
                @Override
                public SysMenu handle(ResultSet resultSet) throws SQLException {
                    if(resultSet.next()){
                        SysMenu entity=new SysMenu();
                        entity.setId(resultSet.getInt("id"));
                        entity.setName(resultSet.getString("name"));
                        entity.setUrl(resultSet.getString("url"));
                        entity.setParentId(resultSet.getInt("parent_Id"));
                        entity.setSeq(resultSet.getInt("seq"));
                        entity.setCreateTime(resultSet.getDate("create_time"));
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
    public SysMenu findByName(String usename) {
        QueryRunner queryRunner= MyDbUtils.getQueryRunner();
        String sql="select * from sys_role where name=?";
        try {
            //BeanListHandler 只能映射相同的字段和属性，如果字段和属性名称不同无法映射
            //List<SysUser>List=queryRunner.query(sql,new BeanListHandler<SysUser>(SysUser.class));
            return queryRunner.query(sql, new ResultSetHandler<SysMenu>() {
                @Override
                public SysMenu handle(ResultSet resultSet) throws SQLException {
                    if(resultSet.next()){
                        SysMenu entity=new SysMenu();
                        entity.setId(resultSet.getInt("id"));
                        entity.setName(resultSet.getString("name"));
                        entity.setUrl(resultSet.getString("url"));
                        entity.setParentId(resultSet.getInt("parent_Id"));
                        entity.setSeq(resultSet.getInt("seq"));
                        entity.setCreateTime(resultSet.getDate("create_time"));
                        return entity;
                    }
                    return null;
                }
            },usename);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return null;
    }

    @Override
    public int update(SysMenu entity) {
        QueryRunner queryRunner= MyDbUtils.getQueryRunner();
        String sql="update sys_menu set name= ?,url=?,parent_id=?,seq=? where id=?";
        try {
            return  queryRunner.update(sql,entity.getName(),entity.getUrl(),entity.getParentId(),entity.getSeq(),entity.getId());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return 0;
    }

    @Override
    public int deleteById(int id) {

        QueryRunner queryRunner= MyDbUtils.getQueryRunner();
        String sql="delete from sys_menu where id=?";
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
        String sql="select count(1) from sys_menu";
        if(StringUtils.isNotEmpty(pageUtils.getKey())){
            sql="select count(1) from sys_menu where name like '%"+pageUtils.getKey()+"%' or  url like '%"+pageUtils.getKey()+"%'";

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
    public List<SysMenu> getAllParent() {
        QueryRunner queryRunner= MyDbUtils.getQueryRunner();
        String sql="select * from sys_menu where parent_id=-1";
        try {
            //BeanListHandler 只能映射相同的字段和属性，如果字段和属性名称不同无法映射
            //List<SysUser>List=queryRunner.query(sql,new BeanListHandler<SysUser>(SysUser.class));
            List<SysMenu>List=queryRunner.query(sql, new ResultSetHandler<java.util.List<SysMenu>>() {
                @Override
                public java.util.List<SysMenu> handle(ResultSet resultSet) throws SQLException {
                    List<SysMenu>list=new ArrayList<>();
                    while(resultSet.next()){
                        SysMenu entity=new SysMenu();
                        entity.setId(resultSet.getInt("id"));
                        entity.setName(resultSet.getString("name"));
                        entity.setUrl(resultSet.getString("url"));
                        entity.setParentId(resultSet.getInt("parent_Id"));
                        entity.setSeq(resultSet.getInt("seq"));
                        entity.setCreateTime(resultSet.getDate("create_time"));
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
    public boolean isDispatcher(int id) {
        QueryRunner queryRunner=MyDbUtils.getQueryRunner();
        String sql="select count(1) from sys_role_menu where menu_id=?";
        try {
            return queryRunner.query(sql, new ResultSetHandler<Boolean>() {

                @Override
                public Boolean handle(ResultSet rs) throws SQLException {
                    rs.next();
                    int count = rs.getInt(1);

                    return count>0?true:false;
                }
            },id);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean havaSubMenu(int id) {
        QueryRunner queryRunner=MyDbUtils.getQueryRunner();
        String sql="select count(1) from sys_menu where parent_id=?";
        try {
            return queryRunner.query(sql, new ResultSetHandler<Boolean>() {

                @Override
                public Boolean handle(ResultSet rs) throws SQLException {
                    rs.next();
                    int count = rs.getInt(1);

                    return count>0?true:false;
                }
            },id);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;
    }

    @Override
    public List<SysMenu> findByRoleId(Integer roleId) {
        QueryRunner queryRunner= MyDbUtils.getQueryRunner();
        String sql="select * from sys_menu  where id in( select menu_id from sys_role_menu where role_id=?)order by seq asc";
        try {
            //BeanListHandler 只能映射相同的字段和属性，如果字段和属性名称不同无法映射
            //List<SysUser>List=queryRunner.query(sql,new BeanListHandler<SysUser>(SysUser.class));
            List<SysMenu>List=queryRunner.query(sql, new ResultSetHandler<java.util.List<SysMenu>>() {
                @Override
                public java.util.List<SysMenu> handle(ResultSet resultSet) throws SQLException {
                    List<SysMenu>list=new ArrayList<>();
                    while(resultSet.next()){
                        SysMenu entity=new SysMenu();
                        entity.setId(resultSet.getInt("id"));
                        entity.setName(resultSet.getString("name"));
                        entity.setUrl(resultSet.getString("url"));
                        entity.setParentId(resultSet.getInt("parent_Id"));
                        entity.setSeq(resultSet.getInt("seq"));
                        entity.setCreateTime(resultSet.getDate("create_time"));
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
}
