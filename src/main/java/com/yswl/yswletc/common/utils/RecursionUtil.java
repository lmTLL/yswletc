package com.yswl.yswletc.common.utils;

import com.yswl.yswletc.dao.DetailMapper;
import com.yswl.yswletc.dao.UserMapper;
import com.yswl.yswletc.entity.Detail;
import com.yswl.yswletc.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.math.BigDecimal;
import java.util.Date;

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
    private DetailMapper detailMapper;

    @Autowired
    private static RecursionUtil recursionUtil;

    @PostConstruct
    public void init() {
        recursionUtil = this;
        recursionUtil.userMapper = this.userMapper;
    }


    public boolean back(User user,Integer id){
        if (user.getUid() == 0){//等于0表示没有父级
            return true;
        }

        Integer uid = user.getUid();
        User user1 = userMapper.selectById(uid);
        //计算子级和父级的差价
        BigDecimal subtract = user1.getCommission().subtract(user.getCommission());
        //给父级加钱 钱包+佣金差
        user1.setWallet(user1.getWallet().add(subtract));


        //加钱明细
        Detail detail = new Detail();
        detail.setUid(user1.getId()); //用户id
        detail.setSid(id); //标记id
        detail.setAction(1);
        detail.setMoney(subtract);
        detail.setBalance(user1.getWallet());
        detail.setCreationtime(new Date());
        detail.setRemark("下级业绩分润");
        detailMapper.insert(detail);//明细入库

        userMapper.updateById(user1);
        //返回
        boolean back = back(user1,id);
        return back;
    }
}
