package com.ptit.mybatis.repository;

import com.ptit.mybatis.dto.response.MstGroupResponse;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Pageable;

import java.util.List;

@Mapper
public interface MstGroupRepository {
    List<MstGroupResponse> getAllMstGroup(@Param("page") Pageable pageable);

    MstGroupResponse getMstGroupByGroupId(@Param("groupId") Integer groupId);
}
