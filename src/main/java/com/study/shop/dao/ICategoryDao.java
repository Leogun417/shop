package com.study.shop.dao;

import com.study.shop.model.Category;

import java.util.List;

/**
 * Created by 傲然 on 2017/2/20.
 */
public interface ICategoryDao {
    void add(Category category);

    void delete(int id);

    void update(Category category);

    List<Category> list(String name);

    Category loadById(int id);
}
