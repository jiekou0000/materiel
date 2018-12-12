package com.bill.materiel.service.wechat;

import com.bill.materiel.utils.message.Message;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.exception.WxErrorException;
import me.chanjar.weixin.common.session.WxSessionManager;
import me.chanjar.weixin.mp.api.WxMpMessageHandler;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.WxMpXmlMessage;
import me.chanjar.weixin.mp.bean.WxMpXmlOutMessage;
import me.chanjar.weixin.mp.bean.WxMpXmlOutTextMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Created by Bill
 */
@Service
@Slf4j
public class TextEventHandler implements WxMpMessageHandler {
    private static final Logger logger = LoggerFactory.getLogger(TextEventHandler.class);

    @Override
    public WxMpXmlOutMessage handle(WxMpXmlMessage wxMessage, Map<String, Object> context, WxMpService wxMpService, WxSessionManager sessionManager) throws WxErrorException {
        ResponseEntity<Message> responseEntity = null;
        List reply = new ArrayList();
        return buildResponse(wxMessage, "text", reply.toString());
    }

    /**
     * 构造响应
     * @param wxMpXmlMessage
     * @param msgType
     * @param reply
     * @return
     */
    private WxMpXmlOutTextMessage buildResponse(WxMpXmlMessage wxMpXmlMessage, String msgType, String reply){
        WxMpXmlOutTextMessage textMessage = new WxMpXmlOutTextMessage();
        textMessage.setCreateTime(System.currentTimeMillis());
        textMessage.setFromUserName(wxMpXmlMessage.getToUserName());
        textMessage.setToUserName(wxMpXmlMessage.getFromUserName());
        textMessage.setMsgType(msgType);
        textMessage.setContent(reply);
        return textMessage;
    }
}
