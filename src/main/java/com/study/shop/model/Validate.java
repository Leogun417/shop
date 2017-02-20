package com.study.shop.model;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Created by 傲然 on 2017/2/17.
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface Validate {
    String value();
}
