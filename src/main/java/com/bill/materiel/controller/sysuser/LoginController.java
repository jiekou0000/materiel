package com.bill.materiel.controller.sysuser;

import com.bill.materiel.consts.WebConstant;
import com.bill.materiel.domain.UserInfo;
import com.bill.materiel.dto.sysuser.LoginReq;
import com.bill.materiel.service.SysUserService;
import com.bill.materiel.utils.message.Message;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.autoconfigure.web.servlet.ManagementErrorEndpoint;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * LoginController
 *
 * @author Bill
 * @date 2018/12/14 0014
 */
@Controller
public class LoginController {
    @Autowired
    private SysUserService sysUserService;

    /**
     * 用户登录页面
     *
     * @return
     */
    @RequestMapping(value = "/login")
    public String login(String uri) {
        if (!StringUtils.isEmpty(uri)) {
            WebConstant.LOGIN_REDIRECT_URI = uri;
        }
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        HttpSession session = request.getSession();
        UserInfo userInfo = (UserInfo) session.getAttribute(WebConstant.SESSION_SYS_USER);
        if (userInfo != null) {
            return "redirect:/index";
        }
        return "sysuser/login";
    }

    // 【HTTP协议】会员登录请求接口
    @RequestMapping(value = "/do-login", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<Message> login(LoginReq req) {
        Message message = sysUserService.doLogin(req);
        return new ResponseEntity<>(message, HttpStatus.OK);
    }
}
