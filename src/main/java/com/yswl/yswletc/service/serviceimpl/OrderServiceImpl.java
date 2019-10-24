package com.yswl.yswletc.service.serviceimpl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.yswl.yswletc.common.utils.ResultUtil;
import com.yswl.yswletc.common.vo.ResultVo;
import com.yswl.yswletc.dao.BankCardMapper;
import com.yswl.yswletc.dao.DetailMapper;
import com.yswl.yswletc.dao.OrdersMapper;
import com.yswl.yswletc.dao.UserMapper;
import com.yswl.yswletc.entity.BankCard;
import com.yswl.yswletc.entity.Detail;
import com.yswl.yswletc.entity.Orders;
import com.yswl.yswletc.entity.User;
import com.yswl.yswletc.service.OrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * User: jang
 * Date: 2019/10/23
 * Time: 17:17
 * Description: No Description
 */
@Service
public class OrderServiceImpl implements OrdersService {

    @Autowired
    private OrdersMapper ordersMapper;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private BankCardMapper bankCardMapper;

    @Autowired
    private DetailMapper detailMapper;

    @Override
    @Transactional
    public ResultVo ordersAdd(Orders orders) {
        try {
            //查询该订单的银行卡信息
            BankCard bankCard = bankCardMapper.selectById(orders.getBid());
            //查询该订单的用户信息
            User user = userMapper.selectById(orders.getUid());
            int i = user.getWallet().compareTo(orders.getAllprice());
            if ( !(i > -1) ){
                return ResultUtil.exec(false,"ERROR","提现金额不能大于奖金");
            }
            user.setWallet(user.getWallet().subtract(orders.getAllprice()));//减去存款
            //同步银行卡信息进提现表
            orders.setUname(user.getName());//发起提款用户姓名
            orders.setName(bankCard.getName());//持卡人姓名
            orders.setNumber(bankCard.getBankcardnumber());//银行卡号
            orders.setBankname(bankCard.getBankname());//银行名
            orders.setCardpath(bankCard.getCardpath());//银行卡图片路径
            orders.setCreationtime(new Date());//发起时间

            userMapper.updateById(user);//减款入库
            ordersMapper.insert(orders);//入库


            //记录明细
            Detail detail = new Detail();
            detail.setUid(user.getId());
            detail.setSid(orders.getId());
            detail.setAction(2);
            detail.setMoney(orders.getAllprice()); //变动金额
            detail.setBalance(user.getWallet());//余额
            detail.setCreationtime(new Date());
            detail.setRemark("金额提现扣除");
            detailMapper.insert(detail); //入库

            return ResultUtil.exec(true,"OK","发起成功");
        }catch (Exception e){
            e.printStackTrace();
            return ResultUtil.exec(false,"ERROR","发起失败");
        }
    }

    @Override
    public ResultVo ordersQueryByUid(Integer uid) {
        try {
            QueryWrapper queryWrapper = new QueryWrapper();
            queryWrapper.eq("uid",uid);
            List list = ordersMapper.selectList(queryWrapper);
            return ResultUtil.exec(true,"OK",list);
        }catch (Exception e){
            e.printStackTrace();
            return ResultUtil.exec(false,"ERROR","网络错误");
        }
    }

    /**
     *
     */
    @Override
    public ResultVo ordersAffirm(Integer id, String comment, String receiptpath) {
        try {
            Orders orders = ordersMapper.selectById(id);
            orders.setComment(comment); //备注
            orders.setReceiptpath(receiptpath);//回执图片路径
            orders.setStatus(1);
            orders.setRemittime(new Date());
            ordersMapper.updateById(orders);
            return ResultUtil.exec(true,"OK","完成核对");
        }catch (Exception e){
            e.printStackTrace();
            return ResultUtil.exec(true,"OK","网络错误");
        }
    }
}
