package com.yswl.yswletc.service.serviceimpl;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.yswl.yswletc.common.utils.Base64Util;
import com.yswl.yswletc.common.utils.HttpUtil;
import com.yswl.yswletc.common.utils.ResultUtil;
import com.yswl.yswletc.common.utils.StrUrl;
import com.yswl.yswletc.common.vo.ResultVo;
import com.yswl.yswletc.dao.BankCardMapper;
import com.yswl.yswletc.dao.UserMapper;
import com.yswl.yswletc.entity.BankCard;
import com.yswl.yswletc.entity.User;
import com.yswl.yswletc.service.BankCardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.net.URLEncoder;
import java.util.Date;
import java.util.List;

/**
 * User: jang
 * Date: 2019/10/23
 * Time: 9:10
 * Description: No Description
 */
@Service
public class BanKcardServiceImpl implements BankCardService {

    @Autowired
    private BankCardMapper bankCardMapper;

    @Autowired
    private UserMapper userMapper;


    @Override
    public ResultVo bankCardAdd(Integer uid, String name, String path) {
        User user = userMapper.selectById(uid);
        // 银行卡识别url
        BankCard bankCard = new BankCard();
        String bankcardIdentificate = "https://aip.baidubce.com/rest/2.0/ocr/v1/bankcard";
        try {
            byte[] imageFromNetByUrl = StrUrl.getImageFromNetByUrl(path);//网络图片转为二进制组
            if (imageFromNetByUrl == null){
                return ResultUtil.exec(false, "ERROR", "无法识别,请核对后再上传");
            }

            String imgStr = Base64Util.encode(imageFromNetByUrl);
            String params = URLEncoder.encode("image", "UTF-8") + "=" + URLEncoder.encode(imgStr, "UTF-8");
            /**
             * 自行缓存，过期后重新获取。
             */
            String accessToken = "24.38772a1ef68c9e961e7cf60e16edd32d.2592000.1574323066.282335-17472678";
            String result = HttpUtil.post(bankcardIdentificate, accessToken, params);
            Gson gson = new Gson();
            JsonObject jsonObject = gson.fromJson(result, JsonObject.class).get("result").getAsJsonObject();
            String number = String.valueOf(jsonObject.get("bank_card_number")).replace("\"", "");
            String valid = String.valueOf(jsonObject.get("valid_date")).replace("\"", "");
            String type = String.valueOf(jsonObject.get("bank_card_type"));
            String bname = String.valueOf(jsonObject.get("bank_name")).replace("\"", "");
            if (bname.equals(null) || bname.equals("")){
                return ResultUtil.exec(false, "ERROR", "无法识别,请核对后再上传");
            }
            if (type.equals(1)){
                bankCard.setBank_card_type("借记卡");
            }else if (type.equals(2)){
                bankCard.setBank_card_type("信用卡");
            }else if (type.equals(0)){
                return ResultUtil.exec(false, "ERROR", "无法识别,请核对后再上传");
            }
            bankCard.setUid(uid); //用户id
            bankCard.setUname(user.getName()); //用户姓名
            bankCard.setName(name); //持卡人姓名
            bankCard.setBank_card_number(number); //卡号
            bankCard.setValid_date(valid); //有效期
            bankCard.setBank_name(bname); //银行名
            bankCard.setCreation_time(new Date()); //绑定时间
            bankCard.setCard_path(path); //图片路径
            bankCardMapper.insert(bankCard);
            return ResultUtil.exec(true, "OK", "绑定成功");
        } catch (Exception e) {
            e.printStackTrace();
            return ResultUtil.exec(false, "ERROR", "无法识别,请核对后再上传");
        }
    }

    @Override
    public ResultVo bankCardDelete(Integer id) {
        try {
            bankCardMapper.deleteById(id);
            return ResultUtil.exec(true, "OK", "删除成功");
        }catch (Exception e){
            e.printStackTrace();
            return ResultUtil.exec(false,"ERROR","网络错误");
        }
    }

    @Override
    public ResultVo bankCardQueryAll() {
        try {
            List<BankCard> bankCards = bankCardMapper.selectList(null);
            return ResultUtil.exec(true,"OK",bankCards);
        }catch (Exception e){
            e.printStackTrace();
            return ResultUtil.exec(false,"ERROR","网络错误");
        }
    }
}
