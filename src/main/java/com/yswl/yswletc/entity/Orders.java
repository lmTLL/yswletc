package com.yswl.yswletc.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * @version 1.0
 * @user ken
 * @date 2019/6/9 11:36
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "t_orders")
public class Orders implements Serializable {

    @TableId(type = IdType.AUTO)
    private Integer id; //编号

    private Integer bid;//卡号id

    private Integer uid;//发起提款用户id;

    private String uname;//发起提款用户姓名

    private String name; //持卡人姓名

    private String  number; //银行卡卡号

    private String bankname; //银行名

    private BigDecimal allprice;//提款金额

    private Date creationtime; //提款发起时间

    private Integer status = 0;//状态 0:待拨款 1:已拨款 2：驳回

    private String comment;//备注

}
