package com.example.test.Exception;

import com.example.test.util.CommonResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class ControllerAdvice {

    @ExceptionHandler(Exception.class)
    public CommonResult handleException(Exception e) {
        log.error(e.getMessage());
        return CommonResult.errorResult(-1, "系统繁忙，请稍候再试");
    }

    @ExceptionHandler(BizException.class)
    public CommonResult handleBizException(BizException exception) {
        return CommonResult.errorResult(Integer.parseInt(exception.getErrorCode()), exception.getErrorMsg());
    }

}
