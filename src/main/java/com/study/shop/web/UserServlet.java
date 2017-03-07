package com.study.shop.web;

import com.study.shop.dao.IUserDao;
import com.study.shop.dao.UserDao;
import com.study.shop.model.Pager;
import com.study.shop.model.DaoInject;
import com.study.shop.model.ShopException;
import com.study.shop.model.User;
import com.study.shop.util.RequestUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by 傲然 on 2017/2/12.
 */
public class UserServlet extends BaseServlet {
    IUserDao userDao;

    @DaoInject
    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    @Authority("all")
    public String registerPage(HttpServletRequest req, HttpServletResponse res) {
        return "register.jsp";
    }

    @Authority("all")
    public String register(HttpServletRequest req, HttpServletResponse res) {
        if (!RequestUtil.validate(User.class, req)) {
            return "register.jsp";
        }
        User user = (User) RequestUtil.setParams(User.class, req);
        try {
            userDao.add(user);
        } catch (ShopException se) {
            req.setAttribute("e", se.getMessage());
            return "register.jsp";
        }
        return "login.jsp";
    }

    public String userList(HttpServletRequest req, HttpServletResponse res) {
        Pager<User> userPager = userDao.find("");
        req.setAttribute("userPager", userPager);
        return "userList.jsp";
    }

    @Authority("all")
    public String loginPage(HttpServletRequest req, HttpServletResponse res) {
        return "login.jsp";
    }

    @Authority("all")
    public String login(HttpServletRequest req, HttpServletResponse res) {
        try {
            User loginUser = userDao.login(req.getParameter("username"), req.getParameter("password"));
            req.getSession(true).setAttribute("loginUser", loginUser);
            req.getSession(true).setMaxInactiveInterval(60 * 60);
        } catch (ShopException se) {
            req.setAttribute("e", se.getMessage());
            return "login.jsp";
        }
        return BaseServlet.redirect + "good.do?method=goodsPage";
    }

    @Authority("all")
    public String logout(HttpServletRequest req, HttpServletResponse res) {
        req.getSession(true).invalidate();
        return "login.jsp";
    }

    public String updateUserPage(HttpServletRequest req, HttpServletResponse res) {
        String userId = req.getParameter("userId");
        if (userId != null) {
            User user = userDao.loadById(Integer.parseInt(userId));
            req.setAttribute("toUpdateUser", user);
        }
        return "userUpdate.jsp";
    }


    public String updateUser(HttpServletRequest req, HttpServletResponse res) {
        User user = null;
        String password = req.getParameter("password");
        String nickname = req.getParameter("nickname");
        String id = req.getParameter("id");
        if (id != null) {
            user = userDao.loadById(Integer.parseInt(id));
            user.setPassword(password);
            user.setNickname(nickname);
            userDao.update(user);
        }
        return BaseServlet.redirect + "user.do?method=userList";
    }

    @Authority("user")
    public String updateSelfPage(HttpServletRequest req, HttpServletResponse res) {
        return "userUpdate.jsp";
    }

    @Authority("user")
    public String updateSelf(HttpServletRequest req, HttpServletResponse res) {
        User loginUser = (User) req.getSession(true).getAttribute("loginUser");
        String password = req.getParameter("password");
        String nickname = req.getParameter("nickname");
        loginUser.setPassword(password);
        loginUser.setNickname(nickname);
        userDao.update(loginUser);
        return BaseServlet.redirect + "good.do?method=goodsList";
    }

    public String deleteUser(HttpServletRequest req, HttpServletResponse res) {
        String id = req.getParameter("id");
        if (id != null) {
            userDao.delete(Integer.parseInt(id));
        }
        return BaseServlet.redirect + "user.do?method=userList";
    }

    public String changeType(HttpServletRequest req, HttpServletResponse res) {
        User user = null;
        String id = req.getParameter("id");
        if (id != null) {
            user = userDao.loadById(Integer.parseInt(id));
            if (user.getType() == 0) {
                user.setType(1);
            } else {
                user.setType(0);
            }
            userDao.update(user);
        }
        return BaseServlet.redirect + "user.do?method=userList";
    }

    public String showUserInfoPage(HttpServletRequest req, HttpServletResponse res) {
        User toShowUser = null;
        String id = req.getParameter("id");
        if (id != null) {
            toShowUser = userDao.loadById(Integer.parseInt(id));
            req.setAttribute("toShowUser", toShowUser);
        }
        return "userInfo.jsp";
    }

    @Authority("user")
    public String showSelfInfoPage(HttpServletRequest req, HttpServletResponse res) {
        User loginUser = (User) req.getSession(true).getAttribute("loginUser");
        int id = loginUser.getId();
        req.getSession(true).setAttribute("loginUser", userDao.loadById(id));
        return "selfInfo.jsp";
    }
}
