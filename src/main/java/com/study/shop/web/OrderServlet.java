package com.study.shop.web;

import com.study.shop.dao.IGoodDao;
import com.study.shop.dao.IOrderDao;
import com.study.shop.dao.IUserDao;
import com.study.shop.model.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Date;

/**
 * Created by 傲然 on 2017/2/27.
 */
public class OrderServlet extends BaseServlet {
    private IGoodDao goodDao;
    private IUserDao userDao;
    private IOrderDao orderDao;

    @DaoInject
    public void setOrderDao(IOrderDao orderDao) {
        this.orderDao = orderDao;
    }

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
        String from = req.getParameter("from");
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
        if (from.equals("showGood")) {
            return BaseServlet.redirect + "good.do?method=showGoodPage&id=" + goodId;
        } else {
            return BaseServlet.redirect + "good.do?method=goodsPage";
        }
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

    @Authority("user")
    public String showOrderPage(HttpServletRequest req, HttpServletResponse res) {
        User loginUser = (User) req.getSession(true).getAttribute("loginUser");
        String addressId = req.getParameter("addressId");
        String totalPrice = req.getParameter("totalPrice");

        Order order = new Order();
        ShopCart shopCart = (ShopCart) req.getSession(true).getAttribute("shopCart");
        order.setGoodList(shopCart.getGoods());
        if (totalPrice != null) {
            order.setPrice(Double.parseDouble(totalPrice));
        }
        order.setBuyDate(new Date());
        order.setStatus(1);
        if (addressId != null) {
            orderDao.add(loginUser, Integer.parseInt(addressId), order);
        }
        req.setAttribute("order", order);
        return "showOrder.jsp";
    }

    @Authority("user")
    public String showUserAllOrderPage(HttpServletRequest req, HttpServletResponse res) {
        User loginUser = (User) req.getSession(true).getAttribute("loginUser");
        Pager<Order> orderPager = orderDao.find(loginUser.getUsername(), -1);
        req.setAttribute("orderPager", orderPager);
        return "showUserAllOrder.jsp";
    }

    @Authority("user")
    public String showOrderInfoPage(HttpServletRequest req, HttpServletResponse res) {
        String orderId = req.getParameter("orderId");
        if (orderId != null) {
            Order order = orderDao.loadById(Integer.parseInt(orderId));
            req.setAttribute("order",order);
            req.setAttribute("fromMethod", "showOrderInfoPage");
        }
        return "showOrder.jsp";
    }

    @Authority("user")
    public String payFinishedPage(HttpServletRequest req, HttpServletResponse res) {
        String orderId = req.getParameter("orderId");
        if (orderId != null) {
            Order order = orderDao.loadById(Integer.parseInt(orderId));
            order.setPayDate(new Date());
            order.setStatus(2);
            orderDao.update(order);
            req.setAttribute("paytime", order.getPayDate());
        }
        return "payFinished.jsp";
    }

    @Authority("user")
    public String deleteOrder(HttpServletRequest req, HttpServletResponse res) {
        String orderId = req.getParameter("orderId");
        if (orderId != null) {
            orderDao.delete(Integer.parseInt(orderId));
        }
        return BaseServlet.redirect + "order.do?method=showUserAllOrderPage";
    }

    public String manageOrdersPage(HttpServletRequest req, HttpServletResponse res) {
        User loginUser = (User) req.getSession(true).getAttribute("loginUser");
        Pager<Order> orderPager = null;
        String name = req.getParameter("name");
        String orderStatus = req.getParameter("orderStatus");
        if (orderStatus != null) {
            orderPager = orderDao.find(name, Integer.parseInt(orderStatus));
        } else {
            orderPager = orderDao.find(name, -1);
        }
        req.setAttribute("orderPager", orderPager);
        return "manageOrders.jsp";
    }

    public String sendGoods(HttpServletRequest req, HttpServletResponse res) {
        String orderId = req.getParameter("orderId");
        if (orderId != null) {
            Order order = orderDao.loadById(Integer.parseInt(orderId));
            order.setSendGoodDate(new Date());
            order.setStatus(3);
            orderDao.update(order);
        }
        return BaseServlet.redirect + "order.do?method=manageOrdersPage";
    }

    @Authority("user")
    public String confirmReceive(HttpServletRequest req, HttpServletResponse res) {
        String orderId = req.getParameter("orderId");
        if (orderId != null) {
            Order order = orderDao.loadById(Integer.parseInt(orderId));
            order.setStatus(4);
            order.setConfirmDate(new Date());
            orderDao.update(order);
        }
        return BaseServlet.redirect + "order.do?method=showUserAllOrderPage";
    }
}
