package com.yswl.yswletc.entity;

import com.baomidou.mybatisplus.annotation.IdType;
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
 * 业绩表
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "t_achievement")
public class NewAchievement implements Serializable {

    @TableId(type = IdType.AUTO)
    private Integer id; //编号

    private Integer uid; //当前提交用户id

    private String username; //用户名

    private String phone; //手机号

    private Integer pid; //项目id

    private String itemname; //项目名

    private String carid; //车牌号

    private String picturepath; //图片路径

    private String remarks; //备注

    private BigDecimal commission; //奖金

    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date submittime; //提交时间

    private Integer state = 0; //审核状态：0待审核  1审核通过  2审核不通过
}