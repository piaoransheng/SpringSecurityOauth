package com.lhc.web.dao;

import com.lhc.web.domain.OrgOperationUser;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface OrgOperationUserMapper {
    int deleteByPrimaryKey(String oou_id);

    int insert(OrgOperationUser record);

    OrgOperationUser selectByPrimaryKey(String oou_id);

    List<OrgOperationUser> selectAll();

    int updateByPrimaryKey(OrgOperationUser record);


    OrgOperationUser selectByUser(OrgOperationUser orgOperationUser);

    List<String> userPermissions(String userId);
}