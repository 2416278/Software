package com.hxx.sys.servlet;

import com.hxx.sys.utils.Constant;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.*;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.List;

/**
 * 文件上传
 * urlPatterns定义客户端提交的请求和映射关系
 */
@WebServlet(name="uploadServlet",urlPatterns = {"/sys/uploadServlet"})
public class UploadServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //上传文件信息
        String uploadPath= Constant.UPLOAD_DIRECTORY;
        File uploadDir=new File(uploadPath);
        if(!uploadDir.exists()){
            //目录不存在
            uploadDir.mkdir();
        }
        //文件上传处理
        DiskFileItemFactory factory=new DiskFileItemFactory();
        ServletFileUpload upload=new ServletFileUpload(factory);

        try{
            //servletFileUpload对上传的请求解析
            //fileItems客户端提交的各个表单域的内容
            List<FileItem> fileItems = upload.parseRequest(req);
            if(fileItems!=null&&fileItems.size()>0){
                for (FileItem fileItem : fileItems) {
                    //判断当前的信息是否为文件
                    if(!fileItem.isFormField()){
                        //表示获取的是上传的文件信息
                        String fileName=new File(fileItem.getName()).getName();
                        //新上传的文件需要生成新的文件名称
                        fileName=new Date().getTime()+fileName.substring(fileName.lastIndexOf("."));
                        //拼接的文件上传的路径
                        String filePath=uploadPath+File.separator+fileName;
                        System.out.println(filePath+"_____");
                        //把文件存储到磁盘中
                        fileItem.write(new File(filePath));
                        //生成的文件名返回到客户端
                        PrintWriter writer=resp.getWriter();
                        writer.write(fileName);
                        writer.flush();
                        writer.close();
                    }
                }
            }
        }catch(Exception e){
            e.printStackTrace();
        }



    }
}
