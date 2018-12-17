package com.bill.materiel.domain.basemate;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * Light
 *
 * @author Bill
 * @date 2018/12/17 0017
 */
@Entity
@Table(name = "light")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Light {
    @Id
    @GeneratedValue
    private Long id;

    private String phoneNum; // 下单者手机号

    private Integer type; // 类型：0 帕灯， 1 筒灯， ...

    private Long num; // 所需数量

    private String startTime; // 使用开始时间

    private Long day; // 所需天数

    private Date createTime; // 下单时间

    private Integer state; //订单状态：0 待定， 1 确定
}
