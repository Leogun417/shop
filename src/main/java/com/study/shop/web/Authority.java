package com.study.shop.web;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Created by 傲然 on 2017/2/15.
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface Authority {
    String value();
}
