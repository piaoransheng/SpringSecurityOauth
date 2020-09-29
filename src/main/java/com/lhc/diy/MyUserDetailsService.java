package com.lhc.diy;

import com.lhc.web.dao.OrgOperationUserMapper;
import com.lhc.web.domain.OrgOperationUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

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
        MyUserDetails myUserDetails = this.buildMyUserDetails(orgOperationUser);
        return myUserDetails;
    }

    /**
     * 根据账号查询用户
     *
     * @param userName 用户名
     * @return 数据库用户信息
     */
    private OrgOperationUser selectUserByName(String userName) {
        OrgOperationUser search = new OrgOperationUser();
        search.setOou_name(userName);
        return orgOperationUserMapper.selectByUser(search);
    }

    /**
     * 查询用户权限 这边先搞个角色玩玩
     *
     * @param userId
     * @return
     */
    private List<GrantedAuthority> selectUserPermissions(String userId) {
        List<GrantedAuthority> authorities = new ArrayList<>();
        //用户角色
        List<String> roleList = orgOperationUserMapper.userPermissions(userId);
        for (String role : roleList) {
            SimpleGrantedAuthority simpleGrantedAuthority = new SimpleGrantedAuthority(role);
            authorities.add(simpleGrantedAuthority);
        }
        return authorities;
    }

    /**
     * 构建自定义返回的用户信息
     *
     * @param orgOperationUser 数据库查询的userDomain
     * @return 自定义用户信息 继承userDetails
     */
    private MyUserDetails buildMyUserDetails(OrgOperationUser orgOperationUser) {
        MyUserDetails myUserDetails = new MyUserDetails();
        myUserDetails.setId(orgOperationUser.getOou_id());
        myUserDetails.setCarrierId(orgOperationUser.getOou_carrier_id());
        myUserDetails.setDeptId(orgOperationUser.getOou_dept_id());
        myUserDetails.setCode(orgOperationUser.getOou_code());
        myUserDetails.setUsername(orgOperationUser.getOou_name());
        myUserDetails.setPassword(orgOperationUser.getOou_password());
        if (!CollectionUtils.isEmpty(selectUserPermissions(orgOperationUser.getOou_id()))) {
            myUserDetails.setAuthorities(selectUserPermissions(orgOperationUser.getOou_id()));
        }
        return myUserDetails;
    }
}