package com.bill.materiel.controller.sysuser;

import com.bill.materiel.dto.sysuser.RegisterReq;
import com.bill.materiel.service.SysUserService;
import com.bill.materiel.utils.message.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * RegisterController
 *
 * @author Bill
 * @date 2018/12/14 0014
 */
@Controller
public class RegisterController {
    @Autowired
    private SysUserService sysUserService;

    /**
     * 用户注册页面
     *
     * @return
     */
    @RequestMapping(value = "/tpl/register")
    public String register() {
        return "sysuser/register";
    }


    //【HTTP协议】用户注册请求接口
    @RequestMapping(value = "/do-register", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<Message> register(RegisterReq req) {
        Message message = sysUserService.doRegister(req);
        return new ResponseEntity<>(message, HttpStatus.OK);
    }
}
