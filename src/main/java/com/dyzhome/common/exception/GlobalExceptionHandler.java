package com.dyzhome.common.exception;

import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.dyzhome.common.result.R;
import com.dyzhome.common.result.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 全局异常处理
 *
 * @author Dyz
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * 处理自定义异常
     *
     * @param ex 异常
     * @return 结果
     */
    @ExceptionHandler(SysException.class)
    public Result handelSysException(SysException ex) {
        log.error("错误信息====>>{}", ex.getResult().getMessage());
        return Result.error(ex.getResult());
    }

    /**
     * token过期异常
     *
     * @param ex 异常
     * @return 结果
     */
    @ExceptionHandler(TokenExpiredException.class)
    public Result handelTokenExpiredException(TokenExpiredException ex) {
        log.error("token过期===>>{}", ex.getMessage());
        return Result.error(R.Login_TIMEOUT);
    }

    /**
     * 参数校验异常
     *
     * @param ex 异常
     * @return 结果
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Result handelJsrException(MethodArgumentNotValidException ex) {
        String message = ex.getAllErrors().get(0).getDefaultMessage();
        return Result.error(R.VALID_PARAM_ERROR, message);
    }

    /**
     * token不合法异常
     *
     * @param ex 异常
     * @return 结果
     */
    @ExceptionHandler(JWTDecodeException.class)
    public Result handelDecodeException(JWTDecodeException ex) {
        log.error("错误的token===>>{}", ex.getMessage());
        return Result.error(R.TOKEN_ILLEGAL);
    }

    /**
     * 处理其他异常
     *
     * @param ex 异常
     * @return 结果
     */
    @ExceptionHandler(Exception.class)
    public Result handleException(Exception ex) {
        log.error("程序异常：", ex);
        return Result.error(R.UNKNOWN_ERROR);
    }
}
