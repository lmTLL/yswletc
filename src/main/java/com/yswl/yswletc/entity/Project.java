package com.yswl.yswletc.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 项目表
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "t_projects")
public class Project implements Serializable {

    @TableId(type = IdType.AUTO)
    private Integer id; //编号

    private String log; //log路径

    private String projectname; //项目名称

    private String introduce; //介绍

    private Integer remainders; //剩余任务数

    private BigDecimal commission; //佣金

    private String qrcodepath; //二维码路径

    private Integer state; //状态
}