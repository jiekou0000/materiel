package com.bill.materiel.controller.basemate;

import com.bill.materiel.utils.message.Message;
import com.bill.materiel.utils.message.MessageType;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 基础物料Controller
 *
 * @author Bill
 * @date 2018/12/12 0012
 */
@Controller
public class BaseMateController {
    /**
     * 灯光页面
     */
    @RequestMapping(value = "/tpl/base-mate/light")
    public String light() {
        return "basemate/light";
    }

    //【HTTP协议】etc确定
    @RequestMapping(value = "/base-mate/light/etc-sure", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<Message> etcSure() {
        Message message = new Message(MessageType.SUCCESS);
        return new ResponseEntity<>(message, HttpStatus.OK);
    }
}
