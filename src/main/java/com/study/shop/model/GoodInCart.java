package com.study.shop.model;

/**
 * Created by 傲然 on 2017/2/27.
 */
public class GoodInCart {
    private int id;
    private Order order;
    private Good good;
    private int goodId;
    private int number;
    private double price;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public Good getGood() {
        return good;
    }

    public void setGood(Good good) {
        this.good = good;
    }

    public int getGoodId() {
        return goodId;
    }

    public void setGoodId(int goodId) {
		this.goodId = goodId;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
