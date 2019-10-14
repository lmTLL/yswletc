package com.yswl.yswletc.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.yswl.yswletc.common.fileuitil.ResUtils;
import com.yswl.yswletc.common.utils.ResultUtil;
import com.yswl.yswletc.common.vo.ResultVo;
import com.yswl.yswletc.dao.UserMapper;
import com.yswl.yswletc.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
public class ResController {
    @Autowired
    private UserMapper userMapper;

    @PostMapping("/uploadImg")
    public ResultVo uploadImg(@RequestParam("file")MultipartFile file) {
        ResUtils resUtils = new ResUtils();
        return resUtils.uploadPictures(file);
    }
    @PostMapping("/testll")
    public ResultVo userLogin(User user) {
        user.setPassword("123456");
            QueryWrapper queryWrapper = new QueryWrapper();
            queryWrapper.eq("phone",156495910);
            List<User> list = userMapper.selectList(queryWrapper);
            for (User user1 : list) {
                if (user1 != null){
                    if(user1.getPassword().equals(user.getPassword())){//密码正确登入成功
                        user1.setPassword(null);
                        return ResultUtil.exec(true,"OK",user1);
                    }else {
                        return ResultUtil.exec(false,"ERROR","密码错误");
                    }
                }
            }
            return ResultUtil.exec(false,"ERROR","未找到该用户");
        }

}

