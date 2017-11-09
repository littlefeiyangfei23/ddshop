package com.qf.ddshop.common.dto;

import java.util.ArrayList;
import java.util.List;

/**
 * easyui的datagrid排序通用类
 */
public class Order {

    private String sort;
    private String order;


    //最终拿到  title,price  这种格式的数据。
    public List<String> getOrderParams() {
//        spilt用来分割，用逗号进行分割,Ctrl+Alt+V,补全
        String[] sorts = this.sort.split(",");//title，price
        String[] orders = this.order.split(",");//asc,desc
        List<String> list = new ArrayList<String>();
        for (int i=0;i<sorts.length;i++){
            String temp = sorts[i] +" "+orders[i];//title asc;price desc
            list.add(temp);//[title asc;price desc]
        }
        return list;
    }



    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }

    public String getOrder() {
        return order;
    }

    public void setOrder(String order) {
        this.order = order;
    }
}
