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
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Service
public class AchieventmentServiceImpl implements AchievementService {
    @Autowired
    private RecursionUtil recursionUtil;

    @Autowired
    private AchievementMapper achievementMapper;

    @Autowired
    private NewAchievementMapper newAchievementMapper;

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
        String uname = user.getUname();//当前用户姓名
        BigDecimal umoney = user.getCommission(); //当前用户的佣金

        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("id", achievement.getPid());
        List<Project> list = projectMapper.selectList(queryWrapper);
        BigDecimal pmoney = null;
        for (Project project : list) {
            pmoney = project.getCommission(); //获取当前项目佣金
        }
        try {
            achievement.setSubmittime(new Date());
            achievement.setCommission(umoney.add(pmoney));
            achievement.setUname(uname);
            achievementMapper.insert(achievement);
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
            //按时间查找并写入虚拟表
            List<Achievement> lists = achievementMapper.queryAchievementByDay(day);
            if (lists.size() == 0){
                return ResultUtil.exec(true,"OK",lists);
            }
            Integer integer = newAchievementMapper.insertAll(lists);
            //对虚拟表进行条件查询
            if (current == null || size == null){
                current =1;
                size = 99999;
            }
            IPage<NewAchievement> page = new Page<NewAchievement>(current,size);
            QueryWrapper queryWrapper = new QueryWrapper();
            if (itemname != "" && itemname != null){
                queryWrapper.eq("itemname",itemname);
            }
            if (uname != "" && uname != null){
                queryWrapper.eq("uname",uname);
            }
            if (username != ""&&username != null){
                queryWrapper.eq("username",username);
            }
            if (phone != "" && phone != null){
                queryWrapper.eq("phone",phone);
            }
            if (carid != "" && carid != null){
                queryWrapper.eq("carud",carid);
            }
            if (state != null){
                queryWrapper.eq("state",state);
            }
            IPage iPage = newAchievementMapper.selectPage(page, queryWrapper);
            return ResultUtil.exec(true,"OK",iPage);
        }catch (Exception e){
            e.printStackTrace();
            return ResultUtil.exec(false,"ERROR","网络错误");
        }finally {
            newAchievementMapper.deleteAll(); //清空虚拟表
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
                //奖金=用户佣金+项目佣金
                BigDecimal bonus = user.getCommission().add(achievement.getCommission());
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