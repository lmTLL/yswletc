package com.yswl.yswletc.service;

import com.yswl.yswletc.common.vo.ResultVo;
import com.yswl.yswletc.entity.User;

public interface UserService {

    ResultVo userLogin(User user);

    ResultVo userRegister(User user);
}
