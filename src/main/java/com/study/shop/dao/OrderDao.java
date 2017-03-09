package com.study.shop.dao;

import com.study.shop.model.*;

import java.util.HashMap;
import java.util.List;

/**
 * Created by 傲然 on 2017/3/9.
 */
public class OrderDao extends BaseDao implements IOrderDao {
    IAddressDao addressDao;
    @DaoInject
    public void setAddressDao(IAddressDao addressDao) {
        this.addressDao = addressDao;
    }

    @Override
    public void add(User user, int addressId, Order order) {
        order.setUser(user);
        order.setAddress((Address) addressDao.loadById(addressId));
        this.add(order);
        List<GoodInCart> goodList = order.getGoodList();
        for (GoodInCart goodInCart : goodList) {
            addToCart(goodInCart);
        }
    }

    @Override
    public void addToCart(GoodInCart goodInCart) {
        super.add(goodInCart);
    }

    @Override
    public Pager<Order> find(int userId, int orderStatus) {
        HashMap<String, Object> params = new HashMap<>();
        params.put("userId", userId);
        params.put("orderStatus", orderStatus);
        return this.find(Order.class.getName() + ".findByUserAndStatus", params);
    }
}
