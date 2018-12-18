package com.bill.materiel.controller.sysuser;

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
 * UserOrderRecordController
 *
 * @author Bill
 * @date 2018/12/18 0018
 */
@Controller
public class UserOrderRecordController {
    @Autowired
    private OrderService orderService;

    /**
     * 用户"确定订单"页面
     */
    @RequestMapping(value = "/user/order/sure")
    public String light() {
        return "sysuser/orderSure";
    }

    /**
     * 【HTTP协议】用户订单记录 -- 确定 / 待定
     */
    @RequestMapping(value = "/user/order/record", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<Message> orderRecord(Principal user, Integer state, Integer page, Integer rows) {
        Message message = orderService.orderRecord(user.getName(), state, page, rows);
        return new ResponseEntity<>(message, HttpStatus.OK);
    }

    /**
     * 【HTTP协议】修改订单状态 -- 改为 确定 / 待定 / 撤单
     */
    @RequestMapping(value = "/user/order/change-sate", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<Message> changeState(Long orderId, Integer state) {
        Message message = orderService.changeState(orderId, state);
        return new ResponseEntity<>(message, HttpStatus.OK);
    }
}
