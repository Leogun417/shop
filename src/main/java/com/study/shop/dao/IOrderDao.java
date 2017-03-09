package com.study.shop.dao;

import com.study.shop.model.GoodInCart;
import com.study.shop.model.Order;
import com.study.shop.model.Pager;
import com.study.shop.model.User;

/**
 * Created by 傲然 on 2017/3/7.
 */
public interface IOrderDao {
    void add(User user, int addressId, Order order);
    void addToCart(GoodInCart goodInCart);
    Pager<Order> find(int userId, int orderStatus);
}
