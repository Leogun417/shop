package com.study.shop.dao;

import com.study.shop.model.*;

import java.util.HashMap;
import java.util.List;

/**
 * Created by 傲然 on 2017/2/5.
 */
public class AddressDao extends BaseDao<Address> implements IAddressDao {
    private IUserDao userDao;

    @DaoInject
    public void setUserDao(IUserDao userDao) {
        this.userDao = userDao;
    }

    public void add(int userId, Address address) {
        User user = userDao.loadById(userId);
        if (user == null) {
            throw new ShopException("用户不存在");
        } else {
            address.setUser(user);
            super.add(address);
        }
    }

    public void delete(int id) {
        super.delete(Address.class, id);
    }

    public void update(Address address) {
        super.update(address);
    }

    public Address loadById(int id) {
        return super.loadById(Address.class.getName() + ".loadById", id);
    }

    public List<Address> list(int userId) {
        HashMap<String, Object> params = new HashMap<String, Object>();
        params.put("userId", userId);
        return super.list(Address.class.getName() + ".list", params);
    }
}
