package com.yswl.yswletc.common.fileuitil;

import com.github.tobato.fastdfs.domain.StorePath;
import com.github.tobato.fastdfs.service.FastFileStorageClient;
import com.yswl.yswletc.common.utils.ResultUtil;
import com.yswl.yswletc.common.vo.ResultVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * User: jang
 * Date: 2019/10/14
 * Time: 13:07
 * Description: No Description
 */
public class ResUtils {

    @Autowired
    private FastFileStorageClient fastFileStorageClient;

    public synchronized ResultVo uploadPictures(MultipartFile file){
        Map<String, String> map = new HashMap<>();
        //获得上传的图片名称
        String fname = file.getOriginalFilename();
        //获得上传的图片大小
        long flength = file.getSize();
        //截取后缀
        int index = fname.lastIndexOf(".");
        String houzui = fname.substring(index + 1);

        //上传图片到FastDFS上

        try {
            StorePath storePath = fastFileStorageClient.uploadImageAndCrtThumbImage(
                    file.getInputStream(),
                    flength,
                    houzui,
                    null
            );
            map.put("filepath", storePath.getFullPath());
            return ResultUtil.exec(true,"OK",map);
        } catch (IOException e) {
            e.printStackTrace();
            return ResultUtil.exec(false,"ERROR","网络错误");
        }
    }
}
