package com.atguigu.servciebase.config.exceptionHandle;

import com.atguigu.commonutils.ResultMap;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author chairuntao
 * @version 1.0
 * @date 2020/5/7 16:53
 */
@ControllerAdvice
@Slf4j
public class GroubalExceptionHandle {
    /**
     * 全局异常
     * @param e
     * @return
     */
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public ResultMap error(Exception e){
        e.printStackTrace();
        return ResultMap.error().message("执行了全局异常处理");
    }

    /**
     * 特定异常
     * @param e
     * @return
     */
    @ExceptionHandler(ArithmeticException.class)
    @ResponseBody
    public ResultMap error01(Exception e){
        e.printStackTrace();
        return ResultMap.error().message("!!!!!!!!!!!!!");
    }

    /**
     * 自定义异常
     * @param e
     * @return
     */
    @ExceptionHandler(GuliException.class)
    @ResponseBody
    public ResultMap error02(GuliException e){
        log.error(e.getMessage());
        e.printStackTrace();
        return ResultMap.error().code(e.getCode()).message(e.getMsg());
    }
}
