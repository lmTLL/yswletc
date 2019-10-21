package com.yswl.yswletc.service.serviceimpl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.yswl.yswletc.common.utils.ResultUtil;
import com.yswl.yswletc.common.vo.ResultVo;
import com.yswl.yswletc.dao.AdministratorMapper;
import com.yswl.yswletc.entity.Administrator;
import com.yswl.yswletc.service.AdministratorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * User: jang
 * Date: 2019/10/17
 * Time: 9:29
 * Description: No Description
 */
@Service
public class AdministratorServiceImpl implements AdministratorService {
    @Autowired
    private AdministratorMapper administratorMapper;


    @Override
    public ResultVo administratorLogin(String username, String password) {
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("username", username);
        List<Administrator> list = administratorMapper.selectList(queryWrapper);
        for (Administrator administrator : list) {
            if (administrator == null){
                return ResultUtil.exec(false,"ERROR","用户不存在");
            }else {
                if (administrator.getPassword().equals(password)){
                    return ResultUtil.exec(true,"OK","成功");
                }
            }
        }
        return ResultUtil.exec(false,"ERROR","密码错误");
    }
}