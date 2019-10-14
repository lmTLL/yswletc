package com.yswl.yswletc;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.yswl.yswletc.common.utils.ResultUtil;
import com.yswl.yswletc.dao.ProjectMapper;
import com.yswl.yswletc.dao.UserMapper;
import com.yswl.yswletc.entity.Project;
import com.yswl.yswletc.entity.User;
import lombok.AllArgsConstructor;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class YswletcApplicationTests {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private ProjectMapper projectMapper;

    @Test
    public void contextLoads() {
        User user = new User(null,"张三","156495910","123456",0,1,"1",null,null);
        BigDecimal bigDecimal = BigDecimal.valueOf((float)1.21);
        BigDecimal bigDecimal1 = BigDecimal.valueOf((float)50);
        user.setCommission(bigDecimal);
        user.setWallet(bigDecimal1);
        userMapper.insert(user);
    }
    @Test
    public void contextLoads1(){
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("state",1);
        List<Project> projects = projectMapper.selectList(null);
        System.out.println(projects);
    }
    @Test
    public void contextLoads2(){
        String name="张三";
        String password = "123456";
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("phone","15649591023");
        List<User> list = userMapper.selectList(queryWrapper);
        for (User user : list) {
            if (user != null){
                if (user.getName().equals(name) && user.getPassword().equals(password)){
                    user.setPassword("abcd123");
                    userMapper.updateById(user);
                    System.out.println("修改成功");
                }else {
                    System.out.println("用户名或密码错误");
                }
            }else {
                System.out.println("您提供的手机号有误");
            }
        }
    }
}
