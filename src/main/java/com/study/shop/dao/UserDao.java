package com.study.shop.dao;

import com.study.shop.model.Pager;
import com.study.shop.model.ShopException;
import com.study.shop.model.SystemContext;
import com.study.shop.model.User;
import com.study.shop.util.MyBatisUtil;
import org.apache.ibatis.session.SqlSession;

import java.util.HashMap;
import java.util.List;

/**
 * Created by 傲然 on 2017/2/3.
 */
public class UserDao extends BaseDao<User> implements IUserDao {
    public void add(User user) {
        if (null != loadByUsername(user.getUsername())) {
            throw new ShopException("用户名已存在！");
        }
        super.add(user);
    }

    public void delete(int id) {
        //TODO 删除关联对象
        super.delete(User.class,id);
    }

    public void update(User user) {
       super.update(user);
    }

    public User loadById(int id) {
        return super.loadById(User.class.getName() + ".loadById",id);
    }

    public User loadByUsername(String username) {
        return super.loadByUsername(User.class.getName() + ".loadByUsername",username);
    }

    public Pager<User> find(String name) {
        HashMap<String, Object> params = new HashMap<String, Object>();
        if (!"".equals(name) && null != name) {
            params.put("name", "%" + name + "%");
        }
        return super.find(User.class.getName() + ".find", params);
    }

    public User login(String username, String password) {
        SqlSession session = null;
        User user = null;
        try {
            session = MyBatisUtil.getSession();
            user = session.selectOne(User.class.getName() + ".login", username);
            if (user == null) {
                throw new ShopException("用户不存在！");
            } else {
                if (!user.getPassword().equals(password)) {
                    throw new ShopException("密码错误！");
                }
            }
        } finally {
            MyBatisUtil.closeSession(session);
        }
        return user;
    }
}
