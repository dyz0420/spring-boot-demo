package com.dyzhome.common.exception;

import com.dyzhome.common.result.R;
import com.dyzhome.common.result.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 全局异常处理
 * @author Dyz
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(SysException.class)
    public Result handelException(SysException ex) {
        log.error("错误信息====>>{}", ex.getResult().getMessage());
        return Result.error(ex.getResult());
    }

    @ExceptionHandler(Exception.class)
    public Result doException(Exception ex){
        ex.printStackTrace();
        return Result.error(R.UNKNOWN_ERROR);
    }
}
