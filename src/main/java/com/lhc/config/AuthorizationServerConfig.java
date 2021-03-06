package com.lhc.config;

import com.lhc.diy.MyTokenEnhancer;
import com.lhc.diy.MyUserAuthenticationConverter;
import com.lhc.diy.MyUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.DefaultAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;

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

    //自定义token返回信息
    @Bean
    TokenEnhancer tokenEnhancer() {
        return new MyTokenEnhancer();
    }

    //2.用户信息

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
        return authentication -> daoAuthenticationProvider().authenticate(authentication);
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
        DefaultAccessTokenConverter defaultAccessTokenConverter = new DefaultAccessTokenConverter();
        defaultAccessTokenConverter.setUserTokenConverter(new MyUserAuthenticationConverter());
        endpoints
                .authenticationManager(authenticationManager())
                .allowedTokenEndpointRequestMethods(HttpMethod.POST, HttpMethod.GET)
                .userDetailsService(myUserDetailsService)
                .accessTokenConverter(defaultAccessTokenConverter)
                .tokenEnhancer(tokenEnhancer())
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