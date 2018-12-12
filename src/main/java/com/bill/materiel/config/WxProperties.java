package com.bill.materiel.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Created by Bill
 */
@ConfigurationProperties(prefix = "wx")
@Data
public class WxProperties {
    private String contactUs;
    private String serverUrl;
    private String subscribeReply;
}
