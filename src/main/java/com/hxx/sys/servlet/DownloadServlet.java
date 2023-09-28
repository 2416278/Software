package com.hxx.sys.servlet;

import com.hxx.sys.utils.Constant;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;

/**
 * 文件下载
 * urlPatterns定义客户端提交的请求和映射关系
 */
@WebServlet(name="downloadServlet",urlPatterns = {"/sys/downloadServlet"})
public class DownloadServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
       //获取文件下载的文件名称
        String fileName = req.getParameter("fileName");
        //对应的文件存储的基本路径
        String basePath= Constant.UPLOAD_DIRECTORY;
        //文件下载的操作
        InputStream in=new FileInputStream(basePath+ File.separator+fileName);
        //获取需要下载的文件长度
        int size=in.available();
        byte data[]=new byte[size];
        in.read(data);//读取数据到字节数组中
        in.close();//关闭输入流
        //相应的信息需要让浏览器直到我们返回的是什么信息
        if(fileName.contains(".jpg")||fileName.contains(".png")){
            //下载的图片
            resp.setContentType("image/jpg");
        }else{
            //下载的文件
            resp.setCharacterEncoding("utf-8");
            resp.setContentType("multipart/form-data");
            resp.setHeader("Content-Disposition", "attachment;filename="+ fileName
                    +";filename*=utf-8''"+ URLEncoder.encode(fileName,"UTF-8"));
        }
        //输出下载的文件
        ServletOutputStream outputStream = resp.getOutputStream();
        outputStream.write(data);
        outputStream.flush();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req,resp);
    }
}
