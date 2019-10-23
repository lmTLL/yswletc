package com.yswl.yswletc.service;

import com.yswl.yswletc.common.vo.ResultVo;
import com.yswl.yswletc.entity.BankCard;

/**
 * User: jang
 * Date: 2019/10/23
 * Time: 9:08
 * Description: No Description
 */
public interface BankCardService {

    ResultVo bankCardAdd(BankCard bankCard);

    ResultVo bankCardDelete(Integer id);

    ResultVo bankCardQueryAll();

    ResultVo bankCardQueryByuid(Integer uid);
}
