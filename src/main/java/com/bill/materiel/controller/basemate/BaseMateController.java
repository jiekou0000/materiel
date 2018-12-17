package com.bill.materiel.controller.basemate;

import com.bill.materiel.domain.basemate.Light;
import com.bill.materiel.service.basemate.BaseMateService;
import com.bill.materiel.utils.message.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.security.Principal;

/**
 * 基础物料Controller
 *
 * @author Bill
 * @date 2018/12/12 0012
 */
@Controller
public class BaseMateController {
    @Autowired
    private BaseMateService baseMateService;

    /**
     * 灯光页面
     */
    @RequestMapping(value = "/tpl/base-mate/light")
    public String light() {
        return "basemate/light";
    }

    //【HTTP协议】etc下单 -- 确定 / 待定
    @RequestMapping(value = "/base-mate/light/etc-order", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<Message> etcOrder(Principal user, Light req) {
        Message message = baseMateService.lightEtcOrder(user.getName(), req);
        return new ResponseEntity<>(message, HttpStatus.OK);
    }
}
