package com.yswl.yswletc.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * User: jang
 * Date: 2019/10/22
 * Time: 15:11
 * Description: 银行卡表
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName(value = "t_BankCard")
public class BankCard {

    @TableId(type = IdType.AUTO)
    private Integer id;//id

    private Integer uid;//用户id属于谁

    private String uname; //用户姓名

    private String name; //持卡人姓名

    @TableField(value = "bankCardNumber")
    private String  bankcardnumber; //银行卡卡号

    @TableField(value = "validDate")
    private String validdate; //银行卡有效期

    @TableField(value = "bankCardType")
    private String bankcardtype;//银行卡类型

    @TableField(value = "bankName")
    private String bankname; //银行名

    @TableField(value = "creationTime")
    private Date creationtime; //绑卡时间

    @TableField(value = "cardPath")
    private String cardpath; //图片路径
}