package com.study.shop.web;

import com.study.shop.dao.IGoodDao;
import com.study.shop.dao.IUserDao;
import com.study.shop.model.DaoInject;
import com.study.shop.model.ShopCart;
import com.study.shop.model.ShopException;
import com.study.shop.model.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by 傲然 on 2017/2/27.
 */
public class OrderServlet extends BaseServlet {
    private IGoodDao goodDao;
    private IUserDao userDao;

    @DaoInject
    public void setUserDao(IUserDao userDao) {
        this.userDao = userDao;
    }

    @DaoInject
    public void setGoodDao(IGoodDao goodDao) {
        this.goodDao = goodDao;
    }

    @Authority("user")
    public String addToCart(HttpServletRequest req, HttpServletResponse res) {
        String goodId = req.getParameter("goodId");
        ShopCart shopCart = (ShopCart) req.getSession(true).getAttribute("shopCart");
        if (shopCart == null) {
            shopCart = new ShopCart();
            req.getSession(true).setAttribute("shopCart", shopCart);
        }
        if (goodId != null) {
            try {
                shopCart.addGood(goodDao.loadById(Integer.parseInt(goodId)));
            } catch (ShopException se) {
                req.setAttribute("e", se.getMessage());
                return "errors.jsp";
            }
        }
        return BaseServlet.redirect + "good.do?method=showGoodPage&id=" + goodId;
    }

    @Authority("user")
    public String showShopCartPage(HttpServletRequest req, HttpServletResponse res) {
        ShopCart shopCart = (ShopCart) req.getSession(true).getAttribute("shopCart");
        User loginUser = (User) req.getSession(true).getAttribute("loginUser");
        req.getSession(true).setAttribute("loginUser", userDao.loadById(loginUser.getId()));
        if (shopCart == null) {
            shopCart = new ShopCart();
            req.getSession(true).setAttribute("shopCart", shopCart);
        }
        return "showShopCart.jsp";
    }

    @Authority("user")
    public String deleteGoodInCart(HttpServletRequest req, HttpServletResponse res) {
        String goodId = req.getParameter("goodId");
        ShopCart shopCart = (ShopCart) req.getSession(true).getAttribute("shopCart");
        if (shopCart == null) {
            shopCart = new ShopCart();
            req.getSession(true).setAttribute("shopCart", shopCart);
        }
        if (goodId != null) {
            shopCart.deleteGood(Integer.parseInt(goodId));
        }

        return BaseServlet.redirect + "order.do?method=showShopCartPage";
    }

    @Authority("user")
    public String clearGoodInCart(HttpServletRequest req, HttpServletResponse res) {
        ShopCart shopCart = (ShopCart) req.getSession(true).getAttribute("shopCart");
        if (shopCart == null) {
            shopCart = new ShopCart();
            req.getSession(true).setAttribute("shopCart", shopCart);
        }
        shopCart.clearGood();
        return BaseServlet.redirect + "order.do?method=showShopCartPage";
    }

    @Authority("user")
    public String updateNumOfGoodInCartPage(HttpServletRequest req, HttpServletResponse res) {
        String goodId = req.getParameter("goodId");
        req.setAttribute("goodId", goodId);
        return "updateNumOfGoodInCart.jsp";
    }

    @Authority("user")
    public String updateNumOfGoodInCart(HttpServletRequest req, HttpServletResponse res) {
        String goodId = req.getParameter("goodId");
        String number = req.getParameter("number");
        ShopCart shopCart = (ShopCart) req.getSession(true).getAttribute("shopCart");
        if (shopCart == null) {
            shopCart = new ShopCart();
            req.getSession(true).setAttribute("shopCart", shopCart);
        }
        if (goodId != null && number != null) {
            try {
                shopCart.updateNumber(Integer.parseInt(goodId), Integer.parseInt(number));
            } catch (ShopException se) {
                req.setAttribute("e", se.getMessage());
                return "errors.jsp";
            }
        }
        return BaseServlet.redirect + "order.do?method=showShopCartPage";
    }
}
