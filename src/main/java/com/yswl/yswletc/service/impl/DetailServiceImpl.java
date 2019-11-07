package com.yswl.yswletc.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.yswl.yswletc.common.utils.ResultUtil;
import com.yswl.yswletc.common.vo.ResultVo;
import com.yswl.yswletc.dao.DetailMapper;
import com.yswl.yswletc.service.DetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * User: jang
 * Date: 2019/10/24
 * Time: 12:31
 * Description: No Description
 */
@Service
public class DetailServiceImpl implements DetailService {

    @Autowired
    private DetailMapper detailMapper;

    @Override
    public ResultVo detailQueryByUid(Integer id) {
        try {
            QueryWrapper queryWrapper = new QueryWrapper();
            queryWrapper.eq("uid",id);
            queryWrapper.orderByDesc("id");
            List list = detailMapper.selectList(queryWrapper);
            return ResultUtil.exec(true,"OK", list);
        } catch (Exception e){
            e.printStackTrace();
            return ResultUtil.exec(true,"OK","网络错误");
        }
    }
}
