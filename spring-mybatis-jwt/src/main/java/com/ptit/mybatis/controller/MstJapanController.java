/**
 * Copyright 2023 NguyenDacTung
 */
package com.ptit.mybatis.controller;

import com.ptit.mybatis.dto.response.MstJapanseResponse;
import com.ptit.mybatis.service.mstJapanse.MstJapanseService;
import com.ptit.mybatis.utils.BaseResponse;
import com.ptit.mybatis.utils.ConstantUrl;
import com.ptit.mybatis.utils.ListBaseResponse;
import io.swagger.v3.oas.annotations.Operation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(ConstantUrl.V1_API + "/mst-japan")
public class MstJapanController {

    private final MstJapanseService mstJapanService;

    public MstJapanController(MstJapanseService mstJapanService) {
        this.mstJapanService = mstJapanService;
    }

    @GetMapping("/list")
    @Operation(summary = "Get list levels of Japanese")
    public ListBaseResponse<MstJapanseResponse> getListMstJapanese(Pageable pageable) {
        return new ListBaseResponse(new PageImpl<>(mstJapanService.getListMstJapanese(pageable)));
    }

    @GetMapping("{codeLevel}")
    @Operation(summary = "Get levels of Japanese by code level ")
    public BaseResponse getMstJapaneseByCodeLevel(@PathVariable String codeLevel) {
        return mstJapanService.getMstJapaneseByCodeLevel(codeLevel);
    }
}
