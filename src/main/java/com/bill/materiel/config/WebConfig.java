package com.bill.materiel.config;

import com.bill.materiel.consts.WeChatConsts;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import me.chanjar.weixin.mp.api.WxMpInMemoryConfigStorage;
import me.chanjar.weixin.mp.api.WxMpMessageRouter;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.api.impl.WxMpServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

/**
 * Created by Bill
 */
@Configuration
@EnableScheduling
public class WebConfig extends WebMvcConfigurationSupport {
    @Bean
    public ObjectMapper objectMapper(){
        return new ObjectMapper();
    }

    @Bean
    public Gson gson(){
        return new Gson();
    }

    @Bean
    public WxProperties wxProperties(){
        return new WxProperties();
    }

    @Bean
    public WxMpService wxMpService(){
        WxMpInMemoryConfigStorage config = new WxMpInMemoryConfigStorage();
        config.setAppId(WeChatConsts.APPID.value());
        config.setSecret(WeChatConsts.APPSECRET.value());
        config.setToken(WeChatConsts.TOKEN.value());
        WxMpService wxMpService = new WxMpServiceImpl();
        wxMpService.setWxMpConfigStorage(config);
        return wxMpService;
    }

    @Bean
    public WxMpMessageRouter wxMpMessageRouter(){
        return new WxMpMessageRouter(wxMpService());
    }

    /**
     * 静态资源映射
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/**").addResourceLocations(
                "classpath:/META-INF/resources/",
                "classpath:/resources/",
                "classpath:/static/"
        );
    }
}
