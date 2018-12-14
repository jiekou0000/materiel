package com.bill.materiel.service;

import com.bill.materiel.consts.WebConstant;
import com.bill.materiel.dto.sysuser.LoginReq;
import com.bill.materiel.utils.message.Message;
import com.bill.materiel.utils.message.MessageType;
import org.springframework.stereotype.Service;

/**
 * SysUserService
 *
 * @author Bill
 * @date 2018/12/14 0014
 */
@Service
public class SysUserService {
    public Message doLogin(LoginReq req) {
        // TODO
        return new Message(MessageType.SUCCESS, (Object) WebConstant.LOGIN_REDIRECT_URI);
    }

}
