package com.hxx.sys.filter;

import com.hxx.sys.utils.Constant;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.xml.ws.WebFault;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

/***
 * 认证过滤器，认证所有的请求
 * 判断当前是否时登录状态
 * 请求的资源是否可以匿名访问
 * 都不满足则跳转到登录页面
 */
@WebFilter(filterName = "authName",urlPatterns ={"/*"})
public class AuthenticationFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {


    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        //对封装请求和响应的对象做向下转型
        HttpServletRequest request=(HttpServletRequest) servletRequest;
        HttpServletResponse response=(HttpServletResponse)servletResponse;
        //获取拦截的请求的访问地址
        String requestURI = request.getRequestURI();
        if(checkAccessible(requestURI)){
            //在没有登录的情况下可以访问的资源：登陆页面 处理登录逻辑的servlet，以及静态页面
            //放过请求
            filterChain.doFilter(servletRequest,servletResponse);
        }else{
            //访问的是需要登录的状态才能访问资源
            HttpSession session=request.getSession();
            Object loginUser = session.getAttribute(Constant.LOGIN_USER);
            if(loginUser!=null){
                //是登录状态
                filterChain.doFilter(servletRequest,servletResponse);
            }else{
                //不是登陆状态跳转到登录页面
                request.setAttribute("msg","请先登录！！！");
                request.getRequestDispatcher("/login.jsp").forward(request,response);
            }
        }


    }

    private boolean checkAccessible(String requestURI){
        List<String> urls= Arrays.asList("login.jsp","register.jsp","emailCheck.jsp","/sys/password/forget.jsp","registerServlet","emailCheckServlet","loginServlet","/sys/resetServlet","/css/",".css","/js/",".png",".jpg");
        for (String url : urls) {
            if(requestURI.contains(url)){
                return true;

            }
        }
        return false;
    }

    @Override
    public void destroy() {

    }
}
