package com.study.shop.dao;

import com.study.shop.model.Address;

import java.util.List;

/**
 * Created by 傲然 on 2017/2/5.
 */
public interface IAddressDao {
    void add(int userId, Address address);

    void delete(int id);

    void update(Address address);

    Object loadById(int id);

    List<Address> list(int userId);
}
