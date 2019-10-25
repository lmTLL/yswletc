package com.yswl.yswletc.controller;


import com.yswl.yswletc.common.vo.ResultVo;
import com.yswl.yswletc.entity.Project;
import com.yswl.yswletc.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
public class ProjectController {

    @Autowired
    private ProjectService projectService;

    @GetMapping("/project/queryAll")
    public ResultVo projectQueryAll() {
        return projectService.queryProject();
    }
    @GetMapping("/project/queryById")
    public ResultVo projectqueryById(Integer id) {
        return projectService.projectqueryById(id);
    }

    @PostMapping("/project/add")
    public ResultVo projectAdd(Project project) {
        return projectService.projectAdd(project);
    }
    @GetMapping("/project/out")
    public ResultVo projectOut(Integer id){
        return projectService.projectOut(id);
    }
    @GetMapping("/project/up")
    public ResultVo projectUp(Integer id){
        return projectService.projectUp(id);
    }
    @PostMapping("/project/update")
    public ResultVo projectUpdate(Project project){
        return projectService.projectUpdate(project);
    }


}
