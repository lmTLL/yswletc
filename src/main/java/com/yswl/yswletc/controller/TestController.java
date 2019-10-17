package com.yswl.yswletc.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * User: jang
 * Date: 2019/10/16
 * Time: 15:36
 * Description: No Description
 */
@RestController
@CrossOrigin(origins = "*")
public class TestController {

    @GetMapping(value = "/hello")
    public String sayHello() {
        return "Hello World!";
    }
}
