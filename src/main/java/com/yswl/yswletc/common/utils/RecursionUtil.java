package com.yswl.yswletc.common.utils;

import com.yswl.yswletc.dao.UserMapper;
import com.yswl.yswletc.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.math.BigDecimal;

/**
 * User: jang
 * Date: 2019/10/21
 * Time: 15:20
 * Description: No Description
 */
@Component
public  class RecursionUtil {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private static RecursionUtil recursionUtil;

    @PostConstruct
    public void init() {
        recursionUtil = this;
        recursionUtil.userMapper = this.userMapper;
    }


    public boolean back(User user){
        if (user.getUid() == 0){//等于0表示没有父级
            return true;
        }
        Integer uid = user.getUid();
        User user1 = userMapper.selectById(uid);
        //计算子级和父级的差价
        BigDecimal subtract = user1.getCommission().subtract(user.getCommission());
        //给父级加钱 钱包+佣金
        user1.setWallet(user1.getWallet().add(subtract));
        userMapper.updateById(user1);

        //返回
        boolean back = back(user1);
        return back;
    }
}
