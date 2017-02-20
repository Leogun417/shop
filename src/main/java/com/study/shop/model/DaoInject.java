package com.study.shop.model;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Created by 傲然 on 2017/2/8.
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface DaoInject {
    String value() default "";
}
