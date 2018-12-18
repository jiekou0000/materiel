package com.bill.materiel.dao;

import com.bill.materiel.domain.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

/**
 * OrderRepository
 *
 * @author Bill
 * @date 2018/12/17 0017
 */
public interface OrderRepository extends JpaRepository<Order, Long>, JpaSpecificationExecutor<Order> {
    @Query(nativeQuery = true, value = "select count(1) from orders where phone_num = ?1 and state != 2")
    Long findCountByPhoneNum(String phoneNum);

    @Transactional
    @Modifying
    @Query(nativeQuery = true, value = "update orders set state = ?2 where id = ?1")
    void changeStateById(Long orderId, Integer state);
}
