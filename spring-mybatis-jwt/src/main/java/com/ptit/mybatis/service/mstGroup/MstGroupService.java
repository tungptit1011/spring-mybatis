package com.ptit.mybatis.service.mstGroup;

import com.ptit.mybatis.dto.response.MstGroupResponse;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface MstGroupService {
    List<MstGroupResponse> getAllMstGroup(Pageable pageable);

    MstGroupResponse getMstGroupByGroupId(Integer groupId);
}
