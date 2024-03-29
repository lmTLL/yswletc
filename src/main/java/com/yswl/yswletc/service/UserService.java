package com.yswl.yswletc.service;

import com.yswl.yswletc.common.vo.ResultVo;
import com.yswl.yswletc.entity.User;

public interface UserService {

    ResultVo userLogin(User user);

    ResultVo userRegister(User user);

    ResultVo userAlterPassword(String name, String phone, String password, String newPassword);

    ResultVo userUpdataCommissionById(User user);

    ResultVo userMyteamById(Integer id);

    ResultVo userQueryAllByPaging(Integer current, Integer size,String name,String phone);

    ResultVo userQueryById(Integer id);

    ResultVo userUpdateOpenid(Integer id, String code);
}
