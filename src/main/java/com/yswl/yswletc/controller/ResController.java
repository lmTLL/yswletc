package com.yswl.yswletc.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.tobato.fastdfs.domain.StorePath;
import com.github.tobato.fastdfs.service.FastFileStorageClient;
import com.yswl.yswletc.common.utils.ResultUtil;
import com.yswl.yswletc.common.vo.ResultVo;
import com.yswl.yswletc.dao.UserMapper;
import com.yswl.yswletc.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin
public class ResController {
    @Value("${img.server}")
    private String imgPath;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private FastFileStorageClient fastFileStorageClient;

    @PostMapping("/uploadImg")
    public ResultVo uploadImg(@RequestParam("file")MultipartFile file) {
        synchronized (this) {
            Map<String, String> map = new HashMap<>();
            String fname = file.getOriginalFilename();
            long flength = file.getSize();
            int index = fname.lastIndexOf(".");
            String houzui = fname.substring(index + 1);
            try {
                StorePath storePath = fastFileStorageClient.uploadFile(
                        file.getInputStream(),
                        flength,
                        houzui,
                        null);
                    map.put("filepath", imgPath+storePath.getFullPath());
                    return ResultUtil.exec(true, "OK", map);
            } catch (Exception e) {
                e.printStackTrace();
                return ResultUtil.exec(false, "ERROR", "网络错误");
            }
        }
    }


    /**
     * 测试接口
     * @param user
     * @return
     */
    @PostMapping("/testll")
    public ResultVo userLogin(User user) {
        user.setPassword("123456");
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("phone",156495910);
        List<User> list = userMapper.selectList(queryWrapper);
        for (User user1 : list) {
            if (user1 != null){
                if(user1.getPassword().equals(user.getPassword())){//密码正确登入成功
                    user1.setPassword(null);
                    return ResultUtil.exec(true,"OK",user1);
                }else {
                    return ResultUtil.exec(false,"ERROR","密码错误");
                }
            }
        }
        return ResultUtil.exec(false,"ERROR","未找到该用户");
    }

}

