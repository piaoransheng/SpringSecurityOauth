package com.lhc.config;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.stereotype.Component;

/**
 * @Author lhc
 * @Date 2020-09-24 14:39
 **/
@Component
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {


    //2.拦截所有请求，使用http-basic方式登录
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests().antMatchers("/**").fullyAuthenticated()
            .and()
            .httpBasic();
    }
}