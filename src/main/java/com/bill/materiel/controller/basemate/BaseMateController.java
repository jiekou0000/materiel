package com.bill.materiel.controller.basemate;

import com.bill.materiel.domain.Order;
import com.bill.materiel.service.OrderService;
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
    private OrderService orderService;

    /**
     * 灯光页面
     */
    @RequestMapping(value = "/tpl/base-mate/light")
    public String light() {
        return "basemate/light";
    }

    /**
     * 【HTTP协议】灯光下单 -- 确定 / 待定
     */
    @RequestMapping(value = "/base-mate/light/order", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<Message> placeOrder(Principal user, Order req) {
        Message message = orderService.placeOrder(user.getName(), req);
        return new ResponseEntity<>(message, HttpStatus.OK);
    }
}
