package com.study.shop.model;

/**
 * Created by 傲然 on 2017/1/30.
 */
public class Address {
    private int id;
    @Validate(ValidateType.NOTNULL)
    private String address;
    @Validate(ValidateType.NOTNULL)
    private String phone;
    @Validate(ValidateType.NOTNULL)
    private String postcode;
    private User user;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPostcode() {
        return postcode;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
