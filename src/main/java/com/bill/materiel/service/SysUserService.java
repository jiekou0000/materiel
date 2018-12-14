package com.bill.materiel.service;

import com.bill.materiel.consts.WebConstant;
import com.bill.materiel.dao.UserInfoRepository;
import com.bill.materiel.domain.UserInfo;
import com.bill.materiel.dto.sysuser.LoginReq;
import com.bill.materiel.dto.sysuser.RegisterReq;
import com.bill.materiel.utils.message.Message;
import com.bill.materiel.utils.message.MessageType;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.Date;

/**
 * SysUserService
 *
 * @author Bill
 * @date 2018/12/14 0014
 */
@Service
public class SysUserService {
    @Autowired
    private UserInfoRepository userInfoRepository;

    /**
     * 登陆
     */
    public Message doLogin(@Valid LoginReq req) {
        UserInfo exist = userInfoRepository.findByLoginName(req.getLoginName());
        if (exist == null) {
            return new Message(MessageType.ERROR, "登录名不存在");
        }
        if (!StringUtils.equals(req.getPassword(), exist.getPassword())) {
            return new Message(MessageType.ERROR, "登录名或密码错误");
        }

        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        HttpSession session = request.getSession();
        session.setAttribute(WebConstant.SESSION_SYS_USER, exist);

        if (StringUtils.isEmpty(WebConstant.LOGIN_REDIRECT_URI)) {
            WebConstant.LOGIN_REDIRECT_URI = "/index";
        }
        return new Message(MessageType.SUCCESS, (Object) WebConstant.LOGIN_REDIRECT_URI);
    }

    /**
     * 注册
     */
    public Message doRegister(@Valid RegisterReq req) {
        UserInfo exist = userInfoRepository.findByLoginName(req.getLoginName());
        if (exist != null) {
            return new Message(MessageType.ERROR, "登录名已存在");
        }
        UserInfo userInfo = UserInfo.builder()
                .phoneNum(req.getPhoneNum())
                .password(req.getPassword())
                .loginName(req.getLoginName())
                .time(new Date())
                .build();
        userInfoRepository.save(userInfo);
        return new Message(MessageType.SUCCESS);
    }
}
