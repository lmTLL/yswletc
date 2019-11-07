package com.yswl.yswletc.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yswl.yswletc.common.utils.RecursionUtil;
import com.yswl.yswletc.common.utils.ResultUtil;
import com.yswl.yswletc.common.vo.ResultVo;
import com.yswl.yswletc.dao.*;
import com.yswl.yswletc.entity.*;
import com.yswl.yswletc.service.AchievementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Service
public class AchieventmentServiceImpl implements AchievementService {

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Autowired
    private RecursionUtil recursionUtil;

    @Autowired
    private AchievementMapper achievementMapper;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private ProjectMapper projectMapper;

    @Autowired
    private DetailMapper detailMapper;

    /**
     * 提交业绩
     */
    @Override
    public ResultVo achievementSubmit(Achievement achievement) {
        User user = userMapper.selectById(achievement.getUid());
        String uname = user.getName();//当前用户姓名
        BigDecimal umoney = user.getCommission(); //当前用户的佣金

        //获取当前提报的项目
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("id", achievement.getPid());
        List<Project> list = projectMapper.selectList(queryWrapper);
        BigDecimal pmoney = null;
        for (Project project : list) {
            pmoney = project.getCommission(); //获取当前项目佣金
            //当前项目数减一
            project.setRemainders(project.getRemainders()-1);
            if (project.getRemainders()==0){
                project.setState(0);
            }
            projectMapper.updateById(project);//修改项目状态
        }
        try {
            achievement.setSubmittime(new Date());
            achievement.setCommission(umoney.add(pmoney));
            achievement.setUname(uname);
            achievementMapper.insert(achievement);

            //提报缓存
            String s1 = redisTemplate.opsForValue().get("achievement");
            if (s1 == null){
                s1 = "0";
            }
            Integer orders = Integer.valueOf(s1);
            redisTemplate.opsForValue().set("achievement", String.valueOf(orders+1));

            return ResultUtil.exec(true, "OK", "提交成功");
        }catch (Exception e){
            e.printStackTrace();
            return ResultUtil.exec(false, "ERROR", "网络错误");
        }
    }

    /**
     *按当前登入用户id查询业绩
     */
    @Override
    public ResultVo achievementqueryByUid(Integer uid) {
        try {
            QueryWrapper queryWrapper = new QueryWrapper();
            queryWrapper.orderByDesc("id");
            queryWrapper.eq("uid",uid);
            queryWrapper.orderByAsc("submittime");//按时间排序
            List<Achievement> list = achievementMapper.selectList(queryWrapper);
            return ResultUtil.exec(true, "OK", list);
        }catch (Exception e){
            e.printStackTrace();
            return ResultUtil.exec(false, "ERROR", "网络错误");
        }

    }

    @Override
    public ResultVo AchievementQueryAll() {
        try {
            List<Achievement> list = achievementMapper.selectList(null);
            return ResultUtil.exec(true, "OK", list);
        }catch (Exception e){
            e.printStackTrace();
            return ResultUtil.exec(false, "ERROR", "网络错误");
        }
    }

    @Override
    public ResultVo achievementQueryByCondition(String itemname, String uname, String username, String phone, String carid, Integer state, Integer day, Integer current, Integer size) {
        try {
            //传值-1代表查询全部
            if (day == -1){
                day = 1000;
            }

            if (current == null || size == null){
                current =1;
                size = 99999;
            }
            IPage<Achievement> page = new Page<Achievement>(current,size);
            QueryWrapper queryWrapper = new QueryWrapper();
            if (!(itemname == "" || itemname == null)){
                queryWrapper.eq("itemname",itemname);
            }
            if (!(uname == "" || uname == null)){
                queryWrapper.eq("uname",uname);
            }
            if (!(username == "" || username == null)){
                queryWrapper.eq("username",username);
            }
            if (!(phone == "" || phone == null)){
                queryWrapper.eq("phone",phone);
            }
            if (!(carid == "" || carid == null)){
                queryWrapper.eq("carid",carid);
            }
            if (!(state == null)){
                queryWrapper.eq("state",state);
            }
            if ((itemname == "" || itemname == null) && (uname == "" || uname == null) && (username == "" || username == null) && (phone == "" || phone == null) && (carid == "" || carid == null) && (state == null)){
                queryWrapper.last("where DATE_SUB(CURDATE(), INTERVAL "+day+" DAY) <= date(submittime) ORDER BY id DESC ");
            }else {
                queryWrapper.last("and DATE_SUB(CURDATE(), INTERVAL "+day+" DAY) <= date(submittime) ORDER BY id DESC ");
            }
            IPage iPage = achievementMapper.selectPage(page, queryWrapper);
            return ResultUtil.exec(true,"OK",iPage);
        }catch (Exception e){
            e.printStackTrace();
            return ResultUtil.exec(false,"ERROR","网络错误");
        }
    }

    @Override
    public ResultVo achievementQueryById(Integer id) {
        Achievement achievement = achievementMapper.selectById(id);
        return ResultUtil.exec(true,"OK",achievement);
    }

    @Override
    @Transactional
    public ResultVo achievementAudit(Integer id, Integer state, String reason) {
        if (state == 1){//审核通过
            Achievement achievement = achievementMapper.selectById(id);//按id查询业绩

            achievement.setState(state);
            achievement.setReason(reason);
            achievementMapper.updateById(achievement);//修改业绩状态

            //查询业绩由谁提交
            QueryWrapper queryWrapper = new QueryWrapper();
            queryWrapper.eq("id", achievement.getUid());
            List<User> list = userMapper.selectList(queryWrapper);

            User user = list.get(0);
            Detail detail = new Detail();
            if (user != null){
                //给用户加钱
                //奖金=业绩佣金(项目佣金+用户佣金)
                BigDecimal bonus = achievement.getCommission();

                System.out.println("奖金bonus"+bonus);
                System.out.println("钱包"+user.getWallet());

                user.setWallet(user.getWallet().add(bonus));//钱包额度=钱包+奖金
                userMapper.updateById(user);//入库

                detail.setUid(user.getId()); //用户id
                detail.setSid(id); //标记id
                detail.setAction(1);
                detail.setMoney(bonus);
                detail.setBalance(user.getWallet());
                detail.setCreationtime(new Date());
                detail.setRemark("提报业绩奖励");

                detailMapper.insert(detail);

                //查询用户是否有上级+并给上级加钱
                boolean back1 = recursionUtil.back(user,id);
                return ResultUtil.exec(true,"OK","审核完成");
            }
        }else if (state == 2){//审核不通过
            Achievement achievement = achievementMapper.selectById(id);
            achievement.setState(state);
            achievement.setReason(reason);
            achievementMapper.updateById(achievement);
            return ResultUtil.exec(true,"OK","审核完成");
        }
        return ResultUtil.exec(false,"ERROR","条件不满足");
    }

    @Override
    public ResultVo AchievementqueryByidAndDate(Integer id, Integer day) {
        try {
            List<Achievement> list = achievementMapper.AchievementqueryByidAndDate(id,day);
            return ResultUtil.exec(true,"OK",list);
        }catch (Exception e){
            e.printStackTrace();
            return ResultUtil.exec(false,"REEOR","网络错误");
        }
    }
}
