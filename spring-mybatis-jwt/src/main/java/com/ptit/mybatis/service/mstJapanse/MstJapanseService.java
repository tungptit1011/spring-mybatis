package com.ptit.mybatis.service.mstJapanse;

import com.ptit.mybatis.dto.response.MstJapanseResponse;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface MstJapanseService {
    List<MstJapanseResponse> getAllMstJapanese(Pageable pageable);

    MstJapanseResponse getMstJapaneseByCodeLevel(String codeLevel);
}
