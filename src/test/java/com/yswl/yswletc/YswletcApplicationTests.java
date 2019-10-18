package com.yswl.yswletc;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.tobato.fastdfs.domain.StorePath;
import com.github.tobato.fastdfs.service.FastFileStorageClient;
import com.yswl.yswletc.common.utils.ResultUtil;
import com.yswl.yswletc.dao.*;
import com.yswl.yswletc.entity.*;
import jdk.jfr.events.ExceptionThrownEvent;
import jdk.management.resource.internal.inst.SocketOutputStreamRMHooks;
import org.json.JSONObject;
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
import java.util.Map;

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

    @Autowired
    private StudentMapper studentMapper;

    @Autowired
    private NewStudentMapper newStudentMapper;

    @Autowired
    private NewAchievementMapper newAchievementMapper;


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
        NewStudent newStudent = new NewStudent();
        List<Student> lists = studentMapper.queryStudentByDay(1);
        newStudentMapper.insertAll(lists);
    }
    @Test
    public void contextLoads5() {
        Student student = new Student();
        for (int i = 0; i < 10; i++) {
            student.setBirthday(new Date());
            student.setAge(i);
            student.setName("王麻"+i);
            student.setPhone(""+i);
            studentMapper.insert(student);
        }

    }
    @Test
    public void contextLoads6() {
        newAchievementMapper.deleteAll();
    }
    @Test
    public void contextLoads7() {
        NewStudent newStudent = new NewStudent();
        List<Student> lists = studentMapper.queryStudentByDay(1);
        long startTime = System.currentTimeMillis();
        for (Student student : lists) {
            newStudent.setAge(student.getAge());
            newStudent.setBirthday(student.getBirthday());
            newStudent.setName(student.getName());
            newStudent.setPhone(student.getPhone());
            newStudent.setId(student.getId());
            newStudentMapper.insert(newStudent);
        }
        long endTime = System.currentTimeMillis();
        float seconds = (endTime - startTime) / 1000F;
        System.out.println(Float.toString(seconds) + " seconds.");
    }
    @Test
    public void contextLoads8() {
        List<Student> lists = studentMapper.queryStudentByDay(1);
        long startTime = System.currentTimeMillis();
        newStudentMapper.insertAll(lists);
        IPage<NewStudent> page = new Page<NewStudent>(1,3);
        IPage<NewStudent> newStudentIPage = newStudentMapper.selectPage(page, null);

    }
    @Test
    public void contextLoads9() {
        try {
            //条件
            String itemname=null;//项目名
            String uname=null; //提报人姓名
            String username=null;//姓名
            String phone=null;//电话
            String carid=null;//车牌号
            //审核状态
            Integer state=null;//审核状态：0待审核  1审核通过  2审核不通过

            //时间域
            Integer day=null;

            //分页
            Integer current=null; //当前页
            Integer size=null; //个数

            //按时间查找并写入虚拟表
            List<Achievement> lists = achievementMapper.queryAchievementByDay(1);
            NewAchievement newAchievement = new NewAchievement();
            for (Achievement Achievement : lists) {
                newAchievement.setId(Achievement.getId());
                newAchievement.setUid(Achievement.getUid());
                newAchievement.setUname(Achievement.getUname());
                newAchievement.setPid(Achievement.getPid());
                newAchievement.setUsername(Achievement.getUsername());
                newAchievement.setPhone(Achievement.getPhone());
                newAchievement.setItemname(Achievement.getItemname());
                newAchievement.setCarid(Achievement.getCarid());
                newAchievement.setPicturepath(Achievement.getPicturepath());
                newAchievement.setRemarks(Achievement.getRemarks());
                newAchievement.setCommission(Achievement.getCommission());
                newAchievement.setSubmittime(Achievement.getSubmittime());
                newAchievement.setState(Achievement.getState());
                newAchievementMapper.insert(newAchievement);
            }
            //对虚拟表进行条件查询
            IPage<NewAchievement> page = new Page<NewAchievement>(current,size);
            QueryWrapper queryWrapper = new QueryWrapper();
            if (itemname != null){
                queryWrapper.eq("itemname",itemname);
            }
            if (uname != null){
                queryWrapper.eq("uname",uname);
            }
            if (username != null){
                queryWrapper.eq("username",username);
            }
            if (phone != null){
                queryWrapper.eq("phone",phone);
            }
            if (carid != null){
                queryWrapper.eq("carud",carid);
            }
            if (state != null){
                queryWrapper.eq("state",state);
            }
            newAchievementMapper.selectPage(page,queryWrapper);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            newAchievementMapper.deleteAll();
        }
    }
    @Test
    public void contextLoads10(){
        List<Student> lists = studentMapper.queryStudentByDay(1);
        Integer integer = newStudentMapper.insertAll(lists);
        newStudentMapper.deleteAll();
        System.out.println(integer);

    }
    @Test
    public void contextLoads11(){
        List<Achievement> list = achievementMapper.queryAchievementByDay(7);
        Integer integer = newAchievementMapper.insertAll(list);
        newAchievementMapper.deleteAll();
        System.out.println(integer);

    }
}