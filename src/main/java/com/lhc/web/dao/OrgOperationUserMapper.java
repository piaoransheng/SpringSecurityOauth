package com.lhc.web.dao;

import com.lhc.web.domain.OrgOperationUser;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface OrgOperationUserMapper {

    OrgOperationUser selectByUser(OrgOperationUser orgOperationUser);

    List<String> userPermissions(String userId);
}