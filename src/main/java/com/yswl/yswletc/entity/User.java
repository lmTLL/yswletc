package com.yswl.yswletc.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 用户表
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "t_user")
public class User implements Serializable {

    @TableId(type = IdType.AUTO)
    private Integer id; //编号

    private String name; //姓名

    private String phone; //手机

    private String password; //密码

    private Integer uid; //上级id

    private String uname; //上级姓名

    private Integer state = 1; //状态

    private String openid; //用户标识

    private BigDecimal commission; //佣金

    private BigDecimal wallet; //钱包

    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date joindate; //注册时间

    @TableField(exist = false)
    private Integer Today; //今日业绩

}