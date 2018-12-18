package com.bill.materiel.domain;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * Order
 *
 * @author Bill
 * @date 2018/12/17 0017
 */
@Entity
@Table(name = "orders")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Order {
    @Id
    @GeneratedValue
    private Long id;

    private String phoneNum; // 下单者手机号

    private Integer category; // 类目：10 灯光， 11 音响

    private Integer type; // 类型：1001 帕灯， 1002 筒灯， ...

    private Long num; // 所需数量

    private String startTime; // 使用开始时间

    private Long day; // 所需天数

    private Date createTime; // 下单时间

    private String remark; // 备注

    private Integer state; //订单状态：0 待定，1 确定，2 撤单
}
