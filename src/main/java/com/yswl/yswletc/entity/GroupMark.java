package com.yswl.yswletc.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * User: jang
 * Date: 2019/10/15
 * Time: 15:18
 * Description: No Description
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class GroupMark {

    private Integer myScore; //我的业绩

    private Integer myPartner; //我的伙伴数量

    private Integer teamScore; //团队业绩

}
