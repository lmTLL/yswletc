package com.yswl.yswletc.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 用户表
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "t_user")
public class User implements Serializable {

    private Integer id; //编号

    private String name; //姓名

    private String phone; //手机

    private String password; //密码

    private BigDecimal commission; //佣金

    private Integer uid; //上级id

    private Integer state; //状态

    private String openid; //用户标识
}