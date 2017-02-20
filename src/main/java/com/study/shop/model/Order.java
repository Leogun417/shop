package com.study.shop.model;

import java.util.List;
import java.util.Date;

/**
 * Created by 傲然 on 2017/1/30.
 */
public class Order {
    private int id;
    private Date buyDate;
    private Date payDate;
    private Date sendGoodDate;
    private int status;
    private User user;
    private Address address;
    private List<Good> goodList;

    public List<Good> getGoodList() {
        return goodList;
    }

    public void setGoodList(List<Good> goodList) {
        this.goodList = goodList;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getBuyDate() {
        return buyDate;
    }

    public void setBuyDate(Date buyDate) {
        this.buyDate = buyDate;
    }

    public Date getPayDate() {
        return payDate;
    }

    public void setPayDate(Date payDate) {
        this.payDate = payDate;
    }

    public Date getSendGoodDate() {
        return sendGoodDate;
    }

    public void setSendGoodDate(Date sendGoodDate) {
        this.sendGoodDate = sendGoodDate;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }
}
