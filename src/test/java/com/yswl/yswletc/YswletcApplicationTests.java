package com.yswl.yswletc;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.tobato.fastdfs.domain.StorePath;
import com.github.tobato.fastdfs.service.FastFileStorageClient;
import com.yswl.yswletc.dao.AchievementMapper;
import com.yswl.yswletc.dao.ProjectMapper;
import com.yswl.yswletc.dao.UserMapper;
import com.yswl.yswletc.entity.Achievement;
import com.yswl.yswletc.entity.Project;
import com.yswl.yswletc.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class YswletcApplicationTests {

    @Autowired
    private FastFileStorageClient fastFileStorageClient;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private ProjectMapper projectMapper;

    @Autowired
    private AchievementMapper achievementMapper;

    @Test
    public void contextLoads() {

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
    @Test
    public void contextLoads3(){
        File file = new File("D:\\壁纸\\桂林山水风景图片,桂林山水全景图,桂林山水风景6K高清大图_彼岸图网.jpg");
        try {
            synchronized (this){
                StorePath storePath = fastFileStorageClient.uploadImageAndCrtThumbImage(
                        new FileInputStream(file),
                        file.length(),
                        "jpg",
                        null
                );
                System.out.println("上传图片路径为："+storePath.getFullPath());}
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.out.println("发生了错误");
        }
    }
    @Test
    public void contextLoads4() {
        List<User> users = userMapper.selectList(null);
        for (User user : users) {
            user.setJoindate(new Date());
            userMapper.updateById(user);
        }
    }
}
