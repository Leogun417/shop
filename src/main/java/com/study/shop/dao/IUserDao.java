package com.study.shop.dao;

import com.study.shop.model.Pager;
import com.study.shop.model.User;

/**
 * Created by 傲然 on 2017/2/3.
 */
public interface IUserDao {
    void add(User user);
    void delete(int id);
    void update(User user);
    User loadById(int id);
    User loadByUsername(String username);
    Pager<User> find(String name);
    User login(String username, String password);
}
