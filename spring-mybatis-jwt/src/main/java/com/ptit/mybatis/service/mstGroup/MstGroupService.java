package com.ptit.mybatis.service.mstGroup;

import com.ptit.mybatis.dto.response.MstGroupResponse;
import com.ptit.mybatis.utils.BaseResponse;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface MstGroupService {
    List<MstGroupResponse> getListMstGroup(Pageable pageable);

    BaseResponse getMstGroupByGroupId(Integer groupId);
}
