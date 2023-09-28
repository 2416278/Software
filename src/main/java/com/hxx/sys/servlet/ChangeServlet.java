package com.hxx.sys.servlet;
/**
 * 处理修改密码
 */

import com.hxx.sys.bean.SysUser;
import com.hxx.sys.service.IRoleService;
import com.hxx.sys.service.IUserService;
import com.hxx.sys.service.impl.IRoleServiceImpl;
import com.hxx.sys.service.impl.IUserServiceImpl;
import com.hxx.sys.utils.StringUtils;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.hxx.sys.bean.SysRole;
import com.hxx.sys.bean.SysUser;
import com.hxx.sys.service.IRoleService;
import com.hxx.sys.service.IUserService;
import com.hxx.sys.service.impl.IRoleServiceImpl;
import com.hxx.sys.service.impl.IUserServiceImpl;
import com.hxx.sys.utils.Constant;
import com.hxx.sys.utils.StringUtils;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Properties;
import java.util.Random;

@WebServlet(name="changeServlet",urlPatterns={"/sys/changeServlet"})
public class ChangeServlet extends BaseServlet{

    // 密码长度不少于8位且至少包含大写字母、小写字母、数字和特殊符号中的四种
    public static final String vaildPassword = "^(?![A-Za-z0-9]+$)(?![a-z0-9\\W]+$)(?![A-Za-z\\W]+$)(?![A-Z0-9\\W]+$)[a-zA-Z0-9\\W]{8,}$";

    private IRoleService service=new IRoleServiceImpl();//更新生成的密码
    private IUserService userService=new IUserServiceImpl();//获得用户名和邮箱

    @Override
    public void saveOrUpdatePage(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        String name=req.getParameter("name");
        if(StringUtils.isNotEmpty(name)){
            //说明是更新操作,需要回显具体的信息
            SysRole user=service.findByName(name);
          //entity实体
            req.setAttribute("entity",user);
        }
        req.getRequestDispatcher("/sys/password/change.jsp").forward(req,resp);
    }

    @Override
    public void saveOrUpdate(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        String name=req.getParameter("name");
        String password=req.getParameter("password");
        HttpSession session = req.getSession();
        SysRole user=service.findByName(name);
        if(password.matches(vaildPassword)){
            SysRole sysRole=new SysRole();
            sysRole.setName(name);
            sysRole.setPassword(password);
            service.updatePassword(sysRole);
            session.setAttribute("msg","修改密码成功");
            resp.sendRedirect("/sys/password/change.jsp");
        }else{
            session.setAttribute("msg","密码格式不符合要求！！请重新输入");
            req.setAttribute("entity",user);
            req.getRequestDispatcher("/sys/password/change.jsp").forward(req,resp);
        }

    }

    @Override
    public void remove(HttpServletRequest req, HttpServletResponse resp) throws Exception {

    }

    @Override
    public void findById(HttpServletRequest req, HttpServletResponse resp) throws Exception {

    }

}
