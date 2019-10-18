package com.yswl.yswletc.common.aop;

import java.lang.annotation.*;

/**
 * User: jang
 * Date: 2019/8/4
 * Time: 16:54
 * Description: No Description
 */
@Documented
@Target(value = ElementType.METHOD)
@Retention(value = RetentionPolicy.RUNTIME)
public @interface TestTime {

    boolean mustLogin() default false;
}
