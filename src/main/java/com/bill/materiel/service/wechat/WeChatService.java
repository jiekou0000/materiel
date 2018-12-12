package com.bill.materiel.service.wechat;

import me.chanjar.weixin.common.api.WxConsts;
import me.chanjar.weixin.mp.api.WxMpMessageRouter;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.WxMpXmlMessage;
import me.chanjar.weixin.mp.bean.WxMpXmlOutMessage;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;


/**
 * Created by Bill
 */
@Service
public class WeChatService {

    @Autowired
    private WxMpService wxMpService;

    @Autowired
    private WxMpMessageRouter router;

    @Autowired
    private ClickEventHandler clickEventHandler;

    @Autowired
    private SubscribeEventHandler subscribeEventHandler;

    @Autowired
    private TextEventHandler textEventHandler;

    @Autowired
    private CustomServiceEventHandler customServiceEventHandler;

    @Autowired
    private ChatEventHandler chatEventHandler;

    /**
     * 微信事件监听
     * @param request
     * @return
     */
    public String eventListener(HttpServletRequest request, HttpServletResponse response){
        try {
            request.setCharacterEncoding("UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        response.setCharacterEncoding("UTF-8");

        router
            .rule()
            .msgType(WxConsts.XML_MSG_EVENT)
            .event(WxConsts.EVT_CLICK)
            .async(false)
            .handler(clickEventHandler)
            .end()

            .rule()
            .msgType(WxConsts.XML_MSG_EVENT)
            .event(WxConsts.EVT_SUBSCRIBE)
            .async(false)
            .handler(subscribeEventHandler)
            .end()

            .rule()
            .msgType(WxConsts.XML_MSG_TEXT)
            .async(false)
            .handler(textEventHandler)
            .end()

            .rule()
            .msgType(WxConsts.XML_MSG_EVENT)
            .event(WxConsts.EVT_KF_CREATE_SESSION)
            .async(false)
            .handler(customServiceEventHandler)
            .end()

            .rule()
            .msgType(WxConsts.XML_MSG_EVENT)
            .event(WxConsts.EVT_KF_CLOSE_SESSION)
            .async(false)
            .handler(customServiceEventHandler)
            .end()

            .rule()
            .msgType(WxConsts.XML_TRANSFER_CUSTOMER_SERVICE)
            .event(WxConsts.CUSTOM_MSG_TEXT)
            .async(false)
            .handler(chatEventHandler)
            .end();
        WxMpXmlOutMessage responseMsg = router.route(messageDecoder(request));
        if (responseMsg != null) {
          // 说明是同步回复的消息
          // 将xml写入HttpServletResponse
            PrintWriter out = null;
            try {
                out = response.getWriter();
            } catch (IOException e) {
                e.printStackTrace();
            }
            out.print(responseMsg.toXml());
            out.flush();
            out.close();
        }
        return "";
    }

    /**
     * 消息解析
     * @param request
     * @return
     */
    private WxMpXmlMessage messageDecoder(HttpServletRequest request){
        String data = null;
        try {
            data = IOUtils.toString(request.getInputStream(), "UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return WxMpXmlMessage.fromXml(data);
    }
}
