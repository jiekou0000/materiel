package com.bill.materiel.dao;

import com.bill.materiel.domain.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 * UserInfoRepository
 *
 * @author Bill
 * @date 2018/12/14 0014
 */
public interface UserInfoRepository extends JpaRepository<UserInfo, Long> {
    UserInfo findByLoginName(String name);

    @Query(nativeQuery = true, value = "select phone_num, password from user_info where login_name = ?1")
    Object findByName(String name);
}
