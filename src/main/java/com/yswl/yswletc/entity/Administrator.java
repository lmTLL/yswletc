package com.yswl.yswletc.entity;

import com.baomidou.mybatisplus.annotation.TableName;

/**
 * User: jang
 * Date: 2019/10/17
 * Time: 9:02
 * Description: No Description
 */
@TableName(value = "t_administrator")
public class Administrator {

    private Integer id;

    private String username;

    private String password;

}
