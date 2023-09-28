package com.hxx.sys.servlet;

import com.hxx.sys.bean.SysMenu;
import com.hxx.sys.bean.SysRole;
import com.hxx.sys.bean.SysUser;
import com.hxx.sys.service.IMenuService;
import com.hxx.sys.service.IRoleService;
import com.hxx.sys.service.IRoleService;
import com.hxx.sys.service.impl.IMenuServiceImpl;
import com.hxx.sys.service.impl.IRoleServiceImpl;
import com.hxx.sys.utils.Constant;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

@WebServlet(name="loginServlet",urlPatterns={"/sys/loginServlet"})
public class LoginServlet extends HttpServlet {

    private IRoleService service=new IRoleServiceImpl();
    private IMenuService menuService=new IMenuServiceImpl();
    private List<String>inputNames=new ArrayList<>();
    private List<String>lockedNames=new ArrayList<>();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //登录验证
        String userName = req.getParameter("userName");
        
        String password = req.getParameter("password");
        //根据账号在数据库查询到的记录
        SysRole user = service.findByName(userName);
        inputNames.add(userName);
        if(inputNames.size()>=2&&!userName.equals(inputNames.get(inputNames.size()-2))){
            SysRole role=new SysRole();
            role.setName(inputNames.get(inputNames.size() - 2));
            service.delCount(role);
        }

        if(userName==null||password==null){
            //没有内容输入
            req.setAttribute("msg","请输入内容");
            req.getRequestDispatcher("/login.jsp").forward(req,resp);
        }else{
            //有内容输入
            if(user==null){
                //账号不存在
                req.setAttribute("msg","登录失败！账号不存在");
                req.getRequestDispatcher("/login.jsp").forward(req,resp);
            }else{
                //账号存在
                if(user.getIsLocked()==1){
                    //该账号锁定中
                    // 获取当前时间
                    Date currentTime = new Date();
                    // 计算时间差（单位：毫秒）
                    long timeDifferenceMillis = user.getCreateTime().getTime() - user.getLateLoginErrorTime().getTime();
                    // 将时间差转换为分钟
                    long minutes = TimeUnit.MILLISECONDS.toMinutes(timeDifferenceMillis);

                    // 检查时间差是否超过30分钟,由于数据库与当前的背景时间相差8小时
                    if (minutes > 30) {
                       user.setLateLoginErrorTime(null);
                       user.setIsLocked(0);
                       service.updateByName(user);
                        req.setAttribute("msg","该账户已解锁请重新输入");
                        req.getRequestDispatcher("/login.jsp").forward(req,resp);
                    } else {

                        req.setAttribute("msg","该账户被锁定请30分钟后重新输入");
                        req.getRequestDispatcher("/login.jsp").forward(req,resp);
                    }
                }else{
                    //账号已经解锁
                    if(user.getPassword().equals(password)){
                        //密码正确
                        //登录成功跳转到主页面
                        //需要判断之前的错误次数并且修改为0
                        user.setLoginErrorCount(0);
                        service.updateByName(user);
                        //记录当前登录的用户
                        HttpSession session = req.getSession();
                        user.setPassword(null);//记录的账号把密码清空，安全考虑
                        session.setAttribute(Constant.LOGIN_USER,user);//记录当前登录的用户
                        //登录成功后需要获取当前登录用户的菜单信息
                        Integer roleId = user.getId();//角色用户的id
                        if(roleId!=null){
                            //查询对应的菜单信息
                            List<SysMenu> menus=menuService.findByRoleId(roleId);
                            session.setAttribute(Constant.LOGIN_MENUS,menus);
                        }
                        resp.sendRedirect("/main.jsp");
                    }else{
                        //密码错误
                        //判断当前最后错误名字是否相同
                        if(inputNames.size()==1){
                            if(user.getLoginErrorCount()<4){
                                //错误次数少于5次
                                //错误未超过5次则加一次错误次数
                                inputNames.add(user.getName());
                                user.setLoginErrorCount(user.getLoginErrorCount()+1);
                                Date currentDate = new Date();
                                Timestamp currentTimestamp = new Timestamp(currentDate.getTime());
                                user.setLateLoginErrorTime(currentTimestamp);
                                service.updateByName(user);
                                req.setAttribute("msg","密码错误请重新输入");
                                req.getRequestDispatcher("/login.jsp").forward(req,resp);
                            }else {
                                //错误次数大于5次锁定账号
                                user.setIsLocked(1);
                                user.setLoginErrorCount(0);
                                Date currentDate = new Date();
                                Timestamp currentTimestamp = new Timestamp(currentDate.getTime());
                                user.setLateLoginErrorTime(currentTimestamp);
                               lockedNames.add(user.getName());
                                //***账户锁定之后需要修改错误次数否则其他账户密码错误仍然会被锁定
                                service.updateByName(user);
                                req.setAttribute("msg","锁定中...");
                                req.getRequestDispatcher("/login.jsp").forward(req,resp);
                            }
                        }else {
                            //错误的名字不相同
                            if(user.getName().equals(inputNames.get(inputNames.size() - 1))){
                                if(user.getLoginErrorCount()<4){
                                    //错误次数少于5次
                                    //错误未超过5次则加一次错误次数
                                    inputNames.add(user.getName());
                                    user.setLoginErrorCount(user.getLoginErrorCount()+1);
                                    Date currentDate = new Date();
                                    Timestamp currentTimestamp = new Timestamp(currentDate.getTime());
                                    user.setLateLoginErrorTime(currentTimestamp);
                                    service.updateByName(user);
                                    req.setAttribute("msg","密码错误请重新输入");
                                    req.getRequestDispatcher("/login.jsp").forward(req,resp);
                                }else {
                                    //错误次数大于5次锁定账号
                                    user.setIsLocked(1);
                                    user.setLoginErrorCount(0);
                                    Date currentDate = new Date();
                                    Timestamp currentTimestamp = new Timestamp(currentDate.getTime());
                                    user.setLateLoginErrorTime(currentTimestamp);
                                    lockedNames.add(user.getName());
                                    //***账户锁定之后需要修改错误次数否则其他账户密码错误仍然会被锁定
                                    service.updateByName(user);
                                    req.setAttribute("msg","锁定中...");
                                    req.getRequestDispatcher("/login.jsp").forward(req,resp);
                                }

                            }else{
                                //不是连续输入
                                System.out.println(inputNames.get(inputNames.size() - 1));
                                SysRole role=new SysRole();
                                role.setName(inputNames.get(inputNames.size() - 1));
                                service.delCount(role);
                                service.addCount(user.getName());
                                req.setAttribute("msg","密码错误请重新输入");
                                req.getRequestDispatcher("/login.jsp").forward(req,resp);
                            }

                        }



                    }
                }

            }

        }



    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);
    }
}
