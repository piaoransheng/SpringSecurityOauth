package com.lhc.diy;

import com.lhc.web.dao.OrgOperationUserMapper;
import com.lhc.web.domain.OrgOperationUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

/**
 * @Author lhc
 * @Date 2020-09-25 9:39
 **/

@Component
public class MyUserDetailsService implements UserDetailsService {
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Autowired
    private OrgOperationUserMapper orgOperationUserMapper;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        OrgOperationUser orgOperationUser = this.selectUserByName(userName);
        if (orgOperationUser == null) {
            return null;
        }
        return User.withUsername(orgOperationUser.getOou_name()).password(orgOperationUser.getOou_password()).authorities("ROLE_USER").build();
    }

    private OrgOperationUser selectUserByName(String userName) {
        OrgOperationUser search = new OrgOperationUser();
        search.setOou_name(userName);
        return orgOperationUserMapper.selectByUser(search);
    }
}