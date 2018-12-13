package com.bill.materiel.controller.basemate;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * 基础物料Controller
 *
 * @author Bill
 * @date 2018/12/12 0012
 */
@Controller
@RequestMapping(value = "/base-mate", method = RequestMethod.GET)
public class BaseMateController {
    /**
     * 灯光 light
     */
    @RequestMapping(value = "/light")
    public String light() {
        return "basemate/light";
    }
}
