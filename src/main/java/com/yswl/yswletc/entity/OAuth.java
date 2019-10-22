package com.yswl.yswletc.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * User: jang
 * Date: 2019/10/22
 * Time: 15:52
 * Description: No Description
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName(value = "t_OAuth")
public class OAuth {

    private String refresh_token; //授权的token

    private Integer expires_in; //时间

    private Date creation_time;//创建时间


}
