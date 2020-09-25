package com.lhc.web.controller;

import com.lhc.web.dao.OrgOperationUserMapper;
import com.lhc.web.domain.OrgOperationUser;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author lhc
 * @Date 2020-09-25 11:24
 **/
@Controller
@RequestMapping("/api")
public class OrgOperationUserController {

    @Resource
    private OrgOperationUserMapper orgOperationUserMapper;

    @GetMapping("/userList")
    public void test() {
        List<OrgOperationUser> orgOperationUserList = orgOperationUserMapper.selectAll();
        System.out.println(orgOperationUserList.size());
    }
}