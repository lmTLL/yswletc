package com.yswl.yswletc.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.tobato.fastdfs.domain.StorePath;
import com.github.tobato.fastdfs.service.FastFileStorageClient;
import com.yswl.yswletc.common.utils.ResultUtil;
import com.yswl.yswletc.common.vo.ResultVo;
import com.yswl.yswletc.dao.NewStudentMapper;
import com.yswl.yswletc.dao.StudentMapper;
import com.yswl.yswletc.dao.UserMapper;
import com.yswl.yswletc.entity.NewStudent;
import com.yswl.yswletc.entity.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
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
    private StudentMapper studentMapper;

    @Autowired
    private NewStudentMapper newStudentMapper;

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
     * @return
     */
    @GetMapping("/testll")
    public ResultVo userLogin() {
        try {
            List<Student> lists = studentMapper.queryStudentByDay(1);
            newStudentMapper.insertAll(lists);
            IPage<NewStudent> page = new Page<>(1,3);
            IPage page1 = newStudentMapper.selectPage(page, null);
            return ResultUtil.exec(true,"REEOR",page1);

        }catch (Exception e){
            e.printStackTrace();
            return ResultUtil.exec(true,"REEOR","网络错误");
        }finally {
            newStudentMapper.deleteAll();
        }
    }

}

