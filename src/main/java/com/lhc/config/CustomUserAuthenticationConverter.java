package com.lhc.config;

import com.lhc.diy.MyUserDetails;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.oauth2.provider.token.DefaultUserAuthenticationConverter;
import org.springframework.util.CollectionUtils;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by lisp
 * 2019/4/4
 */
public class CustomUserAuthenticationConverter extends DefaultUserAuthenticationConverter {

    @Override
    public Map<String, ?> convertUserAuthentication(Authentication authentication) {
        Map<String, Object> response = new LinkedHashMap<>();
        MyUserDetails userDetails = (MyUserDetails) authentication.getPrincipal();
        response.put(USERNAME, userDetails);
        if (!CollectionUtils.isEmpty(authentication.getAuthorities())) {
            response.put(AUTHORITIES, AuthorityUtils.authorityListToSet(authentication.getAuthorities()));
        }
        return response;
    }
}
