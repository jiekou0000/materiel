package com.bill.materiel.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * SystemIndexController
 *
 * @author Bill
 * @date 2018/12/12 0012
 */
@Controller
public class SystemIndexController {
    /**
     * 初始网站首页
     *
     * @return String
     */
    @RequestMapping(value = "/index")
    public String index() {
        return "index";
    }
}
