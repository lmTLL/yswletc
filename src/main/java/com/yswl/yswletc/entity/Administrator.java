package com.yswl.yswletc.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * User: jang
 * Date: 2019/10/17
 * Time: 9:02
 * Description: No Description
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "t_administrator")
public class Administrator implements Serializable {

    @TableId(type = IdType.AUTO)
    private Integer id;

    private String username;

    private String password;

}
