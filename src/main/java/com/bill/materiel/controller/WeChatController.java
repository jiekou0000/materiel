package com.bill.materiel.controller;

import com.bill.materiel.dto.wechat.VerificationDto;
import com.bill.materiel.service.wechat.WeChatService;
import me.chanjar.weixin.mp.api.WxMpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by Bill
 */
@RestController
@RequestMapping("/weChat")
public class WeChatController {

    @Autowired
    private WeChatService weChatService;

    @Autowired
    private WxMpService wxMpService;

    /**
     * 微信验证
     * @param verificationDto
     * @return
     */
    @RequestMapping(value = "/verify", method = RequestMethod.GET)
    public String authentication(VerificationDto verificationDto){
        return verificationDto.getEchostr();
    }

    /**
     * 微信事件推送接口
     * @param verificationDto
     * @param request
     * @return
     */
    @RequestMapping(value = "/push", method = RequestMethod.POST)
    public String eventListener(VerificationDto verificationDto, HttpServletRequest request, HttpServletResponse response){
        return weChatService.eventListener(request, response);
    }
}
