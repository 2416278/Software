package com.hxx.sys.servlet;

import com.hxx.sys.bean.SysRole;
import com.hxx.sys.bean.SysUser;
import com.hxx.sys.service.IMenuService;
import com.hxx.sys.service.IRoleService;
import com.hxx.sys.service.IUserService;
import com.hxx.sys.service.impl.IMenuServiceImpl;
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
import java.io.IOException;
import java.util.Properties;

@WebServlet(name="registerServlet",urlPatterns={"/sys/registerServlet"})
public class RegisterServlet extends HttpServlet {

    // 密码长度不少于8位且至少包含大写字母、小写字母、数字和特殊符号中的四种
    public static final String vaildPassword = "^(?![A-Za-z0-9]+$)(?![a-z0-9\\W]+$)(?![A-Za-z\\W]+$)(?![A-Z0-9\\W]+$)[a-zA-Z0-9\\W]{8,}$";
    // 密码长度8-20位且至少包含大写字母、小写字母、数字或特殊符号中的任意三种
    public static final String vaildPpassword2 = "^(?![a-zA-Z]+$)(?![A-Z0-9]+$)(?![A-Z\\W_]+$)(?![a-z0-9]+$)(?![a-z\\W_]+$)(?![0-9\\W_]+$)[a-zA-Z0-9\\W_]{8,20}$";

    private IRoleService service=new IRoleServiceImpl();//添加密码
    private IUserService userService=new IUserServiceImpl();//添加用户名和邮箱

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String name = req.getParameter("name");
        String password = req.getParameter("password");
        String passwordSub=req.getParameter("passwordSub");

        String email=req.getParameter("email");
        String s = service.checkUserName(name);

        if(s.equals("success")){
            //用户不存在
            if(name.length()<5){
                //用户不存在但是字符少于5个
                req.setAttribute("msg","用户名不少于5个字符");
                req.getRequestDispatcher("/register.jsp").forward(req,resp);
            }else if(name.length()>=5&&!password.matches(vaildPassword)){
                //用户字符不少于5个但是密码格式不符合
                req.setAttribute("msg","密码必须是8位以上包含大小写字母、数字、特殊符号");
                req.getRequestDispatcher("/register.jsp").forward(req,resp);

            }else if(name.length()>=5&&password.matches(vaildPassword)&&!password.equals(passwordSub)){
                //密码格式符合但是两次密码不一致
                req.setAttribute("msg","两次密码不一致");
                req.getRequestDispatcher("/register.jsp").forward(req,resp);
            }else{
                if(isVaild(email)==false){
                    req.setAttribute("msg","邮箱无效，请重新输入");
                    req.getRequestDispatcher("/register.jsp").forward(req,resp);
                }else{
                    //所有验证条件都符合则加入两个数据库中
                    SysUser user=new SysUser();
                    SysRole role=new SysRole();
                    user.setUsename(name);
                    user.setEmail(email);
                    role.setName(name);
                    role.setPassword(password);
                    userService.save(user);
                    service.save(role);
                    req.setAttribute("msg","注册成功，请登录！");
                    req.getRequestDispatcher("/login.jsp").forward(req,resp);
                }
            }
        }else{
            //用户存在
            req.getSession().setAttribute("msg","该用户已存在!请登录");
            //req.setAttribute("msg","该用户已存在!请登录");
            req.getRequestDispatcher("/register.jsp").forward(req,resp);
        }


    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);
    }

    protected boolean isVaild(String email){
        boolean isValid = email.matches("^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$");
        if(isValid==false){
            return false;
        }else{
              Boolean flag=sendMail(email, "该邮箱有效，请忽略该条消息");//发送重置信息
            if(flag==false){
                return false;
            }else{
                return true;
            }
        }
    }

    protected  boolean sendMail(String to, String codenews){
        try {
            final Properties props = new Properties();
            props.put("mail.smtp.auth", "true");
            props.put("mail.smtp.host", "smtp.qq.com");

            // 发件人的账号
            props.put("mail.user", "2416273879@qq.com");
            //发件人的密码
            props.put("mail.password", "agumhxenuzbvdiej");

            // 构建授权信息，用于进行SMTP进行身份验证
            Authenticator authenticator = new Authenticator() {
                @Override
                protected PasswordAuthentication getPasswordAuthentication() {
                    // 用户名、密码
                    String userName = props.getProperty("mail.user");
                    String password = props.getProperty("mail.password");
                    return new PasswordAuthentication(userName, password);
                }
            };
            // 使用环境属性和授权信息，创建邮件会话
            Session mailSession = Session.getInstance(props, authenticator);
            // 创建邮件消息
            MimeMessage message = new MimeMessage(mailSession);
            // 设置发件人
            String username = props.getProperty("mail.user");
            InternetAddress form = new InternetAddress(username);
            message.setFrom(form);

            // 设置收件人
            InternetAddress toAddress = new InternetAddress(to);
            message.setRecipient(Message.RecipientType.TO, toAddress);

            // 设置邮件标题
            message.setSubject("邮箱验证");

            // 设置邮件的内容体
            message.setContent(codenews, "text/html;charset=UTF-8");
            // 发送邮件
            Transport.send(message);
            return true;
        }catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }
}
