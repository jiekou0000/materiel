package com.bill.materiel.security;

import com.bill.materiel.dao.UserInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import com.google.common.collect.Sets;

import java.util.Set;

/**
 * Created by Bill
 */
public class CustomUserDetailsService implements UserDetailsService {
    @Autowired
    private UserInfoRepository userInfoRepository;

    @Override
    public UserDetails loadUserByUsername(String userName) {
        Set<GrantedAuthority> authSet = Sets.newHashSet();
        Object sysUser = userInfoRepository.findByName(userName);
        if (sysUser == null) {
            throw new UsernameNotFoundException("登录名不存在");
        }
        Object[] objects = (Object[]) sysUser;
        return new User(objects[0].toString(), objects[1].toString(), authSet);
    }
}
