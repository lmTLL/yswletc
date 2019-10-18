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

    private String documentpath; //帮助文档路径

    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date joindate; //创建时间

    private Integer state = 1; //状态 1：可用状态 0:不可用状态

    private Integer additionnumber; //总任务数
}