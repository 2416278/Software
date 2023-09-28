package com.hxx.store.servlet;

import com.hxx.sys.bean.SysGoods;
import com.hxx.sys.bean.SysGoodsType;
import com.hxx.sys.service.IGoodsService;
import com.hxx.sys.service.IGoodsTypeService;
import com.hxx.sys.service.impl.IGoodsServiceImpl;
import com.hxx.sys.service.impl.IGoodsTypeServiceImpl;
import com.hxx.sys.servlet.BaseServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name="showServlet",urlPatterns =  {"/store/showServlet"})
public class ShowServlet extends BaseServlet {
    private IGoodsTypeService goodsTypeService=new IGoodsTypeServiceImpl();

    private IGoodsService goodsService=new IGoodsServiceImpl();

    @Override
    public void list(HttpServletRequest req, HttpServletResponse resp)throws Exception {
        super.list(req,resp);//调用父类中方法完成分页数据的处理
        //执行分页查询
        goodsTypeService.ListPage(pageUtils);
        if(pageUtils.getList()!=null && pageUtils.getList().size()>0){
            List<SysGoodsType> sysGoodsTypes = (List<SysGoodsType>) pageUtils.getList();
            List<SysGoods>goods=null;
            for(SysGoodsType sysGoodsType : sysGoodsTypes){
                SysGoods good=new SysGoods();
                good.setTypeId(sysGoodsType.getId());
                goods=goodsService.listByTypeId(good);
                sysGoodsType.setGoods(goods);
            }
        }        //将结果存储在request的作用域中
        req.setAttribute("pageUtils",pageUtils);
        //通过服务端转发方式,再页面跳转展示在页面上
        req.getRequestDispatcher("/user/show/list.jsp").forward(req,resp);
    }

    @Override
    public void saveOrUpdatePage(HttpServletRequest req, HttpServletResponse resp) throws Exception {

    }

    @Override
    public void saveOrUpdate(HttpServletRequest req, HttpServletResponse resp) throws Exception {

    }

    @Override
    public void remove(HttpServletRequest req, HttpServletResponse resp) throws Exception {

    }

    @Override
    public void findById(HttpServletRequest req, HttpServletResponse resp) throws Exception {

    }


}
