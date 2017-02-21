package com.study.shop.web;

import com.study.shop.model.SystemContext;
import com.study.shop.model.User;
import com.study.shop.util.DaoUtil;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Created by 傲然 on 2017/2/12.
 */
public class BaseServlet extends HttpServlet {
    public static final String redirect = "redirect:";

    @Override
    public void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        DaoUtil.inject(this);
        if (ServletFileUpload.isMultipartContent(req)) {
            req = new MultipartWrapper(req);
        }
        String methodName = req.getParameter("method");
        try {
            Method method = this.getClass().getMethod(methodName, HttpServletRequest.class, HttpServletResponse.class);
            //通过方法访问前先进入service中，所以在此进行权限判定
            int authFlag = checkAuthority(req, method);

            if (authFlag == 0) {
                res.sendRedirect("user.do?method=loginPage");
                return;
            } else if (authFlag == -1) {
                req.setAttribute("e","对不起，您没有足够权限访问该页面！");
                req.getRequestDispatcher("WEB-INF/errors.jsp").forward(req,res);
            }
            String path = (String) method.invoke(this, req, res);

            if (path.startsWith(redirect)) {
                path = path.substring(redirect.length());
                res.sendRedirect(path);
            } else {
                req.getRequestDispatcher("/WEB-INF/" + path).forward(req, res);
            }
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

    }
    /*
    * 1为可以访问
    * 0为跳转登录
    * -1为无权限，跳转错误
    * */
    public int checkAuthority(HttpServletRequest req, Method method) {
        User loginUser = (User) req.getSession(true).getAttribute("loginUser");
        if (loginUser != null && loginUser.getType() == 1) {
            //管理员访问，不拦截
            return 1;
        }

        if (method.isAnnotationPresent(Authority.class)) {
            if (method.getAnnotation(Authority.class).value().equals("user")) {
                if (loginUser == null) {
                    return 0;
                } else {
                    return 1;
                }
            } else {
                return 1;
            }
        } else {
            if (loginUser == null) {
                return 0;
            } else {
                return -1;
            }
        }
    }
}
