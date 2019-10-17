package com.yswl.yswletc.service.serviceimpl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.yswl.yswletc.common.utils.ResultUtil;
import com.yswl.yswletc.common.vo.ResultVo;
import com.yswl.yswletc.dao.ProjectMapper;
import com.yswl.yswletc.entity.Project;
import com.yswl.yswletc.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ProjectServiceImpl implements ProjectService {

    @Autowired
    private ProjectMapper projectMapper;

    /**
     *获取项目列表
     */
    @Override
    public ResultVo queryProject() {
        try {
            QueryWrapper queryWrapper = new QueryWrapper();
            queryWrapper.eq("state", 1);
            List<Project> projects = projectMapper.selectList(queryWrapper);
            return ResultUtil.exec(true, "OK", projects);
        } catch (Exception e) {
            return ResultUtil.exec(false, "ERROR", "网络错误");
        }
    }

    /**
     *按id获取项目详情
     */
    @Override
    public ResultVo projectqueryById(Integer id) {
        try {
            QueryWrapper queryWrapper = new QueryWrapper();
            queryWrapper.eq("state", 1);
            queryWrapper.eq("id", id);
            List<Project> list = projectMapper.selectList(queryWrapper);
            Project project = list.get(0);
            return ResultUtil.exec(false, "ERROR", project);
        } catch (Exception e) {
            return ResultUtil.exec(false, "ERROR", "网络错误");
        }
    }

    /**
     * 项目添加
     */
    @Override
    public ResultVo projectAdd(Project project) {
        try {
            project.setJoindate(new Date());
            projectMapper.insert(project);
            return ResultUtil.exec(true, "OK","添加成功");
        }catch (Exception e){
            return ResultUtil.exec(false, "ERROR", "网络错误");
        }
    }

    /**
     *上架项目
     */
    @Override
    public ResultVo projectUp(Integer id) {
        try {
            Project project = projectMapper.selectById(id);
            project.setState(1);
            projectMapper.updateById(project);
            return ResultUtil.exec(true, "OK","上架成功");
        }catch (Exception e){
            return ResultUtil.exec(false, "ERROR", "网络错误");
        }
    }

    /**
     * 下架项目
     */
    @Override
    public ResultVo projectOut(Integer id) {
        try {
            Project project = projectMapper.selectById(id);
            project.setState(0);
            projectMapper.updateById(project);
            return ResultUtil.exec(true, "OK","下架成功");
        }catch (Exception e){
            return ResultUtil.exec(false, "ERROR", "网络错误");
        }
    }

    /**
     * 修改项目
     */
    @Override
    public ResultVo projectUpdate(Project project) {
        try {

            Project project1 = projectMapper.selectById(project.getId());
            project1.setLog(project.getLog());
            project1.setProjectname(project.getProjectname());
            project1.setIntroduce(project.getIntroduce());
            project1.setRemainders(project.getRemainders());
            project1.setCommission(project.getCommission());
            project1.setQrcodepath(project.getQrcodepath());
            project1.setDocumentpath(project.getDocumentpath());
            projectMapper.updateById(project1);
            return ResultUtil.exec(true, "OK","修改成功");
        }catch (Exception e){
            return ResultUtil.exec(false, "ERROR", "网络错误");
        }
    }

}

