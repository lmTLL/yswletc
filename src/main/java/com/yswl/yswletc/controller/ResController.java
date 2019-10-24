package com.yswl.yswletc.controller;

import com.github.tobato.fastdfs.domain.fdfs.StorePath;
import com.github.tobato.fastdfs.service.FastFileStorageClient;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.yswl.yswletc.common.utils.Base64Util;
import com.yswl.yswletc.common.utils.HttpUtil;
import com.yswl.yswletc.common.utils.ResultUtil;
import com.yswl.yswletc.common.utils.StrUrl;
import com.yswl.yswletc.common.vo.ResultVo;
import com.yswl.yswletc.dao.AchievementMapper;
import com.yswl.yswletc.dao.NewAchievementMapper;
import com.yswl.yswletc.dao.UserMapper;
import com.yswl.yswletc.entity.Achievement;
import com.yswl.yswletc.entity.BankCard;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.net.URLEncoder;
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

    @Autowired
    private AchievementMapper achievementMapper;

    @Autowired
    private NewAchievementMapper newAchievementMapper;

    @PostMapping("/uploadImg")
    public ResultVo uploadImg(@RequestParam("file")MultipartFile file) {
        synchronized (fastFileStorageClient) {
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
     * 银行卡接口
     */
    @PostMapping("/uploadBanKcarImg")
    public ResultVo uploadBanKcarImg(@RequestParam("file")MultipartFile file){
        BankCard bankCard = new BankCard();
        Map map = new HashMap();
        // 银行卡识别url
        String bankcardIdentificate = "https://aip.baidubce.com/rest/2.0/ocr/v1/bankcard";
        // 本地图片路径
        try {
            if (file == null){
                return ResultUtil.exec(true, "ERROR", "无法识别,请核对后再上传");
            }
            InputStream inputStream = file.getInputStream();
            byte[] imageFromNetByUrl = StrUrl.readInputStream(inputStream);

            String imgStr = Base64Util.encode(imageFromNetByUrl);
            String params = URLEncoder.encode("image", "UTF-8") + "=" + URLEncoder.encode(imgStr, "UTF-8");
            /**
             * 客户端可自行缓存，过期后重新获取。
             */
            String accessToken = "24.38772a1ef68c9e961e7cf60e16edd32d.2592000.1574323066.282335-17472678";
            String result = HttpUtil.post(bankcardIdentificate, accessToken, params);
            Gson gson = new Gson();
            JsonObject jsonObject = gson.fromJson(result, JsonObject.class).get("result").getAsJsonObject();
            String number = String.valueOf(jsonObject.get("bank_card_number")).replace("\"", "");
            String valid = String.valueOf(jsonObject.get("valid_date")).replace("\"", "");
            String type = String.valueOf(jsonObject.get("bank_card_type"));
            String bname = String.valueOf(jsonObject.get("bank_name")).replace("\"", "");
            System.out.println(type);
            if (bname.equals(null) || bname.equals("")){
                return ResultUtil.exec(true, "ERROR", "无法识别,请核对后再上传");
            }
            if (type.equals("1")){
                map.put("Bank_card_type","借记卡");
            }else if (type.equals("2")){
                map.put("Bank_card_type","信用卡");
            }else if (type.equals("0")){
                return ResultUtil.exec(true, "ERROR", "无法识别,请核对后再上传");
            }
            //上传图片路径
            synchronized (fastFileStorageClient) {
                String fname = file.getOriginalFilename();
                long flength = file.getSize();
                int index = fname.lastIndexOf(".");
                String houzui = fname.substring(index + 1);
                StorePath storePath = fastFileStorageClient.uploadFile(
                        file.getInputStream(),
                        flength,
                        houzui,
                        null);
                    map.put("filepath", imgPath+storePath.getFullPath());
            }

            map.put("Bank_name",bname);
            map.put("Bank_card_number",number);
            map.put("Valid_date",valid);
            System.out.println(number);
            return ResultUtil.exec(true,"OK",map);
        } catch (Exception e) {
            e.printStackTrace();
            return ResultUtil.exec(true, "ERROR", "无法识别,请核对后再上传");
        }
    }

    /**
     * 测试接口
     */
    @GetMapping("/testll")
    public ResultVo userLogin() {
        List<Achievement> list = achievementMapper.queryAchievementByDay(365);
        Integer integer = newAchievementMapper.insertAll(list);
        return ResultUtil.exec(true,"OK",list);
    }


}

