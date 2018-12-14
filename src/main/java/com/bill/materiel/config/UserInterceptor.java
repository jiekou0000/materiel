package com.bill.materiel.config;

import com.bill.materiel.consts.WebConstant;
import com.bill.materiel.utils.JsonUtils;
import com.bill.materiel.utils.message.Message;
import com.bill.materiel.utils.message.MessageType;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Created by Bill
 */
@Slf4j
@Component
public class UserInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 判断是否登录,没有登录重定向到登陆页
        HttpSession session = request.getSession();
        if (session == null || session.getAttribute(WebConstant.SESSION_SYS_USER) == null) {
            // 重定向
            response.sendRedirect(request.getContextPath() + "/login?uri=" + request.getRequestURI());
            return false;
        }
        // 只有返回true才会继续向下执行，返回false取消当前请求
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
    }
}
