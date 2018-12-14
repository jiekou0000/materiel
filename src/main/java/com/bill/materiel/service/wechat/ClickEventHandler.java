package com.bill.materiel.service.wechat;

import com.bill.materiel.config.WxConfig;
import com.bill.materiel.utils.Utils;
import me.chanjar.weixin.common.exception.WxErrorException;
import me.chanjar.weixin.common.session.WxSessionManager;
import me.chanjar.weixin.mp.api.WxMpMessageHandler;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.WxMpXmlMessage;
import me.chanjar.weixin.mp.bean.WxMpXmlOutMessage;
import me.chanjar.weixin.mp.bean.WxMpXmlOutNewsMessage;
import me.chanjar.weixin.mp.bean.WxMpXmlOutTextMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * Created by Bill
 */
@Service
public class ClickEventHandler implements WxMpMessageHandler{
    @Autowired
    private WxConfig wxConfig;

    @Override
    public WxMpXmlOutMessage handle(WxMpXmlMessage wxMessage, Map<String, Object> context, WxMpService wxMpService, WxSessionManager sessionManager) throws WxErrorException {
        if(wxMessage.getEventKey().equals("contact_us")){
            return pushText(wxMessage, Utils.buildText(wxConfig.getContactUs()));
        }else if(wxMessage.getEventKey().equals("online_help")){
            return pushText(wxMessage, "很高兴为您服务,请简要输入您的问题,我们会耐心为您解答");
        }
        return null;
    }

    private WxMpXmlOutMessage pushText(WxMpXmlMessage wxMpXmlMessage, String text){
        WxMpXmlOutTextMessage textMessage = new WxMpXmlOutTextMessage();
        textMessage.setCreateTime(System.currentTimeMillis());
        textMessage.setFromUserName(wxMpXmlMessage.getToUserName());
        textMessage.setToUserName(wxMpXmlMessage.getFromUserName());
        textMessage.setMsgType("text");
        textMessage.setContent(text);
        return textMessage;
    }

    private WxMpXmlOutNewsMessage.Item constructProblem(){
        WxMpXmlOutNewsMessage.Item item = new WxMpXmlOutNewsMessage.Item();
        item.setPicUrl("http://222.73.56.22:89/information/news/6aad4e23-4d35-4bef-8b79-d7a8851314ac.jpg");
        item.setTitle("常见问题");
        item.setUrl(wxConfig.getServerUrl()+"/#/wx/commonProblem");
        return item;
    }
}
