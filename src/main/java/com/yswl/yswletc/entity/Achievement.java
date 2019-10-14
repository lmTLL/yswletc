package com.yswl.yswletc.entity;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 业绩表
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "t_achievement")
public class Achievement implements Serializable {

    @TableId(type = IdType.AUTO)
    private Integer id; //编号

    private String uid; //当前提交用户id

    private String username; //用户名

    private String phone; //手机号

    private Integer project; //项目表Id

    private String carid; //车牌号

    private String picturepath; //图片路径

    private String remarks; //备注

    private Integer state = 0; //审核状态：0待审核  1已审核
}