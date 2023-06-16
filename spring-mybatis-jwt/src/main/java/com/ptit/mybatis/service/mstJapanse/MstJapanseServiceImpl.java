/**
 * Copyright 2023 NguyenDacTung
 */
package com.ptit.mybatis.service.mstJapanse;

import com.ptit.mybatis.dto.response.MstJapanseResponse;
import com.ptit.mybatis.repository.MstJapanseRepository;
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
public class MstJapanseServiceImpl implements MstJapanseService {

    private static final Logger logger = LoggerFactory.getLogger(MstJapanseServiceImpl.class);

    @Autowired
    private MstJapanseRepository mstJapanseRepository;

    @Override
    public List<MstJapanseResponse> getListMstJapanese(Pageable pageable) {
        log.info("Get list mst Japanse, page: {}, size: {}, sort:{} ", pageable.getPageNumber(), pageable.getPageSize(), pageable.getSort().toString());
        return mstJapanseRepository.getListMstJapanese(pageable);
    }

    @Override
    public BaseResponse getMstJapaneseByCodeLevel(String codeLevel) {
        log.info("get Mst Japanese By CodeLevel: {}", codeLevel);
        MstJapanseResponse mstJapanseResponse = mstJapanseRepository.getMstJapaneseByCodeLevel(codeLevel);
        if (mstJapanseResponse == null) {
            return new BaseResponse(new Meta("200", "Japanese level dose not exist !"), codeLevel);
        }
        return new BaseResponse(new Meta("200", "Success"), mstJapanseResponse);

    }
}
