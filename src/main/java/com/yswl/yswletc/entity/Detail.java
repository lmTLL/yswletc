package com.yswl.yswletc.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

/**
 * User: jang
 * Date: 2019/10/24
 * Time: 11:23
 * Description: 账单明细表
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "t_detail")
public class Detail {

    @TableId(type = IdType.AUTO)
    private Integer id;//id

    private Integer uid;//用户id

    private Integer sid;//标记id

    private Integer action;//1:入账、2:支出

    private BigDecimal money;//变动金额

    private BigDecimal balance;//余额

    private Date creationtime;//操作时间

    private String remark; //备注

}
