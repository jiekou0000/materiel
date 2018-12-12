package com.bill.materiel.service.wechat;

import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.exception.WxErrorException;
import me.chanjar.weixin.common.session.WxSessionManager;
import me.chanjar.weixin.mp.api.WxMpMessageHandler;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.WxMpCustomMessage;
import me.chanjar.weixin.mp.bean.WxMpXmlMessage;
import me.chanjar.weixin.mp.bean.WxMpXmlOutMessage;
import me.chanjar.weixin.mp.bean.kefu.result.WxMpKfInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Created by LEO on 16/11/11.
 */
@Service
@Slf4j
public class CustomServiceEventHandler implements WxMpMessageHandler {

    private static final Logger logger = LoggerFactory.getLogger(CustomServiceEventHandler.class);
    @Override
    public WxMpXmlOutMessage handle(WxMpXmlMessage wxMessage, Map<String, Object> context, WxMpService wxMpService, WxSessionManager sessionManager) throws WxErrorException {
        log.info("***********" + wxMessage);
        if(wxMessage.getEvent().equals("kf_create_session")){
            List<WxMpKfInfo> kfInfoList = wxMpService.getKefuService().kfList().getKfList();
            String nickName = "";
            for(WxMpKfInfo kfInfo : kfInfoList){
                if(kfInfo.getAccount().equals(wxMessage.getKfAccount())){
                    nickName = kfInfo.getNick();
                }
            }
            pushText(wxMpService, wxMessage, "您好，"+nickName+"很荣幸为您服务！");
            return null;
        }else if(wxMessage.getEvent().equals("kf_close_session")){
            log.info("客服通话结束");
            pushText(wxMpService, wxMessage, "您好，您已成功退出人工服务。");
            return null;
        }
        return null;
    }

    private void pushText(WxMpService wxMpService, WxMpXmlMessage wxMpXmlMessage, String reply){
        WxMpCustomMessage message = WxMpCustomMessage
                .TEXT()
                .content(reply)
                .toUser(wxMpXmlMessage.getFromUserName())
                .build();
        try {
            wxMpService.customMessageSend(message);
        } catch (WxErrorException e) {
            e.printStackTrace();
        }
    }
}
