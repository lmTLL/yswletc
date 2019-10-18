package com.yswl.yswletc;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.tobato.fastdfs.domain.StorePath;
import com.github.tobato.fastdfs.service.FastFileStorageClient;
import com.yswl.yswletc.dao.*;
import com.yswl.yswletc.entity.*;
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

    @Autowired
    private StudentMapper studentMapper;

    @Autowired
    private NewStudentMapper newStudentMapper;


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
        newStudentMapper.deleteAll();
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
        NewStudent newStudent = new NewStudent();
        List<Student> lists = studentMapper.queryStudentByDay(1);
        long startTime = System.currentTimeMillis();
        newStudentMapper.insertAll(lists);
        long endTime = System.currentTimeMillis();
        float seconds = (endTime - startTime) / 1000F;
        System.out.println(Float.toString(seconds) + " seconds.");
    }
    @Test
    public void contextLoads9() {
        IPage<Student> page = new Page<Student>(1,2);
        IPage<Student> page1 = studentMapper.selectPage(page, null);
        System.out.println(page1.toString());
    }

}