package com.ptit.mybatis.service.mstGroup;

import com.ptit.mybatis.dto.response.MstGroupResponse;
import com.ptit.mybatis.repository.MstGroupRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MstGroupServiceImpl implements MstGroupService {

    private static final Logger logger = LoggerFactory.getLogger(MstGroupServiceImpl.class);

    @Autowired
    private MstGroupRepository mstGroupRepository;

    @Override
    public List<MstGroupResponse> getAllMstGroup(Pageable pageable) {
        return mstGroupRepository.getAllMstGroup(pageable);
    }

    @Override
    public MstGroupResponse getMstGroupByGroupId(Integer groupId) {
        if (groupId == null) {
            return null;
        } else return mstGroupRepository.getMstGroupByGroupId(groupId);
    }
}
