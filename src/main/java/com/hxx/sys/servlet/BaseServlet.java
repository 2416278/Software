package com.hxx.sys.servlet;

import com.hxx.sys.utils.PageUtils;
import com.hxx.sys.utils.StringUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.lang.reflect.Method;

/**
 * 系统公共的Servlet
 * 规定客户端提交的请求action名称即为处理请求所调用的同名方法
 */
public abstract class BaseServlet extends HttpServlet {
    protected PageUtils pageUtils;

    /* 通过反射的方式处理请求 */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 获取传递的 action 参数
        String action = req.getParameter("action");
        try {
            // 获取当前对象对应的 method 对象
            Method method = this.getClass().getDeclaredMethod(action, HttpServletRequest.class, HttpServletResponse.class);
            // 调用方法
            method.invoke(this, req, resp);
        } catch (Exception e) {
            // 调用方法操作时出现了异常
            e.printStackTrace();


        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    // 定义增删改查的基础方法
    public void list(HttpServletRequest req, HttpServletResponse resp) throws Exception{
        pageUtils=new PageUtils();
        //查询分页相关的参数
        String pn = req.getParameter("pageNum");
        String ps = req.getParameter("pageSize");
        String key = req.getParameter("key");
        //声明默认的分页参数
        int pageSize=5;//固定每页显示5条
        int pageNum=1;//默认显示第一页
        if(StringUtils.isNotEmpty(ps)){
            pageSize=Integer.parseInt(ps);
        }
        if(StringUtils.isNotEmpty(pn)){
            pageNum=Integer.parseInt(pn);
        }
        if(StringUtils.isNotEmpty(key)){
            // 如果查询条件不为空。那么设置当前页为1
            pageNum = 1;
        }

        pageUtils.setPageNum(pageNum);
        pageUtils.setPageSize(pageSize);
        pageUtils.setKey(key);
    };

    public abstract void saveOrUpdatePage(HttpServletRequest req, HttpServletResponse resp) throws Exception;
    public abstract void saveOrUpdate(HttpServletRequest req, HttpServletResponse resp) throws Exception;
    public abstract void remove(HttpServletRequest req, HttpServletResponse resp) throws Exception;
    public abstract void findById(HttpServletRequest req, HttpServletResponse resp) throws Exception;


}