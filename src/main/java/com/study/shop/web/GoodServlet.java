package com.study.shop.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by 傲然 on 2017/2/15.
 */
public class GoodServlet extends BaseServlet {
    @Authority("all")
    public String goodsList(HttpServletRequest req, HttpServletResponse res) {
        //TODO 商品展示
        return "goodsList.jsp";
    }
}
