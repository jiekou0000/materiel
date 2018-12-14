package com.bill.materiel.service;

import com.bill.materiel.consts.WebConstant;
import com.bill.materiel.dao.UserInfoRepository;
import com.bill.materiel.domain.UserInfo;
import com.bill.materiel.dto.sysuser.LoginReq;
import com.bill.materiel.dto.sysuser.RegisterReq;
import com.bill.materiel.utils.message.Message;
import com.bill.materiel.utils.message.MessageType;
import org.springframework.beans.factory.annotation.Autowired;
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
     * 登陆
     */
    public Message doLogin(@Valid LoginReq req) {
        // TODO
        return new Message(MessageType.SUCCESS, (Object) WebConstant.LOGIN_REDIRECT_URI);
    }

    /**
     * 注册
     */
    public Message doRegister(@Valid RegisterReq req) {
        UserInfo userInfo = UserInfo.builder()
                .phoneNum(req.getPhoneNum())
                .password(req.getPassword())
                .name(req.getName())
                .time(new Date())
                .build();
        userInfoRepository.save(userInfo);
        return new Message(MessageType.SUCCESS);
    }
}
