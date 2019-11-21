package com.yswl.yswletc;

import com.yswl.yswletc.dao.UserMapper;
import com.yswl.yswletc.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class YswletcApplicationTests {
    @Autowired
    private UserMapper userMapper;

    @Test
    public void test(){
        List<String> strings = userMapper.queryAllOpenid();

        System.out.println(strings);
    }


}