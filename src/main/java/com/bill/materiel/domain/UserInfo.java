package com.bill.materiel.domain;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * UserInfo
 *
 * @author Bill
 * @date 2018/12/12 0012
 */
@Entity
@Table(name = "user_info")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserInfo {
    @Id
    @GeneratedValue
    private Long id;

    private String phoneNum;

    private String password;

    private String loginName;

    private Date createTime;
}
