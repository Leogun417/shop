package com.study.shop.dao;

import com.study.shop.model.Category;

import java.util.HashMap;
import java.util.List;

/**
 * Created by 傲然 on 2017/2/20.
 */
public class CategoryDao extends BaseDao<Category> implements ICategoryDao {
    public void add(Category category) {
        super.add(category);
    }

    public void delete(int id) {
        //TODO 包含商品的类别不能删除
        super.delete(Category.class,id);
    }

    public void update(Category category) {
        super.update(category);
    }

    public List<Category> list(String name) {
        HashMap<String, Object> params = new HashMap<String, Object>();
        if (name != null || !name.equals("")) {
            params.put("name", "%" + name + "%");
        }
        List<Category> categoryList = super.list(Category.class.getName() + ".list", params);
        return categoryList;
    }

    public Category loadById(int id) {
        return super.loadById(Category.class.getName() + ".loadById", id);
    }
}
