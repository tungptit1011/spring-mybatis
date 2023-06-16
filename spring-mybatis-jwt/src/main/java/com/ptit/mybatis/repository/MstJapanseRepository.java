/**
 * Copyright 2023 NguyenDacTung
 */
package com.ptit.mybatis.repository;

import com.ptit.mybatis.dto.response.MstJapanseResponse;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Pageable;

import java.util.List;

@Mapper
public interface MstJapanseRepository {
    public List<MstJapanseResponse> getListMstJapanese(@Param("page")Pageable pageable);

    public MstJapanseResponse getMstJapaneseByCodeLevel(@Param("codeLevel") String codeLevel);
}
