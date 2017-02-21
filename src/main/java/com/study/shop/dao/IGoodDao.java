package com.study.shop.dao;

import com.study.shop.model.Category;
import com.study.shop.model.Good;
import com.study.shop.model.Pager;

/**
 * Created by 傲然 on 2017/2/20.
 */
public interface IGoodDao {
    void add(int categoryId, Good good);
    void delete(int id);
    void update(int categoryId, Good good);
    /*
    * status表示目前处于上线或下线的状态，若下线则不可销售
    * 1为在线，0为下线
    * */
    Pager<Good> find(int categoryId, int status, String goodName);
    Good loadById(int id);
    void changeStatus(int id);
    void addStock(int id, int num);
    void decreaseStock(int id, int num);
}
