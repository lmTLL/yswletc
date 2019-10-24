package com.yswl.yswletc.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.yswl.yswletc.common.utils.ResultUtil;
import com.yswl.yswletc.common.vo.ResultVo;
import com.yswl.yswletc.dao.BankCardMapper;
import com.yswl.yswletc.entity.BankCard;
import com.yswl.yswletc.service.BankCardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * User: jang
 * Date: 2019/10/23
 * Time: 9:10
 * Description: No Description
 */
@Service
public class BanKcardServiceImpl implements BankCardService {

    @Autowired
    private BankCardMapper bankCardMapper;

    @Override
    public ResultVo bankCardAdd(BankCard bankCard) {
        try {
            bankCard.setCreationtime(new Date());
            bankCardMapper.insert(bankCard);

        return ResultUtil.exec(true, "OK", "绑定成功");
        } catch (Exception e) {
            e.printStackTrace();
            return ResultUtil.exec(false, "ERROR", "无法识别,请核对后再上传");
        }
    }

    @Override
    public ResultVo bankCardDelete(Integer id) {
        try {
            bankCardMapper.deleteById(id);
            return ResultUtil.exec(true, "OK", "删除成功");
        }catch (Exception e){
            e.printStackTrace();
            return ResultUtil.exec(false,"ERROR","网络错误");
        }
    }

    @Override
    public ResultVo bankCardQueryAll() {
        try {
            List<BankCard> bankCards = bankCardMapper.selectList(null);
            return ResultUtil.exec(true,"OK",bankCards);
        }catch (Exception e){
            e.printStackTrace();
            return ResultUtil.exec(false,"ERROR","网络错误");
        }
    }

    @Override
    public ResultVo bankCardQueryByuid(Integer uid) {
        try {
            QueryWrapper queryWrapper = new QueryWrapper();
            queryWrapper.eq("uid",uid);
            List list = bankCardMapper.selectList(queryWrapper);
            return  ResultUtil.exec(true,"OK",list);
        }catch (Exception e){
            e.printStackTrace();
            return ResultUtil.exec(false,"ERROR","网络错误");
        }
    }
}
