package com.bill.materiel.service;

import com.bill.materiel.consts.WebConstant;
import com.bill.materiel.dao.UserInfoRepository;
import com.bill.materiel.domain.UserInfo;
import com.bill.materiel.dto.sysuser.RegisterReq;
import com.bill.materiel.utils.message.Message;
import com.bill.materiel.utils.message.MessageType;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
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
     * 注册
     */
    public Message doRegister(@Valid RegisterReq req) {
        UserInfo exist = userInfoRepository.findByLoginName(req.getLoginName());
        if (exist != null) {
            return new Message(MessageType.ERROR, "登录名已存在");
        }
        UserInfo userInfo = UserInfo.builder()
                .phoneNum(req.getPhoneNum())
                .password(new BCryptPasswordEncoder().encode(req.getPassword()))
                .loginName(req.getLoginName())
                .createTime(new Date())
                .build();
        userInfoRepository.save(userInfo);
        return new Message(MessageType.SUCCESS);
    }

    /**
     * 登陆
     */
    public Message doLogin() {
        if (StringUtils.isEmpty(WebConstant.LOGIN_REDIRECT_URI)) {
            return new Message(MessageType.SUCCESS, (Object) "/index");
        } else {
            String[] str = WebConstant.LOGIN_REDIRECT_URI.split("/");
            WebConstant.LOGIN_REDIRECT_URI = "/tpl/" + str[1] + "/" + str[2];
            return new Message(MessageType.SUCCESS, (Object) WebConstant.LOGIN_REDIRECT_URI);
        }
    }
}
