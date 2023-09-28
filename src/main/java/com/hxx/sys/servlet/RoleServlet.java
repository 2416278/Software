package com.hxx.sys.servlet;

import com.hxx.sys.bean.SysMenu;
import com.hxx.sys.bean.SysRole;
import com.hxx.sys.bean.SysRoleMenu;
import com.hxx.sys.bean.SysUser;
import com.hxx.sys.service.IMenuService;
import com.hxx.sys.service.IRoleService;
import com.hxx.sys.service.IUserService;
import com.hxx.sys.service.impl.IMenuServiceImpl;
import com.hxx.sys.service.impl.IRoleServiceImpl;
import com.hxx.sys.service.impl.IUserServiceImpl;
import com.hxx.sys.utils.Constant;
import com.hxx.sys.utils.StringUtils;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@WebServlet(name="roleServlet",urlPatterns = {"/sys/roleServlet"})
public class RoleServlet extends BaseServlet{

    private IRoleService service=new IRoleServiceImpl();
    private IUserService userService=new IUserServiceImpl();
    private IMenuService menuService=new IMenuServiceImpl();
    public void list(HttpServletRequest req, HttpServletResponse resp)throws Exception {
        super.list(req,resp);//调用父类中方法完成分页数据的处理
        //查询处理
        service.ListPage(pageUtils);
        req.setAttribute(Constant.LIST_PAGE_UTILS,pageUtils);
        //页面跳转
        req.getRequestDispatcher("/sys/role/list.jsp").forward(req,resp);
    }

    @Override
    public void saveOrUpdatePage(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        //如果更新操作，需要查询出当前的信息
        String id = req.getParameter("id");

        //查询出所有的菜单信息
        List<SysMenu> menus = menuService.list(null);
        req.setAttribute("menus",menus);

        if(StringUtils.isNotEmpty(id)){
            int roleId=Integer.parseInt(id);
            List<SysRoleMenu> roleMenus=service.queryByRoleId(roleId);
            if(roleMenus!=null&&roleMenus.size()>0){
                List<Integer> ownerMenus = roleMenus.stream().map(item -> item.getMenuId()).collect(Collectors.toList());
                for (SysMenu menu : menus) {
                    if(ownerMenus.contains(menu.getId())){
                        menu.setCheck(true);
                    }
                }
            }

            //修改
            SysRole entity = service.findById(Integer.parseInt(id));
            req.setAttribute("entity",entity);

        }
        //根据当前的角色编号 查询出相应已分配的菜单信息

        //进入表单获取数据
        req.getRequestDispatcher("/sys/role/addOrUpdate.jsp").forward(req,resp);


    }


    @Override
    public void saveOrUpdate(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        //获取提交的数据
        //添加数据
        //获取客户端提交的数据表单

        String id=req.getParameter("id");
        String name=req.getParameter("name");
        SysRole role=service.findById(Integer.parseInt(id));
        String password=req.getParameter("password");

        SysRole entity=new SysRole();

        entity.setName(name);
        entity.setPassword(password);
        entity.setNotes(role.getNotes());

        String[] menuIds = req.getParameterValues("menuId");
        int roleId=Integer.parseInt(id);
        //表示是修改密码或者重置密码
        entity.setId(Integer.parseInt(id));
        //调用更新方法
        service.updateById(entity);
        //如果修改了级别同样用户管理也要修改

        //更新分配的菜单
        //1.删除当前角色已分配的菜单
        service.deleteMenuByRoleId(Integer.parseInt(id));
        //2.插入新分配的菜单
        if(menuIds !=null&& menuIds.length>0){
            for (String menuId : menuIds) {
                service.saveDispatchMenu(roleId,menuId);
            }
        }
        //保存数据，调用Service方法实现数据的存储
        //重定向的查询操作
        resp.sendRedirect("/sys/roleServlet?action=list");


    }

    @Override
    public void remove(HttpServletRequest req, HttpServletResponse resp) throws Exception {
       //删除信息
        String id = req.getParameter("id");
        //一个用户一个角色
        PrintWriter writer = resp.getWriter();
        service.deleteById(Integer.parseInt(id));
        writer.write("ok");
        writer.flush();
        writer.close();

    }

    @Override
    public void findById(HttpServletRequest req, HttpServletResponse resp) throws Exception {

    }



}
