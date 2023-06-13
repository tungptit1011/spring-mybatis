package com.ptit.mybatis.service.mstJapanse;

import com.ptit.mybatis.dto.response.MstJapanseResponse;
import com.ptit.mybatis.repository.MstJapanseRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MstJapanseServiceImpl implements MstJapanseService {

    private static final Logger logger = LoggerFactory.getLogger(MstJapanseServiceImpl.class);

    @Autowired
    private MstJapanseRepository mstJapanseRepository;

    @Override
    public List<MstJapanseResponse> getAllMstJapanese(Pageable pageable) {
        return mstJapanseRepository.getAllMstJapanese(pageable);
    }

    @Override
    public MstJapanseResponse getMstJapaneseByCodeLevel(String codeLevel) {
        return mstJapanseRepository.getMstJapaneseByCodeLevel(codeLevel);
    }
}
