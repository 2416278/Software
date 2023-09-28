package com.hxx.sys.servlet;

import com.hxx.sys.bean.SysRole;
import com.hxx.sys.bean.SysUser;
import com.hxx.sys.service.IRoleService;
import com.hxx.sys.service.IUserService;
import com.hxx.sys.service.impl.IRoleServiceImpl;
import com.hxx.sys.service.impl.IUserServiceImpl;
import com.hxx.sys.utils.Constant;
import com.hxx.sys.utils.PageUtils;
import com.hxx.sys.utils.StringUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.List;
/**
 * 系统用户的servlet
 * 处理客户端对用户的相关请求
 * 1.需要继承httpServlet
 * 2.重写对应的doGet的doPost方法
 * 3.通过@WebServlet注解定义相关的信息
 * urlPatterns定义客户端提交的请求和映射关系
 */
@WebServlet(name="userServlet",urlPatterns = {"/sys/userServlet"})
public class UserServlet extends BaseServlet{
    private IUserService service=new IUserServiceImpl();

    private IRoleService roleService=new IRoleServiceImpl();

    @Override
    public void list(HttpServletRequest req, HttpServletResponse resp)throws Exception {
        super.list(req,resp);//调用父类中方法完成分页数据的处理
        //执行分页查询
        service.ListPage(pageUtils);
        //将结果存储在request的作用域中
        req.setAttribute("pageUtils",pageUtils);
        //通过服务端转发方式,再页面跳转展示在页面上
        req.getRequestDispatcher("/sys/user/list.jsp").forward(req,resp);
    }

    @Override
    public void saveOrUpdatePage(HttpServletRequest req, HttpServletResponse resp) throws Exception {
    }


    @Override
    public void saveOrUpdate(HttpServletRequest req, HttpServletResponse resp) throws Exception {

    }

    @Override
    public void remove(HttpServletRequest req, HttpServletResponse resp) throws Exception {

        String id = req.getParameter("id");
        Integer uid=Integer.parseInt(id);
        SysUser user=service.findById(uid);
        String name=user.getUsename();
        roleService.deleteByName(name);
        int count=service.deleteById(Integer.parseInt(id));
        roleService.deleteById(Integer.parseInt(id));
        PrintWriter writer = resp.getWriter();
        writer.write(count+"");
        writer.flush();
        writer.close();
    }

    @Override
    public void findById(HttpServletRequest req, HttpServletResponse resp) throws Exception {

    }

}
