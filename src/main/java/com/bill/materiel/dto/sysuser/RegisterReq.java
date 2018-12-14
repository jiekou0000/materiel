package com.bill.materiel.dto.sysuser;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.Pattern;

/**
 * RegisterReq
 *
 * @author Bill
 * @date 2018/12/14 0014
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class RegisterReq {
    @NotBlank(message = "登录名不能为空")
    private String loginName;

    @NotBlank(message = "手机号不能为空")
    @Pattern(regexp = "^[1][3,4,5,6,7,8,9][0-9]{9}$", message = "手机号码格式不正确")
    private String phoneNum;

    @NotBlank(message = "密码不能为空")
    @Length(max = 16, min = 6, message = "请填写6~16位的密码")
    private String password;
}
