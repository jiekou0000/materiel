package com.bill.materiel.dto.sysuser;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * LoginReq
 *
 * @author Bill
 * @date 2018/12/14 0014
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class LoginReq {
    private String phoneNum;

    private String password;

    private String name;

    private String pictureCode;
}
