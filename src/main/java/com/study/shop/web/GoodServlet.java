package com.study.shop.web;

import com.study.shop.dao.CategoryDao;
import com.study.shop.dao.GoodDao;
import com.study.shop.dao.ICategoryDao;
import com.study.shop.dao.IGoodDao;
import com.study.shop.model.*;
import com.study.shop.util.RequestUtil;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Created by 傲然 on 2017/2/15.
 */
public class GoodServlet extends BaseServlet {
    IGoodDao goodDao;
    ICategoryDao categoryDao;

    @DaoInject
    public void setGoodDao(GoodDao goodDao) {
        this.goodDao = goodDao;
    }

    @DaoInject
    public void setCategoryDao(CategoryDao categoryDao) {
        this.categoryDao = categoryDao;
    }

    @Authority("all")
    public String goodsPage(HttpServletRequest req, HttpServletResponse res) {
        //TODO 商品展示
        return "goodsPage.jsp";
    }

    public String goodsList(HttpServletRequest req, HttpServletResponse res) {
        Pager<Good> goodPager = goodDao.find(0, -1, "");
        req.setAttribute("goodPager",goodPager);
        System.out.println(goodPager.getDatas().size());
        return "goodsList.jsp";
    }

    public String addGoodPage(HttpServletRequest req, HttpServletResponse res) {
        List<Category> categoryList = categoryDao.list("");
        req.setAttribute("categoryList", categoryList);
        return "addGood.jsp";
    }

    public String addGood(HttpServletRequest req, HttpServletResponse res) {
        List<Category> categoryList = categoryDao.list("");
        req.setAttribute("categoryList", categoryList);
        if (!RequestUtil.validate(Good.class, req)) {
            return "addGood.jsp";
        }
        String categoryId = req.getParameter("categoryId");
        Good good = (Good) RequestUtil.setParams(Good.class, req);
        if (categoryId != null) {
            try {
                good.setStatus(1);
                goodDao.add(Integer.parseInt(categoryId), good);
            } catch (ShopException se) {
                req.setAttribute("e",se.getMessage());
                return "addGood.jsp";
            }
        }
        return BaseServlet.redirect + "good.do?method=goodsList";
    }
}
