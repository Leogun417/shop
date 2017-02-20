package com.study.shop.web;
import com.study.shop.dao.ICategoryDao;
import com.study.shop.model.Category;
import com.study.shop.model.DaoInject;
import com.study.shop.util.RequestUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Created by 傲然 on 2017/2/20.
 */
public class CategoryServlet extends BaseServlet {
    private ICategoryDao categoryDao;
    @DaoInject
    public void setCategoryDao(ICategoryDao categoryDao) {
        this.categoryDao = categoryDao;
    }

    public String categoryList(HttpServletRequest req, HttpServletResponse res) {
        List<Category> list = categoryDao.list("");
        req.setAttribute("categoryList",list);
        return "categoryList.jsp";
    }

    public String addCategoryPage(HttpServletRequest req, HttpServletResponse res) {
        return "addCategory.jsp";
    }

    public String addCategory(HttpServletRequest req, HttpServletResponse res) {
        if (!RequestUtil.validate(Category.class, req)) {
            return "addCategory.jsp";
        }
        Category category = (Category) RequestUtil.setParams(Category.class, req);
        categoryDao.add(category);
        return BaseServlet.redirect + "category.do?method=categoryList";
    }

    public String updateCategoryPage(HttpServletRequest req, HttpServletResponse res) {
        String id = req.getParameter("id");
        if (id != null) {
            Category toUpdateCategory = categoryDao.loadById(Integer.parseInt(id));
            req.setAttribute("toUpdateCategory", toUpdateCategory);
        }
        return "updateCategory.jsp";
    }

    public String updateCategory(HttpServletRequest req, HttpServletResponse res) {
        Category category = (Category) RequestUtil.setParams(Category.class, req);
        categoryDao.update(category);
        return BaseServlet.redirect + "category.do?method=categoryList";
    }

    public String deleteCategory(HttpServletRequest req, HttpServletResponse res) {
        String id = req.getParameter("id");
        if (id != null) {
            categoryDao.delete(Integer.parseInt(id));
        }

        return BaseServlet.redirect + "category.do?method=categoryList";
    }
}
