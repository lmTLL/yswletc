package com.yswl.yswletc.entity;

import com.baomidou.mybatisplus.annotation.IdType;
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
 * Description: No Description
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

    private String  bank_card_number; //银行卡卡号

    private String valid_date; //银行卡有效期

    private String bank_card_type;//银行卡类型

    private String bank_name; //银行名

    private Date creation_time; //时间

    private String card_path; //图片路径
}
