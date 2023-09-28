package com.hxx.sys.servlet;

import com.alibaba.fastjson.JSONObject;
import com.hxx.sys.bean.SysGoods;
import com.hxx.sys.bean.SysGoodsType;
import com.hxx.sys.service.IGoodsService;
import com.hxx.sys.service.IGoodsTypeService;
import com.hxx.sys.service.impl.IGoodsServiceImpl;
import com.hxx.sys.service.impl.IGoodsTypeServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet(name="indexGoodsServlet",urlPatterns={"/sys/indexGoodsServlet"})
public class IndexGoodsServlet extends BaseServlet {

    private IGoodsTypeService typeService=new IGoodsTypeServiceImpl();

    private IGoodsService goodsService=new IGoodsServiceImpl();

    public void list(HttpServletRequest req, HttpServletResponse resp)throws Exception {
        super.list(req,resp);//调用父类中方法完成分页数据的处理
        //执行分页查询
        typeService.ListPage(pageUtils);
        if(pageUtils.getList()!=null && pageUtils.getList().size()>0){

            List<SysGoodsType> types = (List<SysGoodsType>) pageUtils.getList();
            List<SysGoods>goods=null;
            //需要返回的统计数据
            List<String>typeNames=new ArrayList<>();
            List<Integer>goodsNums=new ArrayList<>();
            if(types!=null&&types.size()>0){
                for(SysGoodsType sysGoodsType : types){
                    //根据类别查询数量

                    SysGoods good=new SysGoods();

                    good.setTypeId(sysGoodsType.getId());

                    goods=goodsService.listByTypeId(good);
                    typeNames.add(sysGoodsType.getName());
                    goodsNums.add(goods.size());

                }
            }
            //返回的数据通过Map统一处理
            Map<String,Object> map=new HashMap<>();
            map.put("typeNames",typeNames);
            map.put("goodsNums",goodsNums);
            resp.setContentType("application/json;charset=utf-8");
            //map转换未json数据
            String json= JSONObject.toJSONString(map);
            PrintWriter writer = resp.getWriter();
            writer.write(json);
            writer.flush();


        }
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
