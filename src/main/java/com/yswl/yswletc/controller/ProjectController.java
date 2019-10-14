package com.yswl.yswletc.controller;


import com.yswl.yswletc.common.vo.ResultVo;
import com.yswl.yswletc.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProjectController {
    @Autowired
    private ProjectService projectService;

    @GetMapping("/project/queryAll")
    public ResultVo uploadImg() {
        return projectService.queryProject();
    }

}
