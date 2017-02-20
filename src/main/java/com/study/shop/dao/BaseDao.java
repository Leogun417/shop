package com.study.shop.dao;

import com.study.shop.model.Pager;
import com.study.shop.model.SystemContext;
import com.study.shop.model.User;
import com.study.shop.util.DaoUtil;
import com.study.shop.util.MyBatisUtil;
import org.apache.ibatis.session.SqlSession;

import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;

/**
 * Created by 傲然 on 2017/2/5.
 */
public class BaseDao<T> {
    public BaseDao() {
        DaoUtil.inject(this);
    }
    public void add(T obj) {
        SqlSession session = null;
        try {
            session = MyBatisUtil.getSession();
            session.insert(obj.getClass().getName() + ".add", obj);
            session.commit();
        } catch (Exception e) {
            e.printStackTrace();
            session.rollback();
        } finally {
            MyBatisUtil.closeSession(session);
        }
    }

    public void delete(Class<T> cls, int id) {
        SqlSession session = null;
        try {
            session = MyBatisUtil.getSession();
            session.delete(cls.getName() + ".delete", id);
            session.commit();
        } catch (Exception e) {
            e.printStackTrace();
            session.rollback();
        } finally {
            MyBatisUtil.closeSession(session);
        }
    }

    public void update(T obj) {
        SqlSession session = null;
        try {
            session = MyBatisUtil.getSession();
            session.update(obj.getClass().getName() + ".update", obj);
            session.commit();
        } catch (Exception e) {
            e.printStackTrace();
            session.rollback();
        } finally {
            MyBatisUtil.closeSession(session);
        }
    }

    public T loadById(String slqId, int id) {
        SqlSession session = null;
        T obj = null;
        try {
            session = MyBatisUtil.getSession();
            obj = session.selectOne(slqId, id);
        } finally {
            MyBatisUtil.closeSession(session);
        }
        return obj;
    }

    public T loadByUsername(String slqId, String username) {
        SqlSession session = null;
        T obj = null;
        try {
            session = MyBatisUtil.getSession();
            obj = session.selectOne(User.class.getName() + ".loadByUsername", username);
        } finally {
            MyBatisUtil.closeSession(session);
        }
        return obj;
    }

    public Pager<T> find(String sqlId, Map params) {
        SqlSession session = null;
        Pager<T> objPager = null;
        try {
            int offset = SystemContext.getOffset();
            int pageSize = SystemContext.getPageSize();
            String sortBy = SystemContext.getSortBy();
            String order = SystemContext.getOrder();
            session = MyBatisUtil.getSession();
            params.put("offset", offset);
            params.put("pageSize", pageSize);
            params.put("sortBy", sortBy);
            params.put("order", order);
            List<T> objList = session.selectList(sqlId, params);
            objPager = new Pager<T>();
            objPager.setDatas(objList);
            objPager.setOffset(offset);
            objPager.setPageSize(pageSize);
            int totalRecord = session.selectOne(sqlId + "Count", params);
            objPager.setTotalRecord(totalRecord);
        } finally {
            MyBatisUtil.closeSession(session);
        }
        return objPager;
    }

    public List<T> list(String sqlId, Map params) {
        SqlSession session = null;
        List<T> objList = null;
        try {
            String sortBy = SystemContext.getSortBy();
            String order = SystemContext.getOrder();
            session = MyBatisUtil.getSession();
            params.put("sortBy", sortBy);
            params.put("order", order);
            objList = session.selectList(sqlId, params);
        } finally {
            MyBatisUtil.closeSession(session);
        }
        return objList;
    }
}
