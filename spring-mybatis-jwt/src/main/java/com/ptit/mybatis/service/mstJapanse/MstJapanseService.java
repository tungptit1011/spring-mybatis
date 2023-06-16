/**
 * Copyright 2023 NguyenDacTung
 */
package com.ptit.mybatis.service.mstJapanse;

import com.ptit.mybatis.dto.response.MstJapanseResponse;
import com.ptit.mybatis.utils.BaseResponse;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface MstJapanseService {
    List<MstJapanseResponse> getListMstJapanese(Pageable pageable);

    BaseResponse getMstJapaneseByCodeLevel(String codeLevel);
}
