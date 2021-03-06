package com.study.shop.model;

/**
 * Created by 傲然 on 2017/1/30.
 */
public class Good {
    private int id;
    @Validate(ValidateType.NOTNULL)
    private String name;
    @Validate(ValidateType.NOTNULL)
    private double price;
    @Validate(ValidateType.NOTNULL)
    private int stock;
    @Validate(ValidateType.NOTNULL)
    private String introduce;
    private Category category;
    private String img;
    private int status;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

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

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public String getIntroduce() {
        return introduce;
    }

    public void setIntroduce(String introduce) {
        this.introduce = introduce;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }
}
