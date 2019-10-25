package com.yswl.yswletc.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @version 1.0
 * 提现订单表
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "t_neworders")
public class NewOrders implements Serializable {

    private Integer id; //编号

    private Integer bid;//卡号id

    private Integer uid;//发起提款用户id;

    private String uname;//发起提款用户姓名

    private String name; //持卡人姓名

    private String  number; //银行卡卡号

    private String bankname; //银行名

    private String cardpath; //银行卡图片路径

    private BigDecimal allprice;//提款金额

    private Date creationtime; //提款发起时间

    private Integer status = 0;//状态 0:待拨款 1:已拨款 2：驳回

    private String comment;//备注

    private String receiptpath; //打款回执图片路径

    private Date remittime;//打款时间

}
