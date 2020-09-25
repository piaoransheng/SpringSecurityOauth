package com.lhc.config;

import com.lhc.config.zhuru.MyUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

/**
 * @Author lhc
 * @Date 2020-09-24 15:07
 **/

@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {

    //1.加密方式
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    //2.用户信息  正常从数据库读取
    @Autowired
    private MyUserDetailsService myUserDetailsService;

    //3.整合加密方式和用户信息
    @Bean
    AuthenticationProvider daoAuthenticationProvider() {
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setUserDetailsService(myUserDetailsService);
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
        daoAuthenticationProvider.setHideUserNotFoundExceptions(false);
        return daoAuthenticationProvider;
    }

    //4.啥东东
    @Bean
    AuthenticationManager authenticationManager() {
        AuthenticationManager authenticationManager = new AuthenticationManager() {
            @Override
            public Authentication authenticate(Authentication authentication) throws AuthenticationException {
                return daoAuthenticationProvider().authenticate(authentication);
            }
        };
        return authenticationManager;
    }


    //1.配置appId secret  回到地址  token有效期
    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients
            .inMemory()
                .withClient("client_1")
                    .secret(passwordEncoder().encode("secret_1"))
                    .redirectUris("https://www.baidu.com/")
                    .authorizedGrantTypes("password", "refresh_token", "authorization_code")
                    .scopes("all")
                    .resourceIds("resourceId1")
                    .accessTokenValiditySeconds(36000)
                    .refreshTokenValiditySeconds(36000)

                .and()

                .withClient("client_2")
                .secret(passwordEncoder().encode("secret_2"))
                .redirectUris("https://www.baidu.com/")
                .authorizedGrantTypes("password", "refresh_token", "authorization_code")
                .scopes("all")
                .resourceIds("resourceId2")
                .accessTokenValiditySeconds(36000)
                .refreshTokenValiditySeconds(36000)
        ;
    }

    //2.配置token类型
    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) {
        endpoints
            .authenticationManager(authenticationManager())
                .allowedTokenEndpointRequestMethods(HttpMethod.POST, HttpMethod.GET)
                .userDetailsService(myUserDetailsService)
        ;
    }

    //3.配置权限
    @Override
    public void configure(AuthorizationServerSecurityConfigurer oauthServer) {
        oauthServer
                .allowFormAuthenticationForClients()    //允许表单认证
                .checkTokenAccess("permitAll()");       //验证token
    }
}