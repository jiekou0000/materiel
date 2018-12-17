package com.bill.materiel.config;

import com.bill.materiel.security.CustomUserDetailsService;
import com.bill.materiel.security.RestAuthenticationEntryPoint;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;


/**
 * Created by Bill
 */
@Configuration
@Order(SecurityProperties.BASIC_AUTH_ORDER)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    UserDetailsService customUserDetailsService(){
        return new CustomUserDetailsService(); //注册CustomUserService的bean；
    }

    @Bean
    RestAuthenticationEntryPoint restAuthenticationEntryPoint(){
        return new RestAuthenticationEntryPoint();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(customUserDetailsService()).passwordEncoder(passwordEncoder()); //添加我们自定的user detail service认证；
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.httpBasic()
                .authenticationEntryPoint(restAuthenticationEntryPoint())
                .and()
                .authorizeRequests()
                .anyRequest().authenticated()
                .and().csrf().disable().cors(); // 跨域请求通过
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        // 忽略的路径，不会权限校验
        web.ignoring()
                .antMatchers("/templates/**", "/lib/**", "/js/**", "/css/**", "/img/**", "/favicon.ico", "/"
                        ,"/index", "/tpl/**"
                        ,"/weChat/**", "/do-register");
    }
}
