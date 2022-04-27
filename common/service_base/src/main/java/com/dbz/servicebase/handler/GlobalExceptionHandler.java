package com.dbz.servicebase.handler;

import com.dbz.commonutils.ExceptionUtil;
import com.dbz.commonutils.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author 10979
 * @description:
 * @date 2022/4/122:42
 */
@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public Result error(Exception exception){
        log.error(ExceptionUtil.getMessage(exception));
        exception.printStackTrace();
        return Result.error().message("执行了全局异常处理");
    }

    @ExceptionHandler(GuliException.class)
    @ResponseBody
    public Result error(GuliException e){
        return Result.error().code(e.getCode()).message(e.getMsg());
    }
}
