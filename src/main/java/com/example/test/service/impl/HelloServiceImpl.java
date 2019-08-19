package com.example.test.service.impl;

import com.example.test.Exception.BizException;
import com.example.test.service.HelloService;
import org.springframework.stereotype.Service;

@Service
public class HelloServiceImpl implements HelloService {

    @Override
    public void test() {
        int a = 2 / 0;
    }

    @Override
    public String testBiz() {
        boolean bizException = true;
        if (bizException) {
            throw new BizException("1000", "biz异常");
        }
        return "tstBizException";
    }
}
