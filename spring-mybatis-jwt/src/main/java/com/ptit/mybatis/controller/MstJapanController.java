package com.ptit.mybatis.controller;

import com.ptit.mybatis.dto.response.MstJapanseResponse;
import com.ptit.mybatis.service.mstJapanse.MstJapanseService;
import com.ptit.mybatis.utli.ConstantUrl;
import com.ptit.mybatis.utli.ListBaseResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
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
@Scope("prototype")
public class MstJapanController {

    private static final Logger logger = LoggerFactory.getLogger(MstJapanController.class);

    private final MstJapanseService mstJapanService;

    public MstJapanController(MstJapanseService mstJapanService) {
        this.mstJapanService = mstJapanService;
    }

    @GetMapping("/list")
    @Operation(summary = "Get all levels of Japanese")
    public ListBaseResponse<MstJapanseResponse> getAllMstJapanese(Pageable pageable) {
        return new ListBaseResponse(new PageImpl<>(mstJapanService.getAllMstJapanese(pageable)));
    }

    @GetMapping("{codeLevel}")
    @Operation(summary = "Get levels of Japanese by code level ")
    public MstJapanseResponse getMstJapaneseByCodeLevel(@PathVariable String codeLevel) {
        return mstJapanService.getMstJapaneseByCodeLevel(codeLevel);
    }
}
