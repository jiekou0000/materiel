package com.bill.materiel.dao;

import com.bill.materiel.domain.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * UserInfoRepository
 *
 * @author Bill
 * @date 2018/12/14 0014
 */
public interface UserInfoRepository extends JpaRepository<UserInfo, Long> {
}
