package com.bill.materiel.service;

import com.bill.materiel.dao.OrderRepository;
import com.bill.materiel.domain.Order;
import com.bill.materiel.utils.PageUtils;
import com.bill.materiel.utils.message.Message;
import com.bill.materiel.utils.message.MessageType;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * OrderService
 *
 * @author Bill
 * @date 2018/12/17 0017
 */
@Service
public class OrderService {
    @Autowired
    private OrderRepository orderRepository;

    /**
     * place order
     */
    public Message placeOrder(String phoneNum, Order req) {
        Order light = Order.builder()
                .phoneNum(phoneNum)
                .category(req.getCategory())
                .type(req.getType())
                .num(req.getNum())
                .startTime(req.getStartTime())
                .day(req.getDay())
                .createTime(new Date())
                .remark(req.getRemark())
                .state(req.getState())
                .build();
        orderRepository.save(light);
        return new Message(MessageType.SUCCESS);
    }

    /**
     * order record
     */
    public Message orderRecord(String phoneNum, Integer state, Integer page, Integer rows) {
        Page<Order> sureOrders = orderRepository.findAll(this.createSpecification(phoneNum, state), PageUtils.pageDataDesc(page, rows, "id"));
        return new Message(MessageType.SUCCESS, sureOrders);
    }

    // createSpecification
    private Specification<Order> createSpecification(String phoneNum, Integer state) {
        return (Specification<Order>) (root, query, cb) -> {
            List<Predicate> list = new ArrayList<>();
            if (StringUtils.isNotEmpty(phoneNum)) {
                list.add(cb.equal(root.get("phoneNum"), phoneNum));
            }

            if (StringUtils.isNotEmpty(String.valueOf(state))) {
                list.add(cb.equal(root.get("state"), state));
            }
            Predicate[] p = new Predicate[list.size()];
            return cb.and(list.toArray(p));
        };
    }

    /**
     * order change state
     */
    public Message changeState(Long orderId, Integer state) {
        orderRepository.changeStateById(orderId, state);
        return new Message(MessageType.SUCCESS);
    }
}
