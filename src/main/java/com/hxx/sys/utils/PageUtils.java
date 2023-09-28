package com.hxx.sys.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *分页的工具类
 */
public class PageUtils {

    private String key;//查询的关键字

    private int pageSize=5;//每页显示的数据数

    private int pageNum=1;//从哪页开始显示

    private int totalCount;//总记录数

    private int totalPage;//总页数

    private List<?>list;//分页显示的数据

    private List<String> pageList;//前端分页的页码

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getPageNum() {
        return pageNum;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    /*
     * 获取总的页数
     * */
    public int getTotalPage() {
        //总记录数/每页显示显示的条数
        totalPage = (int)Math.ceil(((double)totalCount) / pageSize);
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public List<?> getList() {
        return list;
    }

    public void setList(List<?> list) {
        this.list = list;
    }
    /*
    * SQL分页中的开始位置
    * */
    public int getStart(){
        return (this.getPageNum()-1)*this.getPageSize();
    }
    /**
     * SQL分页中的结束位置
     * */
    public int getEnd(){
        return this.getPageNum()*this.getPageSize();
    }
    /*
    1
    1 2
    1 2 3
    1 2 3 4
    1 2 3 4 5
    1 2 .. 5 6
    2 3 .. 6 7
    * */
    public List<String> getPageList(){
        pageList=new ArrayList<>();
        //获取总的页数
        totalPage=getTotalPage();
        if(totalPage<7){
            for(int i=1;i<=totalPage;i++){
                pageList.add(i+"");
            }
        }
        else{
            if(pageNum==1||pageNum==2||pageNum==3){
                pageList= Arrays.asList("1","2","3","...",totalPage+"");
            }else {
                pageList = Arrays.asList((pageNum-2)+"",(pageNum-
                        1)+"",pageNum+"","..."+totalPage);
            }
        }

        return pageList;
    }




}
