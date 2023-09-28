package com.hxx.store.servlet;

import com.google.protobuf.TextFormatParseInfoTree;
import com.hxx.store.bean.Cart;
import com.hxx.store.bean.Order;
import com.hxx.store.service.CartService;
import com.hxx.store.service.OrderService;
import com.hxx.store.service.impl.CartServiceImpl;

import com.hxx.store.service.impl.OrderServiceImpl;
import com.hxx.sys.bean.SysGoods;
import com.hxx.sys.bean.SysRole;
import com.hxx.sys.bean.SysUser;
import com.hxx.sys.service.IGoodsService;
import com.hxx.sys.service.impl.IGoodsServiceImpl;
import com.hxx.sys.servlet.BaseServlet;
import com.hxx.sys.utils.Constant;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import static java.lang.System.exit;

@WebServlet(name="orderServlet",urlPatterns = {"/store/orderServlet"})
public class OrderServlet extends BaseServlet {

    private  IGoodsService goodsService=new IGoodsServiceImpl();

   private CartService cartService=new CartServiceImpl();

   private OrderService orderService=new OrderServiceImpl();



    @Override
    public void list(HttpServletRequest req, HttpServletResponse resp)throws Exception {

        super.list(req,resp);//调用父类中方法完成分页数据的处理

        //获取当前登录的用户信息
        HttpSession session = req.getSession();
        Object obj = session.getAttribute(Constant.LOGIN_USER);
        SysRole user = null;
        if(obj!=null){
            user=(SysRole) obj;
            String notes = user.getNotes();
            if(notes.contains("超级管理员")){
                //当前登录用户是
                user.setIsAdmin(true);
            }else{
                //
                user.setIsAdmin(false);
            }

        }
        //执行分页查询
        orderService.ListPage(pageUtils,user);
        //将结果存储在request的作用域中
        req.setAttribute("pageUtils",pageUtils);
        //通过服务端转发方式,再页面跳转展示在页面上
        req.getRequestDispatcher("/user/order/list.jsp").forward(req,resp);
    }


    @Override
    public void saveOrUpdatePage(HttpServletRequest req, HttpServletResponse resp) throws Exception {

    }

    @Override
    public void saveOrUpdate(HttpServletRequest req, HttpServletResponse resp) throws Exception {



    }


    public void saveByName(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        String name=req.getParameter("name");//获取商品的名称


        Order order=new Order();

        //获取当前登录的用户信息
        HttpSession session = req.getSession();
        Object obj = session.getAttribute(Constant.LOGIN_USER);
        SysRole user = null;
        String userName=null;
        if(obj!=null){
            user=(SysRole) obj;
            userName= user.getName();
        }
        //获取商品名称，商品数量信息
        Cart cart=cartService.findNotPayByName(name,userName);
        if(cart==null){
            return;
        }else{
            //添加到订单数据库
            order.setImg(cart.getImg());
            order.setGoodsName(cart.getName());
            order.setTotal(cart.getPrice()*cart.getAmount());
            order.setAmount(cart.getAmount());
            order.setName(userName);
            orderService.saveName(order);
        }


        //保存数据，调用Service方法实现数据的存储
        //重定向的查询操作
        //修改该商品的支付状态
        cartService.saveStateById(cart.getId());
        //修改库存量
        SysGoods good=goodsService.findByName(cart.getName());

        goodsService.updateAmountByName(cart.getAmount(),cart.getName());

    }


    @Override
    public void remove(HttpServletRequest req, HttpServletResponse resp) throws Exception {

    }

    @Override
    public void findById(HttpServletRequest req, HttpServletResponse resp) throws Exception {

    }


}