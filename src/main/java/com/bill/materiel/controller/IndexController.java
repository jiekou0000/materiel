package com.bill.materiel.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * IndexController
 *
 * @author Bill
 * @date 2018/12/12 0012
 */
@Controller
public class IndexController {
    /**
     * 初始网站首页
     *
     * @return String
     */
    @RequestMapping(value = {"/index.html", "/index"})
    public String index() {
        return "index";
    }

}
