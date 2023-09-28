package com.hxx.sys.servlet;

import com.hxx.sys.bean.SysMenu;
import com.hxx.sys.service.IMenuService;
import com.hxx.sys.service.impl.IMenuServiceImpl;
import com.hxx.sys.utils.Constant;
import com.hxx.sys.utils.StringUtils;
import com.sun.xml.internal.bind.v2.model.core.ID;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(name="menuServlet",urlPatterns = {"/sys/menuServlet"})

public class MenuServlet extends BaseServlet{

    private IMenuService service=new IMenuServiceImpl();
    /***
     * 根据副菜单动态展示
     * @param req
     * @param resp
     * @throws Exception
     */
    public void list(HttpServletRequest req, HttpServletResponse resp)throws Exception {

        List<SysMenu> list = service.list(null);
        req.setAttribute("list",list);
        req.getRequestDispatcher("/sys/menu/list.jsp").forward(req,resp);

    }


    @Override
    public void saveOrUpdatePage(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        //查询所用父菜单parent_id=-1
        List<SysMenu>list=service.getAllParent();
        req.setAttribute("parents",list);
        //判断是否为更新操作
        String id = req.getParameter("id");
        if(StringUtils.isNotEmpty(id)){
            SysMenu entity = service.findById(Integer.parseInt(id));
            req.setAttribute("entity",entity);
        }
        req.getRequestDispatcher("/sys/menu/addOrUpdate.jsp").forward(req,resp);
    }

    @Override
    public void saveOrUpdate(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        // 获取表单提交的数据
        String id = req.getParameter("id");
        String name = req.getParameter("name");
        String url = req.getParameter("url");
        String seq = req.getParameter("seq");
        String parentId = req.getParameter("parentId");
        SysMenu menu = new SysMenu();
        menu.setName(name);
        menu.setUrl(url);
        //获取分配的菜单

        if(StringUtils.isNotEmpty(seq)){
            menu.setSeq(Integer.parseInt(seq));
        }
        if(StringUtils.isNotEmpty(parentId)){
            menu.setParentId(Integer.parseInt(parentId));
        }
        if(StringUtils.isNotEmpty(id)){
// 更新
            menu.setId(Integer.parseInt(id));
            service.update(menu);
        }else{
// 添加
            service.save(menu);
            //保存分配的菜单信息 获取id
        }
        resp.sendRedirect("/sys/menuServlet?action=list");
    }

    @Override
    public void remove(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        int id =Integer.parseInt(req.getParameter("id"));
        //菜单分配给角色则不能删除
        boolean flag=service.isDispatcher(id);
        String msg="ok";
        if(flag){
            msg="error";
        } else{
            //有子菜单的父菜单不能被删除
            SysMenu entity = service.findById(id);
            if (entity.getParentId()==-1) {
                //判断该父菜单有没有子菜单
                flag=service.haveSubMenu(id);
                if(flag){
                    //有子菜单
                    msg="error";
                }else{
                    //父菜单没有子菜单
                    service.deleteById(id);
                }

            }else{
                //该子菜单可以删除
                service.deleteById(id);
            }
        }
        PrintWriter writer = resp.getWriter();
        writer.write(msg);
        writer.flush();

    }

    @Override
    public void findById(HttpServletRequest req, HttpServletResponse resp) throws Exception {

    }

}
