package com.hxx.store.servlet;


import com.hxx.store.bean.Cart;
import com.hxx.store.service.CartService;
import com.hxx.store.service.impl.CartServiceImpl;
import com.hxx.sys.bean.SysGoods;
import com.hxx.sys.bean.SysRole;
import com.hxx.sys.bean.SysUser;
import com.hxx.sys.service.IGoodsService;
import com.hxx.sys.service.impl.IGoodsServiceImpl;
import com.hxx.sys.servlet.BaseServlet;
import com.hxx.sys.utils.Constant;
import com.hxx.sys.utils.StringUtils;


import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.PrintWriter;


@WebServlet(name="cartServlet",urlPatterns = {"/store/cartServlet"})
public class CartServlet extends BaseServlet {

    private IGoodsService service=new IGoodsServiceImpl();

    private CartService cartService=new CartServiceImpl();


    @Override
    public void list(HttpServletRequest req, HttpServletResponse resp)throws Exception {
        super.list(req,resp);//调用父类中方法完成分页数据的处理
        //执行分页查询

        //获取当前登录的用户信息
        HttpSession session = req.getSession();
        Object obj = session.getAttribute(Constant.LOGIN_USER);
        SysRole user = null;
        if(obj!=null){
            user=(SysRole) obj;

        }
        cartService.ListPage(pageUtils,user);
        //将结果存储在request的作用域中
        req.setAttribute("pageUtils",pageUtils);
        //通过服务端转发方式,再页面跳转展示在页面上
        req.getRequestDispatcher("/user/cart/list.jsp").forward(req,resp);
    }

    @Override
    public void saveOrUpdatePage(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        String id=req.getParameter("id");
        if(StringUtils.isNotEmpty(id)){
            //说明是更新操作,需要回显具体的信息
            Cart cart = new Cart();
            cart.setId(Integer.parseInt(id));

            req.setAttribute("entity",cart);
        }

        //需要进入添加页面
        req.getRequestDispatcher("/user/cart/addOrUpdate.jsp").forward(req,resp);
    }

    @Override
    public void saveOrUpdate(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        //添加数据
        //获取客户端提交的数据表单

        String id=req.getParameter("id");
        String quantity=req.getParameter("quantity");
        //获取当前登录的用户信息
        HttpSession session = req.getSession();
        Object obj = session.getAttribute(Constant.LOGIN_USER);
        SysRole user = null;
        String username=null;
        if(obj!=null){
            user=(SysRole) obj;
            username=user.getName();
        }

        //获取商品信息
        SysGoods goods = service.findById(Integer.parseInt(id));
        Boolean flag=true;//判断输入的数量是否是整数
        for (int i = 0; i < quantity.length(); i++){

            if (!Character.isDigit(quantity.charAt(i))){
                flag= false;   }
        }
        if(goods==null){

            session.setAttribute("msg","该商品编号不存在");
            req.getRequestDispatcher("/user/cart/addOrUpdate.jsp").forward(req,resp);
        }else{
            //找到未支付的已存在在订单信息，要保证在该用户名下
            Cart cartExist=cartService.findNotPayByName(goods.getName(),username);

            if(flag==false){
                session.setAttribute("msg","商品数量不为整数！！请重新输入");
                req.getRequestDispatcher("/user/cart/addOrUpdate.jsp").forward(req,resp);
            }else{
                if(goods.getCount()<Integer.parseInt(quantity)){
                    //输入的商品数量大于库存量
                    req.getSession().setAttribute("msg","购买数量过多，商品库存量不足！！");
                    Cart cart = new Cart();
                    cart.setId(Integer.parseInt(id));
                    req.setAttribute("entity",cart);
                    req.getRequestDispatcher("/user/cart/addOrUpdate.jsp").forward(req,resp);
                }else{
                    if(cartExist==null&&goods.getCount()>0){
                        //有库存且购物车数据库没有记录
                        //新建一个cart类连接两个类信息

                        Cart cart=new Cart();
                        cart.setImg(goods.getImg());
                        cart.setName(goods.getName());
                        cart.setPrice(goods.getSellprice());
                        cart.setAmount(Integer.parseInt(quantity));
                        cart.setUsername(username);
                        //保存数据，调用Service方法实现数据的存储
                        //重定向的查询操作
                        resp.sendRedirect("/store/cartServlet?action="+ Constant.BASE_ACTION_LIST);
                        cartService.save(cart);
                    }else if(cartExist==null&&goods.getCount()==0){

                        session.setAttribute("msg","该商品库存量不够，请重新输入");
                        req.getRequestDispatcher("/user/cart/addOrUpdate.jsp").forward(req,resp);
                    }else{
                        if(cartExist!=null){
                            if (goods.getCount()<cartExist.getAmount()+Integer.parseInt(quantity)) {

                                session.setAttribute("msg","该商品库存量不够，请重新输入购买数量");
                                //重新回显商品编号
                                Cart cart = new Cart();
                                cart.setId(Integer.parseInt(id));
                                req.setAttribute("entity",cart);
                                req.getRequestDispatcher("/user/cart/addOrUpdate.jsp").forward(req,resp);
                            }
                            else{
                                //购物车中存在但是未购买,增加数量
                                //获取当前登录的用户信息


                                cartService.saveAmountByName(Integer.parseInt(quantity),goods.getName(),username);

                                resp.sendRedirect("/store/cartServlet?action="+ Constant.BASE_ACTION_LIST);

                            }
                        }
                        //购物车中有未购买的记录
                    }

                }


            }
        }
    }


    @Override
    public void remove(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        String id = req.getParameter("id");

        int count=cartService.deleteById(Integer.parseInt(id));
        PrintWriter writer = resp.getWriter();
        writer.write(count+"");
        writer.flush();
        writer.close();
    }

    @Override
    public void findById(HttpServletRequest req, HttpServletResponse resp) throws Exception {

    }

    public void saveStateByName(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        String name=req.getParameter("name");
        cartService.saveStateByName(name);



    }

    public void removeByName(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        String name = req.getParameter("name");
        int count=cartService.deleteByName(name);
        PrintWriter writer = resp.getWriter();
        writer.write(count+"");
        writer.flush();
        writer.close();
    }

    public void saveByName(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        String name = req.getParameter("name");
        String amount=req.getParameter("amount");
        //获取当前登录的用户信息
        HttpSession session = req.getSession();
        Object obj = session.getAttribute(Constant.LOGIN_USER);
        SysRole user = null;
        String username=null;
        if(obj!=null){
            user=(SysRole) obj;
            username=user.getName();
        }

        Cart cart=cartService.findNotPayByName(name,username);

        cartService.updateAmount(cart.getId(),amount);


    }

    public void update(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        //添加数据
        //获取客户端提交的数据表单

        String id=req.getParameter("id");
        String amount=req.getParameter("amount");

        Cart cart=new Cart();
        cartService.updateAmount(Integer.parseInt(id),amount);
       //保存数据，调用Service方法实现数据的存储
        //重定向的查询操作
        resp.sendRedirect("/store/cartServlet?action="+ Constant.BASE_ACTION_LIST);



    }

}
