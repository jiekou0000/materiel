package com.bill.materiel.dto.wechat;

import lombok.*;

/**
 * Created by bill
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class VerificationDto {
    private String signature;
    private String timestamp;
    private String nonce;
    private String echostr;
}
