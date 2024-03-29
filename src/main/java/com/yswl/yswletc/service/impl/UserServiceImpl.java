package com.yswl.yswletc.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yswl.yswletc.common.utils.HttpOpenUtil;
import com.yswl.yswletc.common.utils.ResultUtil;
import com.yswl.yswletc.common.vo.ResultVo;
import com.yswl.yswletc.dao.AchievementMapper;
import com.yswl.yswletc.dao.BankCardMapper;
import com.yswl.yswletc.dao.UserMapper;
import com.yswl.yswletc.entity.*;
import com.yswl.yswletc.service.UserService;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private AchievementMapper achievementMapper;

    @Autowired
    private BankCardMapper bankCardMapper;
    /**
     * 登入
     * @param user
     * @return
     */
    @Override
    public ResultVo userLogin(User user) {
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("phone",user.getPhone());
        List<User> list = userMapper.selectList(queryWrapper);
        for (User user1 : list) {
            if (user1 != null){
                if(user1.getPassword().equals(user.getPassword()) && user1.getName().equals(user.getName())){//密码正确登入成功
                    user1.setPassword(null);
                    return ResultUtil.exec(true,"OK",user1);
                }else {
                    return ResultUtil.exec(false,"ERROR","账号密码有误");
                }
            }
        }
        return ResultUtil.exec(false,"ERROR","未找到该用户");
    }

    /**
     * 邀请用户
     */
    @Override
    public ResultVo userRegister(User user) {
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("phone",user.getPhone());
        List<User> list = userMapper.selectList(queryWrapper);
        for (User user1 : list) {
            if (user1 != null){
                return ResultUtil.exec(false,"ERROR","该手机号已存在");
            }
        }
        User user2 = userMapper.selectById(user.getUid());
        System.out.println(user2);
        user.setUname(user2.getName());//添加父级姓名
        user.setJoindate(new Date());
        userMapper.insert(user);
        return ResultUtil.exec(true,"OK","邀请成功");
    }

    /**
     * 修改密码
     * @return
     */
    @Override
    public ResultVo userAlterPassword(String name, String phone, String password, String newPassword) {
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("phone",phone);
        List<User> list = userMapper.selectList(queryWrapper);
        for (User user : list) {
            if (user != null) {
                if (user.getName().equals(name) && user.getPassword().equals(password)) {
                    user.setPassword(newPassword);
                    userMapper.updateById(user);
                    return ResultUtil.exec(true,"OK","修改成功");
                } else {
                    return ResultUtil.exec(false,"ERROR","用户名或密码错误");
                }
            }
        }
        return ResultUtil.exec(false,"ERROR","未查找到该用户");
    }

    /**
     * 修改佣金
     */
    @Override
    public ResultVo userUpdataCommissionById(User user) {
        try {
            User user1 = userMapper.selectById(user.getId());
            if (user1.getUid()==0){
                user1.setCommission(user.getCommission());
                userMapper.updateById(user1);
                return ResultUtil.exec(true,"OK","修改成功");
            }
            User user2 = userMapper.selectById(user1.getUid());
            int i = user2.getCommission().compareTo(user.getCommission());
            if (i>-1){
                user1.setCommission(user.getCommission());
                userMapper.updateById(user1);
                return ResultUtil.exec(true,"OK","修改成功");
            }else {
                return ResultUtil.exec(false,"ERROR","修改佣金不能大于您的佣金");
            }
        }catch (Exception e){
            e.printStackTrace();
            return ResultUtil.exec(false, "ERROR", "网络错误");
        }

    }

    /**
     * 查询我的团队信息
     */
    @Override
    public ResultVo userMyteamById(Integer id) {
        GroupMark groupMark = new GroupMark();
        Map map = new HashMap();
        List<Achievement> TeamAchievements = new ArrayList<>();
        try {
            //自己的信息
            User user = userMapper.selectById(id);
            user.setPassword(null);
            QueryWrapper uWrapper = new QueryWrapper();
            //查询我银行卡
            uWrapper.eq("uid",user.getId());
            List<BankCard> list1 = bankCardMapper.selectList(uWrapper);
            for (BankCard bankCard : list1) {
                user.setBankCard(bankCard);
            }
            //封装传给前台
            map.put("myuser",user);

            if (user.getUid() != 0){//不等于0说明有父级
                //找到父级
                User user2 = userMapper.selectById(user.getUid());
                user2.setPassword(null);
                map.put("father",user2);
            }else {
                //否则为null
                map.put("father",null);
            }

            //查询我的伙伴
            QueryWrapper queryWrapper = new QueryWrapper();
            queryWrapper.eq("uid",id);
            List<User> list = userMapper.selectList(queryWrapper);

            List<Achievement> myAchievements = achievementMapper.selectAllAchievementByTime(id);//查询我的业绩
            groupMark.setMyScore(myAchievements.size());//我的业绩
            groupMark.setMyPartner(list.size());//我的伙伴数量


            for (User user1 : list) { //遍历所有伙伴
                user1.setPassword(null);
                List<Achievement> achievements = achievementMapper.selectAllAchievementByTime(user1.getId());//查询某个伙伴的业绩
                user1.setToday(achievements.size());
                for (Achievement achievement : achievements) {
                    TeamAchievements.add(achievement);//添加进团队业绩中
                }
            }

            groupMark.setTeamScore(TeamAchievements.size());
            map.put("groupMark",groupMark);
            map.put("son",list);
            return ResultUtil.exec(true,"OK",map);
        }catch (Exception e){
            e.printStackTrace();
            return ResultUtil.exec(false, "ERROR", "网络错误");
        }
    }

    /**
     *查询所有用户列表
     */
    @Override
    public ResultVo userQueryAllByPaging(Integer current, Integer size,String name,String phone) {
        IPage<User> page = new Page<User>(current,size);
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("state",1);
        if (name != null && name != ""){
            queryWrapper.eq("name",name);
        }
        if (phone != null && phone != ""){
            queryWrapper.eq("phone",phone);
        }
        IPage iPage = userMapper.selectPage(page, queryWrapper);
        return ResultUtil.exec(true, "OK",iPage);
    }

    @Override
    public ResultVo userQueryById(Integer id) {
        try {
            User user = userMapper.selectById(id);
            user.setPassword(null);
            return ResultUtil.exec(true,"OK",user);
        }catch (Exception e){
            e.printStackTrace();
            return ResultUtil.exec(false,"ERROR","数据库连接异常");
        }
    }

    @Override
    public ResultVo userUpdateOpenid(Integer id, String code) {
        try {
            String result = HttpOpenUtil.sendGet("https://api.weixin.qq.com/sns/oauth2/access_token",
                    "appid=wx48f8adf218161ab0&secret=26bdf10b4dfc24223506a45a170a2d1b&code="+code+"&grant_type=authorization_code");
            JSONObject jsonObject = new JSONObject(result);
            String openid;

            try {
                openid = jsonObject.getString("openid");
            }catch (Exception e){
                e.printStackTrace();
                return ResultUtil.exec(true,"OK",String.valueOf(jsonObject));
            }

            if (openid == null){
                return ResultUtil.exec(true,"OK",String.valueOf(jsonObject));
            }
            User user = userMapper.selectById(id);
            user.setOpenid(openid);
            userMapper.updateById(user);
            return ResultUtil.exec(true,"OK","完成操作");
        }catch (Exception e){
            e.printStackTrace();
            return ResultUtil.exec(false, "ERROR", "网络错误");
        }
    }
}
