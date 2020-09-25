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
        System.out.println(passwordEncoder().encode("123456"));
        OrgOperationUser orgOperationUser = this.selectUserByName(userName);
        if (orgOperationUser == null) {
            return null;
        }
        MyUserDetails myUserDetails = this.buildMyUserDetails(orgOperationUser);
        return myUserDetails;

        //用户权限
//        List<String> userpermissionList = this.selectUserPermissions(orgOperationUser.getOou_id());
//        //用户权限集合转数组
//        String[] array = new String[userpermissionList.size()];
//        userpermissionList.toArray(array);
//        UserDetails userDetails = User.withUsername(orgOperationUser.getOou_name()).password(orgOperationUser.getOou_password()).authorities(array).build();
//        return userDetails;
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
     * 查询用户权限 这边先搞个角色往往
     *
     * @param userId
     * @return
     */
    private List<String> selectUserPermissions(String userId) {
        return orgOperationUserMapper.userPermissions(userId);
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
        return myUserDetails;
    }
}