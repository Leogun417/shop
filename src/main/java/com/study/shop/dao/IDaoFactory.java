package com.study.shop.dao;

/**
 * Created by 傲然 on 2017/2/6.
 */
public interface IDaoFactory {
    Object createDao(String name);
}
