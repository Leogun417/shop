package com.study.shop.model;

/**
 * Created by 傲然 on 2017/1/30.
 */
public class Category {
    private int id;
    @Validate(ValidateType.NOTNULL)
    private String name;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
