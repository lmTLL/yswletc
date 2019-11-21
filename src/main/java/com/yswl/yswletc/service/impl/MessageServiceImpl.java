package com.yswl.yswletc.service.impl;

import com.yswl.yswletc.common.utils.ResultUtil;
import com.yswl.yswletc.common.utils.WxTemplateUtil;
import com.yswl.yswletc.common.vo.ResultVo;
import com.yswl.yswletc.dao.AchievementMapper;
import com.yswl.yswletc.dao.OrdersMapper;
import com.yswl.yswletc.dao.UserMapper;
import com.yswl.yswletc.entity.Achievement;
import com.yswl.yswletc.entity.Orders;
import com.yswl.yswletc.entity.User;
import com.yswl.yswletc.service.MessageService;
import me.chanjar.weixin.mp.api.WxMpInMemoryConfigStorage;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.api.impl.WxMpServiceImpl;
import me.chanjar.weixin.mp.bean.template.WxMpTemplateData;
import me.chanjar.weixin.mp.bean.template.WxMpTemplateMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

/**
 * User: jang
 * Date: 2019/11/7
 * Time: 15:51
 * Description: No Description
 */
@Service
public class MessageServiceImpl implements MessageService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private AchievementMapper achievementMapper;

    @Autowired
    private OrdersMapper ordersMapper;


    /**
     *群发模板消息
     */
    @Override
    public ResultVo pushMassTexting(String content) {
        List<String> strings = userMapper.queryAllOpenid();

        WxMpInMemoryConfigStorage wxStorage = WxTemplateUtil.templateDeploy();

        WxMpService wxMpService = new WxMpServiceImpl();
        wxMpService.setWxMpConfigStorage(wxStorage);

        List<WxMpTemplateData> data = Arrays.asList(
                new WxMpTemplateData("first","感谢您使用一三网络科技ETC推广平台,请查收最新推广任务，祝您推广愉快！！"),
                new WxMpTemplateData("keyword1",content),
                new WxMpTemplateData("keyword2","ETC推广"),
                new WxMpTemplateData("keyword3", WxTemplateUtil.formatTime()),
                new WxMpTemplateData("keyword4","全国"),
                new WxMpTemplateData("keyword5","ETC推广"),
                new WxMpTemplateData("remark","全国热门ETC项目推广中")
        );
        WxMpTemplateMessage.WxMpTemplateMessageBuilder builder = WxMpTemplateMessage.builder();
        builder.templateId("O93hHo0QUWantm_YDF6xkhBNSUlPV6TopbWjas6ArEY");
        builder.url("m.fhbjkj.com/ETC/");
        try {
            for (String string : strings) {
                if (!(string == null || string.equals(""))){
                    WxMpTemplateMessage templateMessage = builder.toUser(string).build();
                    templateMessage.setData(data);
                    try {
                        wxMpService.getTemplateMsgService().sendTemplateMsg(templateMessage);
                    }catch (Exception e){
                    }
                }
            }
            return ResultUtil.exec(true,"OK","发送成功");
        } catch (Exception e) {
            e.printStackTrace();
            return ResultUtil.exec(false,"ERROR","推送失败：" + e.getMessage());
        }
    }

    /**
     * 业绩通过审核
     */
    @Override
    public ResultVo achievementPass(Integer id, String comment) {
        Achievement achievement = achievementMapper.selectById(id);
        User user = userMapper.selectById(achievement.getUid());
        WxMpInMemoryConfigStorage wxStorage = WxTemplateUtil.templateDeploy();
        WxMpService wxMpService = new WxMpServiceImpl();
        wxMpService.setWxMpConfigStorage(wxStorage);

        List<WxMpTemplateData> data = Arrays.asList(
                new WxMpTemplateData("first","您的业绩核通过"),
                new WxMpTemplateData("keyword1",achievement.getId()+""),
                new WxMpTemplateData("keyword2",WxTemplateUtil.formatTime()),
                new WxMpTemplateData("remark","车主名为"+achievement.getUsername()+"通过     "+comment)
        );

        try {
            //2,推送消息
            WxMpTemplateMessage templateMessage = WxMpTemplateMessage.builder()
                    .toUser(user.getOpenid())
                    .templateId("EQy2jYxzZKiTEwVVyfhaYbVZ5rL6J6iloOwS6rlKahw")//模版id
                    .url("m.fhbjkj.com/ETC/")//点击模版消息要访问的网址
                    .build();

            //3,设置模板消息内容
            templateMessage.setData(data);
            wxMpService.getTemplateMsgService().sendTemplateMsg(templateMessage);
            return ResultUtil.exec(true,"OK","发送成功");
        } catch (Exception e) {
            e.printStackTrace();
            return ResultUtil.exec(false,"ERROR","推送失败：" + e.getMessage());
        }
    }

    @Override
    public ResultVo achievementject(Integer id, String comment) {
        Achievement achievement = achievementMapper.selectById(id);
        User user = userMapper.selectById(achievement.getUid());
        WxMpInMemoryConfigStorage wxStorage = WxTemplateUtil.templateDeploy();
        WxMpService wxMpService = new WxMpServiceImpl();
        wxMpService.setWxMpConfigStorage(wxStorage);

        List<WxMpTemplateData> data = Arrays.asList(
                new WxMpTemplateData("first","您好，您提交的ETC信息未审核通过"),
                new WxMpTemplateData("keyword1",achievement.getUsername()),
                new WxMpTemplateData("keyword2","审核未通过"),
                new WxMpTemplateData("keyword3",comment),
                new WxMpTemplateData("remark","请您修改相关信息后再提交")
        );

        try {
            //2,推送消息
            WxMpTemplateMessage templateMessage = WxMpTemplateMessage.builder()
                    .toUser(user.getOpenid())
                    .templateId("XKTpNH76cXDcu42-n0COCrYHS-Uou6TrIzyb6p9zS6E")//模版id
                    .url("m.fhbjkj.com/ETC/")//点击模版消息要访问的网址
                    .build();
            templateMessage.setData(data);
            wxMpService.getTemplateMsgService().sendTemplateMsg(templateMessage);
            return ResultUtil.exec(true,"OK","发送成功");
        } catch (Exception e) {
            e.printStackTrace();
            return ResultUtil.exec(false,"ERROR","推送失败：" + e.getMessage());
        }
    }

    @Override
    public ResultVo pushOrdersPass(Integer id) {
        Orders orders = ordersMapper.selectById(id);
        User user = userMapper.selectById(orders.getUid());
        WxMpInMemoryConfigStorage wxStorage = WxTemplateUtil.templateDeploy();
        WxMpService wxMpService = new WxMpServiceImpl();
        wxMpService.setWxMpConfigStorage(wxStorage);

        List<WxMpTemplateData> data = Arrays.asList(
                new WxMpTemplateData("first", "您好，您的提现已到账，请注意查收"),
                new WxMpTemplateData("keyword1", orders.getNumber()),
                new WxMpTemplateData("keyword2", String.valueOf(orders.getAllprice())),
                new WxMpTemplateData("remark", WxTemplateUtil.formatTime())
        );

        try {
            WxMpTemplateMessage templateMessage = WxMpTemplateMessage.builder()
                    .toUser(user.getOpenid())
                    .templateId("mA_YdtSlRUNntbu3Pbdw55paD5QJiRQudFeDiRWfrR0")//模版id
                    .url("m.fhbjkj.com/ETC/")//点击模版消息要访问的网址
                    .build();
            templateMessage.setData(data);
            wxMpService.getTemplateMsgService().sendTemplateMsg(templateMessage);
            return ResultUtil.exec(true, "OK", "发送成功");
        } catch (Exception e) {
            e.printStackTrace();
            return ResultUtil.exec(false, "ERROR", "推送失败：" + e.getMessage());
        }
    }
    /**
     * 提现驳回
     */
    @Override
    public ResultVo pushOrdersReject(Integer id, String comment) {
        Orders orders = ordersMapper.selectById(id);
        User user = userMapper.selectById(orders.getUid());
        WxMpInMemoryConfigStorage wxStorage = WxTemplateUtil.templateDeploy();
        WxMpService wxMpService = new WxMpServiceImpl();
        wxMpService.setWxMpConfigStorage(wxStorage);

        List<WxMpTemplateData> data = Arrays.asList(
                new WxMpTemplateData("first", "您好，您的提现申请失败"),
                new WxMpTemplateData("keyword1", comment),
                new WxMpTemplateData("keyword2", String.valueOf(orders.getAllprice())),
                new WxMpTemplateData("remark", "感谢您使用一三网络ETC推广平台")
        );
        try {
            WxMpTemplateMessage templateMessage = WxMpTemplateMessage.builder()
                    .toUser(user.getOpenid())
                    .templateId("nw7yJGU0QIAspWNfFLWYZAkCHRn3N4m0wx3vUXVDlmI")//模版id
                    .url("m.fhbjkj.com/ETC/")//点击模版消息要访问的网址
                    .build();
            templateMessage.setData(data);
            wxMpService.getTemplateMsgService().sendTemplateMsg(templateMessage);
            return ResultUtil.exec(true, "OK", "发送成功");
        } catch (Exception e) {
            e.printStackTrace();
            return ResultUtil.exec(false, "ERROR", "推送失败：" + e.getMessage());

        }
    }
}
