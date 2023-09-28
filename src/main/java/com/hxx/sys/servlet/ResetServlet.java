package com.hxx.sys.servlet;

import com.hxx.store.bean.Cart;
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

@WebServlet(name="resetServlet",urlPatterns={"/sys/resetServlet"})
public class ResetServlet extends BaseServlet {

    // 密码长度不少于8位且至少包含大写字母、小写字母、数字和特殊符号中的四种
    public static final String vaildPassword = "^(?![A-Za-z0-9]+$)(?![a-z0-9\\W]+$)(?![A-Za-z\\W]+$)(?![A-Z0-9\\W]+$)[a-zA-Z0-9\\W]{8,}$";
    // 密码长度8-20位且至少包含大写字母、小写字母、数字或特殊符号中的任意三种
    public static final String vaildPpassword2 = "^(?![a-zA-Z]+$)(?![A-Z0-9]+$)(?![A-Z\\W_]+$)(?![a-z0-9]+$)(?![a-z\\W_]+$)(?![0-9\\W_]+$)[a-zA-Z0-9\\W_]{8,20}$";

    private IRoleService service=new IRoleServiceImpl();//设置为随机生成的密码
    private IUserService userService=new IUserServiceImpl();//获得用户名和邮箱



    @Override
    public void saveOrUpdatePage(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        String name=req.getParameter("name");

        if(StringUtils.isNotEmpty(name)){
            SysUser user=new SysUser();
            user.setUsename(name);
            //回显具体的信息
            req.setAttribute("entity",user);
        }
        req.getRequestDispatcher("/sys/password/reset.jsp").forward(req,resp);
    }

    @Override
    public void saveOrUpdate(HttpServletRequest req, HttpServletResponse resp) throws Exception {


        String name=req.getParameter("name");
        String email=req.getParameter("email");
        boolean isValid = email.matches("[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}");//邮箱是否格式有效
        String password=randomPassword();//生成随机密码
        SysUser resetUser = userService.findByName(name);

        if(isValid){
            //填写的邮箱有效
            if(!resetUser.getEmail().equals(email)){
                HttpSession session = req.getSession();
                if(StringUtils.isNotEmpty(name)){
                    SysUser user=new SysUser();
                    user.setUsename(name);
                    //回显具体的信息
                    req.setAttribute("entity",user);
                }
                session.setAttribute("msg","请输入注册使用的邮箱");
                req.getRequestDispatcher("/sys/password/reset.jsp").forward(req,resp);
            }else{
                //该邮箱是注册使用的邮箱
                //更新数据库同时修改成功
                Boolean flag=sendMail(email, "【校园购物系统】账号：" +name+ "随机密码为：" +password
                        +"仅用于用户账号密码重置，请勿泄露和转发。如非本人操作，请忽略此短信。");//发送重置信息
                SysRole sysRole=new SysRole();
                sysRole.setName(name);
                sysRole.setPassword(password);
                service.updatePassword(sysRole);
                HttpSession session = req.getSession();
                session.setAttribute("msg","重置成功!!");
                req.getRequestDispatcher("/sys/password/reset.jsp").forward(req,resp);
            }
        }else{
            //填写的邮箱无效
            HttpSession session = req.getSession();
            session.setAttribute("msg","该邮箱无效请重新输入");
            SysUser user=new SysUser();
            user.setUsename(name);
            session.setAttribute("entity",user);
            req.getRequestDispatcher("/sys/password/reset.jsp").forward(req,resp);
        }

    }
    public void forgetPage(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        req.getRequestDispatcher("/sys/password/forget.jsp").forward(req,resp);
    }
    public void forget(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        String name=req.getParameter("name");
        String email=req.getParameter("email");
        boolean isValid = email.matches("[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}");//邮箱是否格式有效
        String password=randomPassword();//生成随机密码
        String s = service.checkUserName(name);
        SysUser resetUser = userService.findByName(name);

        if(s.equals("error")){
            //该用户存在
            if(name.length()<5){
                //用户存在但是字符少于5个
                HttpSession session = req.getSession();
                session.setAttribute("msg1","错误的用户名");
                req.getRequestDispatcher("/sys/password/forget.jsp").forward(req,resp);
            }else if(name.length()>=5){
                if(isValid){
                    //填写的邮箱有效
                    if(!resetUser.getEmail().equals(email)){

                        HttpSession session = req.getSession();
                        session.setAttribute("msg1","请输入注册使用的邮箱");
                        req.getRequestDispatcher("/sys/password/forget.jsp").forward(req,resp);
                    }else{
                        //该邮箱是注册使用的邮箱
                        //更新数据库同时修改成功
                        Boolean flag=sendMail(email, "【校园购物系统】账号：" +name+ "随机密码为：" +password
                                +"仅用于用户账号密码重置，请勿泄露和转发。如非本人操作，请忽略此邮件。");//发送重置信息
                        SysRole sysRole=new SysRole();
                        sysRole.setName(name);
                        sysRole.setPassword(password);
                        service.updatePassword(sysRole);
                        HttpSession session = req.getSession();
                        session.setAttribute("msg","已发送密码！！请登录");
                        req.getRequestDispatcher("/login.jsp").forward(req,resp);
                        session.invalidate();//注销


                    }
                }else{
                    //填写的邮箱无效
                    HttpSession session = req.getSession();
                    session.setAttribute("msg1","该邮箱无效请重新输入");
                    req.getRequestDispatcher("/sys/password/forget.jsp").forward(req,resp);
                }


            }
        }else{

            req.setAttribute("msg1","该用户未注册!请注册");
            req.getRequestDispatcher("/sys/password/forget.jsp").forward(req,resp);
        }

    }

