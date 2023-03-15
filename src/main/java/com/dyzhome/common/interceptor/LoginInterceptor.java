package com.dyzhome.common.interceptor;

import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSON;
import com.dyzhome.common.result.R;
import com.dyzhome.common.result.Result;
import com.dyzhome.common.util.CurrentUserUtil;
import com.dyzhome.common.util.JwtUtil;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 登录拦截器
 *
 * @author Dyz
 */
@Component
public class LoginInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //获取请求头中的令牌
        String token = request.getHeader("Authorization");
        if (StrUtil.isBlankOrUndefined(token)) {
            response.setContentType("application/json;charset=utf-8");
            response.getWriter().print(JSON.toJSONString(Result.error(R.NOT_LOGIN)));
            return false;
        }
        Long userId = JwtUtil.parseToken(token);
        //将用户Id存入ThreadLocal
        CurrentUserUtil.putUserId(userId);
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        //防止内存泄漏的风险
        CurrentUserUtil.remove();
    }

}
