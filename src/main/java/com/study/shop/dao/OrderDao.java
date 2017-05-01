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
        super.add(order);
        List<GoodInCart> goodList = order.getGoodList();
        for (GoodInCart goodInCart : goodList) {
            goodInCart.setOrder(order);
            addToCart(goodInCart);
        }
    }

    @Override
    public void addToCart(GoodInCart goodInCart) {
        super.add(goodInCart);
    }

    @Override
    public Pager<Order> find(String name, int orderStatus) {
        HashMap<String, Object> params = new HashMap<>();
        if (!"".equals(name) && null != name) {
            params.put("name", "%" + name + "%");
        }
        if (orderStatus != -1) {
            params.put("orderStatus", orderStatus);
        }
        return super.find(Order.class.getName() + ".findByUserAndStatus", params);
    }

    @Override
    public Order loadById(int id) {
        return (Order) super.loadById(Order.class.getName() + ".loadById", id);
    }

    @Override
    public void update(Order order) {
        super.update(order);
    }

    @Override
    public void delete(int id) {
        this.deleteFromCart(id);
        super.delete(Order.class, id);
    }

    @Override
    public void deleteFromCart(int orderId) {
        super.delete(GoodInCart.class, orderId);
    }
}
