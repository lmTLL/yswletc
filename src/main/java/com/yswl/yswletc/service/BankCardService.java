package com.yswl.yswletc.service;

import com.yswl.yswletc.common.vo.ResultVo;

/**
 * User: jang
 * Date: 2019/10/23
 * Time: 9:08
 * Description: No Description
 */
public interface BankCardService {

    ResultVo bankCardAdd(Integer uid, String name, String path);

    ResultVo bankCardDelete(Integer id);

    ResultVo bankCardQueryAll();
}
