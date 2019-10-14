package com.yswl.yswletc.controller;

import com.yswl.yswletc.common.fileuitil.ResUtils;
import com.yswl.yswletc.common.vo.ResultVo;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class ResController {

    @PostMapping("/uploadImg")
    public ResultVo uploadImg(MultipartFile file) {
        ResUtils resUtils = new ResUtils();
        return resUtils.uploadPictures(file);
    }
}

