package com.bill.materiel.dto.sysuser;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.NotBlank;

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
    @NotBlank(message = "登录名不能为空")
    private String loginName;

    @NotBlank(message = "密码不能为空")
    private String password;
}
