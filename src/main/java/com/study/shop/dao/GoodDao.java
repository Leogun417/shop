package com.study.shop.dao;

import com.study.shop.model.*;

import java.io.File;
import java.util.HashMap;

/**
 * Created by 傲然 on 2017/2/21.
 */
public class GoodDao extends BaseDao<Good> implements IGoodDao {
    CategoryDao categoryDao;

    @DaoInject
    public void setCategoryDao(CategoryDao categoryDao) {
        this.categoryDao = categoryDao;
    }

    public void add(int categoryId, Good good) {
        Category category = categoryDao.loadById(categoryId);
        if (category == null) {
            throw new ShopException("所选类别不存在！");
        }
        good.setCategory(category);
        super.add(good);
    }

    public void delete(int id) {
        //TODO 为上线状态或有订单存在的商品不能删除
        Good good = this.loadById(id);
        String img = good.getImg();
        new File(SystemContext.getPROJECTUPLOADPATH() + "/" + img).delete();
        new File(SystemContext.getUPLOADPATH() + "/" + img).delete();
        super.delete(Good.class, id);
    }

    public void update(int categoryId, Good good) {
        Category category = categoryDao.loadById(categoryId);
        if (category == null) {
            throw new ShopException("所选类别不存在！");
        }
        good.setCategory(category);
        super.update(good);
    }

    public Pager<Good> find(int categoryId, int status, String goodName) {
        HashMap<String, Object> params = new HashMap<String, Object>();
        if (categoryId > 0) {
            params.put("categoryId", categoryId);
        }
        if (status == 0 || status == 1) {
            params.put("status", status);
        }
        if (goodName != null && "".equals(goodName)) {
            params.put("name", "%" + goodName + "%");
        }
        Pager<Good> goodPager = super.find(Good.class.getName() + ".find", params);
        return goodPager;
    }

    public Good loadById(int id) {
        return super.loadById(Good.class.getName() + ".loadById", id);
    }

    public void changeStatus(int id) {
        Good good = loadById(id);
        int status = good.getStatus();
        System.out.println(status);
        if (status == 0) {
            status = 1;
            System.out.println(status);
        } else if (status == 1) {
            status = 0;
        }
        good.setStatus(status);
        super.update(good);
    }

    public void addStock(int id, int num) {
        Good good = loadById(id);
        int stock = good.getStock();
        stock = stock + num;
        good.setStock(stock);
        super.update(good);
    }

    public void decreaseStock(int id, int num) {
        Good good = loadById(id);
        int stock = good.getStock();
        stock = stock - num;
        if (stock >= 0) {
            good.setStock(stock);
            super.update(good);
        }
    }
}
