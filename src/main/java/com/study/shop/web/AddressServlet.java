package com.study.shop.web;

import com.study.shop.dao.AddressDao;
import com.study.shop.dao.IAddressDao;
import com.study.shop.dao.IUserDao;
import com.study.shop.dao.UserDao;
import com.study.shop.model.Address;
import com.study.shop.model.DaoInject;
import com.study.shop.model.User;
import com.study.shop.util.RequestUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by 傲然 on 2017/2/16.
 */
public class AddressServlet extends BaseServlet {
    IUserDao userDao;
    IAddressDao addressDao;

    @DaoInject
    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    @DaoInject
    public void setAddressDao(AddressDao addressDao) {
        this.addressDao = addressDao;
    }

    @Authority("user")
    public String addAddressPage(HttpServletRequest req, HttpServletResponse res) {
        return "addAddress.jsp";
    }

    @Authority("user")
    public String addAddress(HttpServletRequest req, HttpServletResponse res) {
        User loginUser = (User) req.getSession(true).getAttribute("loginUser");
        String from = req.getParameter("from");
        if (!RequestUtil.validate(Address.class, req)) {
            return "addAddress.jsp";
        }
        Address address = (Address) RequestUtil.setParams(Address.class, req);
        try {
            addressDao.add(loginUser.getId(), address);
        } catch (SecurityException se) {
            req.setAttribute("e", se.getMessage());
            return "addAddress.jsp";
        }

        if (from.equals("userInfo")) {
            return BaseServlet.redirect + "user.do?method=showUserInfoPage&id=" + loginUser.getId();
        } else if (from.equals("shopCart")) {
            return BaseServlet.redirect + "order.do?method=showShopCartPage";
        } else {
            return BaseServlet.redirect + "user.do?method=showSelfInfoPage";
        }
    }

    @Authority("user")
    public String updateAddressPage(HttpServletRequest req, HttpServletResponse res) {
        Address toUpdateAddress = null;
        String addressId = req.getParameter("addressId");
        if (addressId != null) {
            toUpdateAddress = (Address) addressDao.loadById(Integer.parseInt(addressId));
            req.setAttribute("toUpdateAddress", toUpdateAddress);
        }

        User loginUser = (User) req.getSession(true).getAttribute("loginUser");
        //欲更新的地址不属于当前登录用户
        if (toUpdateAddress.getUser().getId() != loginUser.getId()) {
            req.setAttribute("e", "非法访问！");
            return "errors.jsp";
        }
        return "updateAddress.jsp";
    }

    @Authority("user")
    public String updateAddress(HttpServletRequest req, HttpServletResponse res) {
        //没有通过更新页面正常访问
        if (req.getParameter("addrId") == null) {
            req.setAttribute("e", "非法访问！");
            return "errors.jsp";
        }
        Address toUpdateAddress = (Address) addressDao.loadById(Integer.parseInt(req.getParameter("addrId")));
        Address rAddress = (Address) RequestUtil.setParams(Address.class, req);
        String from = req.getParameter("from");

        toUpdateAddress.setAddress(rAddress.getAddress());
        toUpdateAddress.setPhone(rAddress.getPhone());
        toUpdateAddress.setPostcode(rAddress.getPostcode());
        addressDao.update(toUpdateAddress);
        if (from.equals("userInfo")) {
            return BaseServlet.redirect + "user.do?method=showUserInfoPage&id=" + toUpdateAddress.getUser().getId();
        } else {
            return BaseServlet.redirect + "user.do?method=showSelfInfoPage";
        }
    }

    @Authority("user")
    public String deleteAddress(HttpServletRequest req, HttpServletResponse res) {
        String from = req.getParameter("from");
        String addressId = req.getParameter("addressId");
        if (addressId == null) {
            return BaseServlet.redirect + "user.do?method=showSelfInfoPage";
        }
        Address address = (Address) addressDao.loadById(Integer.parseInt(addressId));
        User loginUser = (User) req.getSession(true).getAttribute("loginUser");
        //想要删除的地址不属于当前登录用户
        if (loginUser.getId() != address.getUser().getId()) {
            req.setAttribute("e", "非法访问！");
            return "errors.jsp";
        }
        addressDao.delete(address.getId());
        if (from.equals("userInfo")) {
            return BaseServlet.redirect + "user.do?method=showUserInfoPage&id=" + loginUser.getId();
        } else {
            return BaseServlet.redirect + "user.do?method=showSelfInfoPage";
        }
    }
}
