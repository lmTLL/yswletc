package com.yswl.yswletc.service.serviceimpl;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.yswl.yswletc.common.utils.Base64Util;
import com.yswl.yswletc.common.utils.HttpUtil;
import com.yswl.yswletc.common.utils.ResultUtil;
import com.yswl.yswletc.common.utils.StrUrl;
import com.yswl.yswletc.common.vo.ResultVo;
import com.yswl.yswletc.dao.BankCardMapper;
import com.yswl.yswletc.dao.UserMapper;
import com.yswl.yswletc.entity.BankCard;
import com.yswl.yswletc.entity.User;
import com.yswl.yswletc.service.BankCardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.net.URLEncoder;
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
            bankCard.setCreation_time(new Date());
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
}
