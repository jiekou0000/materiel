package com.bill.materiel.controller.sysuser;

import com.bill.materiel.dao.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.security.Principal;

/**
 * UserIndexController
 *
 * @author Bill
 * @date 2018/12/18 0018
 */
@Controller
public class UserIndexController {
    @Autowired
    private OrderRepository orderRepository;

    /**
     * 用户主界面
     */
    @RequestMapping(value = "/user/index", method = RequestMethod.GET)
    public String userIndex(Principal user, ModelMap modelMap) {
        modelMap.put("phoneNum", user.getName());
        Long orderNum = orderRepository.findCountByPhoneNum(user.getName());
        orderNum = (orderNum == null ? 0L : orderNum);
        modelMap.put("orderNum", orderNum);
        return "sysuser/index";
    }
}