    @Override
    public void remove(HttpServletRequest req, HttpServletResponse resp) throws Exception {

    }

    @Override
    public void findById(HttpServletRequest req, HttpServletResponse resp) throws Exception {

    }


    /**
     *
     * @param to 收件人邮箱
     * @param codenews 邮件内容信息
     */
    /* 发送验证信息的邮件 */
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
            message.setSubject("重置密码");

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
    /* 生成随机密码 */
    protected String randomPassword(){
        String password = "12345678";
        int[] pwdindex = {0,1,2,3,4,5,6,7};
        char[] specialCharacters = {'@','#','.','!','$'};
        char[] numbers = { '0','1','2','3','4','5','6','7','8','9'};
        char[] upperLetters = {'A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z'};
        char[] lowerLetters = {'a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z'};
        //1. 随机生成特殊字符，随机放到密码2-7位置中(特殊字符不出现在开头或结尾)
        int aindex = new Random().nextInt(5);
        int aposition = new Random().nextInt(6)+1;
        password = password.replace(password.charAt(aposition), specialCharacters[aindex]);

        //2. 随机生成数据，随机放到1-8位置中（除去第1步占用的位置）
        int bindex = new Random().nextInt(10);
        int bposition = 0;
        do{
            bposition = new Random().nextInt(8);
        }while(bposition==aposition);
        password = password.replace(password.charAt(bposition), numbers[bindex]);

        //3. 随机生成大写字母，随机放到1-8位置中（除去第1、2步占用的位置）
        int cindex = new Random().nextInt(26);
        int cposition = 0;
        do{
            cposition = new Random().nextInt(8);
        }while(cposition==aposition||cposition==bposition);
        password = password.replace(password.charAt(cposition), upperLetters[cindex]);

        //4. 随机生成小写字母，随机放到1-8位置中（除去第1、2、3步占用的位置）
        int dindex = new Random().nextInt(26);
        int dposition = 0;
        do{
            dposition = new Random().nextInt(8);
        }while(dposition==aposition||dposition==cposition||dposition==bposition);
        password = password.replace(password.charAt(dposition), lowerLetters[dindex]);

        return password;
    }


}
