package com.study.shop.model;

/**
 * Created by 傲然 on 2017/1/30.
 */
public class ShopException extends RuntimeException {
    public ShopException() {
    }

    public ShopException(String message) {
        super(message);
    }

    public ShopException(String message, Throwable cause) {
        super(message, cause);
    }

    public ShopException(Throwable cause) {
        super(cause);
    }
}
