package com.example.test.controller;

import com.example.test.service.HelloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @Autowired
    private HelloService helloService;

    @GetMapping(value = "/hello")
    public String hello() {
        return "Hello World!";
    }

    @GetMapping(value = "/test")
    public String testBizException() {
        return helloService.testBiz();
    }

    @GetMapping(value = "/test/exception")
    public void testException() {
        helloService.test();
    }
}
