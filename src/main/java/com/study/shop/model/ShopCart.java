package com.study.shop.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 傲然 on 2017/2/2.
 */
public class ShopCart {
    private List<GoodInCart> goods;
    private boolean isEmpty;

    public ShopCart() {
        goods = new ArrayList<>();
        isEmpty = true;
    }

    public boolean getIsEmpty() {
        return isEmpty;
    }

    public void setIsEmpty(boolean empty) {
        isEmpty = empty;
    }

    public List<GoodInCart> getGoods() {
        return goods;
    }

    public void setGoods(List<GoodInCart> goods) {
        this.goods = goods;
    }

    public void addGood(Good good) {
        GoodInCart goodInCart = checkCart(good);
        if (goodInCart != null) {
            if (goodInCart.getNumber() + 1 > good.getStock()) {
                throw new ShopException("该商品库存不足");
            }
            goodInCart.setNumber(goodInCart.getNumber() + 1);
            goodInCart.setPrice(goodInCart.getPrice() + good.getPrice());
        } else {
            if (1 > good.getStock()) {
                throw new ShopException("该商品库存不足");
            }
            goodInCart = new GoodInCart();
            goodInCart.setNumber(1);
            goodInCart.setGood(good);
            goodInCart.setPrice(good.getPrice());
            goodInCart.setGoodId(good.getId());
            goods.add(goodInCart);
            isEmpty = false;
        }
    }

    public void deleteGood(int goodId) {
        /*
        * 不能用增强for循环，因为增强for循环的内部也就是调用iteratoer实现，remove过程中指针会出错
        * */
        for (int i = 0; i < goods.size(); i++) {
            if (goods.get(i).getGoodId() == goodId) {
                goods.remove(i);
            }
        }

        if (goods.size() == 0) {
            isEmpty = true;
        }
    }

    public void clearGood() {
        if (goods != null) {
            goods.clear();
            isEmpty = true;
        }
    }

    public void updateNumber(int goodId, int num) {
        for (GoodInCart goodInCart : goods) {
            if (goodInCart.getGoodId() == goodId) {
                if (num > goodInCart.getGood().getStock()) {
                    throw new ShopException("该商品库存不足");
                }
                goodInCart.setNumber(num);
                goodInCart.setPrice(num * goodInCart.getGood().getPrice());
            }
        }
    }

    private GoodInCart checkCart(Good good) {
        for (GoodInCart goodInCart : goods) {
            if (goodInCart.getGoodId() == good.getId()) {
                //购物车中已存在此种商品
                return goodInCart;
            }
        }
        return null;
    }
}
