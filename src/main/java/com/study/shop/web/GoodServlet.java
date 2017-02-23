package com.study.shop.web;

import com.study.shop.dao.CategoryDao;
import com.study.shop.dao.GoodDao;
import com.study.shop.dao.ICategoryDao;
import com.study.shop.dao.IGoodDao;
import com.study.shop.model.*;
import com.study.shop.util.RequestUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
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
        req.setAttribute("goodPager", goodPager);
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
                req.setAttribute("e", se.getMessage());
                return "addGood.jsp";
            }
        }
        return BaseServlet.redirect + "good.do?method=goodsList";
    }

    @Authority("all")
    public String showGoodPage(HttpServletRequest req, HttpServletResponse res) {
        String id = req.getParameter("id");
        if (id != null) {
            Good good = goodDao.loadById(Integer.parseInt(id));
            Category category = good.getCategory();
            req.setAttribute("good", good);
            req.setAttribute("category", category);
        }
        return "showGood.jsp";
    }

    public String deleteGood(HttpServletRequest req, HttpServletResponse res) {
        //TODO 在售商品和拥有订单的商品不能删除
        String id = req.getParameter("id");
        if (id != null) {
            goodDao.delete(Integer.parseInt(id));
        }
        return BaseServlet.redirect + "good.do?method=goodsList";
    }

    public String updateGoodPage(HttpServletRequest req, HttpServletResponse res) {
        String id = req.getParameter("id");
        List<Category> categoryList = categoryDao.list("");
        req.setAttribute("categoryList", categoryList);
        if (id != null) {
            Good good = goodDao.loadById(Integer.parseInt(id));
            req.setAttribute("good", good);
        }
        return "updateGood.jsp";
    }

    public String updateGood(HttpServletRequest req, HttpServletResponse res) {
        String goodId = req.getParameter("goodId");
        String categoryId = req.getParameter("categoryId");
        if (goodId != null) {
            Good good = goodDao.loadById(Integer.parseInt(goodId));
            if (!RequestUtil.validate(Good.class, req)) {
                return "updateGood.jsp";
            }
            Good rGood = (Good) RequestUtil.setParams(Good.class, req);
            good.setStock(rGood.getStock());
            if (rGood.getImg() != null) {
                new File(SystemContext.getPROJECTUPLOADPATH() + "/" + good.getImg()).delete();
                new File(SystemContext.getUPLOADPATH() + "/" + good.getImg()).delete();
                good.setImg(rGood.getImg());
            }
            good.setIntroduce(rGood.getIntroduce());
            good.setName(rGood.getName());
            good.setPrice(rGood.getPrice());
            try {
                if (categoryId != null) {
                    goodDao.update(Integer.parseInt(categoryId), good);
                }
            } catch (ShopException se) {
                req.setAttribute("e", se.getMessage());
                return "addGood.jsp";
            }
        }
        return BaseServlet.redirect + "good.do?method=goodsList";
    }

    public String changeGoodStatus(HttpServletRequest req, HttpServletResponse res) {
        String id = req.getParameter("id");
        if (id != null) {
            goodDao.changeStatus(Integer.parseInt(id));
        }
        return BaseServlet.redirect + "good.do?method=goodsList";
    }

    public String addStockPage(HttpServletRequest req, HttpServletResponse res) {
        String id = req.getParameter("id");
        req.setAttribute("id", id);
        return "addStock.jsp";
    }

    public String addStock(HttpServletRequest req, HttpServletResponse res) {
        String id = req.getParameter("id");
        String num = req.getParameter("num");
        if (id != null && num != null && id.equals("") && num.equals("")) {
            goodDao.addStock(Integer.parseInt(id), Integer.parseInt(num));
        }
        return BaseServlet.redirect + "good.do?method=goodsList";
    }
}
