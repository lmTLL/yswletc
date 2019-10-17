package com.yswl.yswletc.service;

import com.yswl.yswletc.common.vo.ResultVo;
import com.yswl.yswletc.entity.Project;

public interface ProjectService {

    ResultVo queryProject();

    ResultVo projectqueryById(Integer id);

    ResultVo projectAdd(Project project);

    ResultVo projectOut(Integer id);

    ResultVo projectUp(Integer id);

    ResultVo projectUpdate(Project project);
}
