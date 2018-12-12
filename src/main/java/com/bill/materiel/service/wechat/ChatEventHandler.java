package com.bill.materiel.service.wechat;

import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.exception.WxErrorException;
import me.chanjar.weixin.common.session.WxSessionManager;
import me.chanjar.weixin.mp.api.WxMpMessageHandler;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.WxMpXmlMessage;
import me.chanjar.weixin.mp.bean.WxMpXmlOutMessage;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * Created by LEO on 16/11/23.
 */
@Service
@Slf4j
public class ChatEventHandler implements WxMpMessageHandler {
    @Override
    public WxMpXmlOutMessage handle(WxMpXmlMessage wxMessage, Map<String, Object> context, WxMpService wxMpService, WxSessionManager sessionManager) throws WxErrorException {
        log.info("chatEventHandler**************" + wxMessage);
        if("88".equals(wxMessage.getContent()) || "拜拜".equals(wxMessage.getContent())){
            wxMpService.getKefuService().kfSessionClose(wxMessage.getFromUserName(), wxMessage.getKfAccount());
        }
        return null;
    }
}
