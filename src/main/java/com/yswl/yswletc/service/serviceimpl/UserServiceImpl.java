package com.yswl.yswletc.service.serviceimpl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.yswl.yswletc.common.utils.ResultUtil;
import com.yswl.yswletc.common.vo.ResultVo;
import com.yswl.yswletc.dao.UserMapper;
import com.yswl.yswletc.entity.User;
import com.yswl.yswletc.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;
    /**
     * 登入
     * @param user
     * @return
     */
    @Override
    public ResultVo userLogin(User user) {
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("phone",user.getPhone());
        List<User> list = userMapper.selectList(queryWrapper);
        for (User user1 : list) {
            if (user1 != null){
                if(user1.getPassword().equals(user.getPassword()) && user1.getName().equals(user.getName())){//密码正确登入成功
                    user1.setPassword(null);
                    return ResultUtil.exec(true,"OK",user1);
                }else {
                    return ResultUtil.exec(false,"ERROR","账号密码有误");
                }
            }
        }
        return ResultUtil.exec(false,"ERROR","未找到该用户");
    }

    /**
     * 邀请用户
     * @param user
     * @return
     */
    @Override
    public ResultVo userRegister(User user) {
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("phone",user.getPhone());
        List<User> list = userMapper.selectList(queryWrapper);
        for (User user1 : list) {
            if (user1 != null){
                return ResultUtil.exec(false,"ERROR","该手机号已存在");
            }
        }
        userMapper.insert(user);
        return ResultUtil.exec(true,"OK","邀请成功");
    }

    /**
     * 修改密码
     * @return
     */
    @Override
    public ResultVo userAlterPassword(String name, String phone, String password, String newPassword) {
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("phone",phone);
        List<User> list = userMapper.selectList(queryWrapper);
        for (User user : list) {
            if (user != null) {
                if (user.getName().equals(name) && user.getPassword().equals(password)) {
                    user.setPassword(newPassword);
                    userMapper.updateById(user);
                    return ResultUtil.exec(true,"OK","修改成功");
                } else {
                    return ResultUtil.exec(false,"ERROR","用户名或密码错误");
                }
            }
        }
        return ResultUtil.exec(false,"ERROR","未查找到该用户");
    }
}
