package com.yswl.yswletc.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * User: jang
 * Date: 2019/10/17
 * Time: 12:51
 * Description: No Description
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "t_newstudent")
public class NewStudent implements Serializable {

    private Integer id;

    private String name;

    private Integer age;

    private String phone;

    private Date birthday;

}

