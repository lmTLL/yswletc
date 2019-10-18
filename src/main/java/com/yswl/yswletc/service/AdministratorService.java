package com.yswl.yswletc.service;

import com.yswl.yswletc.common.vo.ResultVo;

/**
 * User: jang
 * Date: 2019/10/17
 * Time: 9:16
 * Description: No Description
 */
public interface AdministratorService {

    ResultVo administratorLogin(String username, String password);
}
