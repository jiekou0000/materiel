package com.bill.materiel.config;

import lombok.*;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * Created by Bill
 */
@Component
@ConfigurationProperties(prefix = "wx")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class WxConfig {
    private String contactUs;
    private String serverUrl;
    private String subscribeReply;
}
