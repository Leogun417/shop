package com.study.shop.model;

import java.util.List;

/**
 * Created by 傲然 on 2017/1/30.
 */
public class User {
    private int id;
    @Validate(ValidateType.NOTNULL)
    private String username;
    @Validate(ValidateType.NOTNULL)
    private String nickname;
    @Validate(ValidateType.NOTNULL)
    private String password;
    private int type;
    private List<Address> addrList;

    public List<Address> getAddrList() {
        return addrList;
    }

    public void setAddrList(List<Address> addrList) {
        this.addrList = addrList;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
