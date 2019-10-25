package com.yswl.yswletc.controller;

import com.yswl.yswletc.common.vo.ResultVo;
import com.yswl.yswletc.service.DetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * User: jang
 * Date: 2019/10/24
 * Time: 14:15
 * Description: No Description
 */
@RestController
@CrossOrigin
public class DetailController {

    @Autowired
    private DetailService detailService;

    @GetMapping("/detail/queryByUid")
    public ResultVo detailQueryByUid(Integer id){
       return detailService.detailQueryByUid(id);
    }
}
