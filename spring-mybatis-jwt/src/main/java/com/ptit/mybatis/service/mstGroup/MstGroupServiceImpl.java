/**
 * Copyright 2023 NguyenDacTung
 */
package com.ptit.mybatis.service.mstGroup;

import com.ptit.mybatis.dto.response.MstGroupResponse;
import com.ptit.mybatis.repository.MstGroupRepository;
import com.ptit.mybatis.utils.BaseResponse;
import com.ptit.mybatis.utils.Meta;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class MstGroupServiceImpl implements MstGroupService {

    @Autowired
    private MstGroupRepository mstGroupRepository;

    @Override
    public List<MstGroupResponse> getListMstGroup(Pageable pageable) {
        log.info("Get list mst group, page: {}, size: {}, sort:{} ", pageable.getPageNumber(), pageable.getPageSize(), pageable.getSort().toString());
        return mstGroupRepository.getListMstGroup(pageable);
    }

    @Override
    public BaseResponse getMstGroupByGroupId(Integer id) {
        log.info("get Mst Group By GroupId: {}", id);
        if (id == 0) {
            return new BaseResponse(new Meta("200", "Department does not exist !"));
        }
        MstGroupResponse mstGroupResponse = mstGroupRepository.getMstGroupByGroupId(id);
        if (mstGroupResponse == null) {
            return new BaseResponse(new Meta("200", "Department does not exist !"));
        }
        return new BaseResponse(new Meta("200", "Success"), mstGroupResponse);
    }
}
