package com.zhd.ultimate.sociology.interceptor;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author: zhanghaodong
 * @description
 * @date: 2019-12-27 17:35
 */
@Component
public class LoginHandlerInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Object opUserName = request.getSession().getAttribute("opUserName");
        if (opUserName == null) {
            // 获取request返回页面到登录页
            request.getRequestDispatcher("/home/showLogin").forward(request, response);
            return false;
        }
        return true;
    }
}
