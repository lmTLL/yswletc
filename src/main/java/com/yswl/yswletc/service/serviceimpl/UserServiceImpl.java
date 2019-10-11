package com.yswl.yswletc.service.serviceimpl;

import com.yswl.yswletc.common.redis.RedisOperator;
import com.yswl.yswletc.common.utils.ResultUtil;
import com.yswl.yswletc.common.vo.ResultVo;
import com.yswl.yswletc.dao.UserMapper;
import com.yswl.yswletc.entity.User;
import com.yswl.yswletc.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private RedisOperator redis;

    @Override
    public ResultVo userLogin(User user) {
        User u = userMapper.selectByName(user.getName());
        if (u!=null){
           if (u.getPassword().equals(user.getPassword())){
               if (u.getPhone().equals(user.getPhone())){

                   redis.set("loginName",
                           u.getName(),
                           1000 * 60 * 30);
                   return ResultUtil.exec(true,"OK","成功登陆");
               }else {
                   return ResultUtil.exec(false,"ERROR","手机号不正确，请核对后再试");
               }
           }else{
               return ResultUtil.exec(false,"ERROR","密码不正确，请重新填写");
           }
        }else{
            return ResultUtil.exec(false,"ERROR","用户名无效");
        }
    }
}
