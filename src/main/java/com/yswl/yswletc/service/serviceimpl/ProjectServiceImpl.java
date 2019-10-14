package com.yswl.yswletc.service.serviceimpl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.yswl.yswletc.common.utils.ResultUtil;
import com.yswl.yswletc.common.vo.ResultVo;
import com.yswl.yswletc.dao.ProjectMapper;
import com.yswl.yswletc.entity.Project;
import com.yswl.yswletc.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectServiceImpl implements ProjectService {

    @Autowired
    private ProjectMapper projectMapper;

    @Override
    public ResultVo queryProject() {
        try {
            QueryWrapper queryWrapper = new QueryWrapper();
            queryWrapper.eq("state",1);
            List<Project> projects = projectMapper.selectList(null);
            return ResultUtil.exec(true, "OK", projects);
        }catch (Exception e){
            return ResultUtil.exec(false, "ERROR", "网络错误");
        }
    }
}
