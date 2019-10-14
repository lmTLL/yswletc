package com.yswl.yswletc;

import com.yswl.yswletc.dao.UserMapper;
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

    @Test
    public void contextLoads() {
        User user = new User("张三","156495910","123456",0,1,"1",new BigDecimal("1"),new BigDecimal("1"));
        userMapper.insert(user);
    }

}
