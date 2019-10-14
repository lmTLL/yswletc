package com.yswl.yswletc.service.serviceimpl;

import com.yswl.yswletc.dao.ProjectMapper;
import com.yswl.yswletc.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProjectServiceImpl implements ProjectService {

    @Autowired
    private ProjectMapper projectMapper;

}
