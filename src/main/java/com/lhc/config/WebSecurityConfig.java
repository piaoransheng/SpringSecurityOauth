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


    //安全拦截机制
    @Override
    protected void configure(HttpSecurity http) throws Exception {
      /*  http.authorizeRequests()
            .antMatchers("/api/test1").hasAuthority("运维总监")
            .antMatchers("/api/test2").hasAuthority("p2")
            .antMatchers("/api/**").fullyAuthenticated()   //所有的 api开头请求必须认证通过
             .anyRequest().permitAll();     */                           //除了 pai开头请求 其他可以访问

     /*   http.csrf().disable()
            .authorizeRequests()
            .antMatchers("/api/test1").hasAuthority("运维总监")
            .antMatchers("/api/test2").hasAuthority("p2")
            .antMatchers("/api/**").fullyAuthenticated()   //所有的 api开头请求必须认证通过
            .anyRequest().permitAll();*/

        http
                .authorizeRequests()
                .antMatchers("/api/test1").hasAuthority("运维总监")
                .antMatchers("/api/test2").hasAuthority("p2")
                .antMatchers("/api/**").fullyAuthenticated()   //所有的 api开头请求必须认证通过
                .anyRequest().authenticated()
                .and()
                .cors()
                .and()
                .csrf().disable();
    }
}