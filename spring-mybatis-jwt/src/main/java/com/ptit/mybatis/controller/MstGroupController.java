/**
 * Copyright 2023 NguyenDacTung
 */
package com.ptit.mybatis.controller;

import com.ptit.mybatis.dto.response.MstGroupResponse;
import com.ptit.mybatis.service.mstGroup.MstGroupService;
import com.ptit.mybatis.utils.BaseResponse;
import com.ptit.mybatis.utils.ConstantUrl;
import com.ptit.mybatis.utils.ListBaseResponse;
import io.swagger.v3.oas.annotations.Operation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(ConstantUrl.V1_API + "/mst-group")
public class MstGroupController {

    @Autowired
    private MstGroupService mstGroupService;

    @GetMapping("/list")
    @Operation(summary = "Get list the departments")
    public ListBaseResponse<MstGroupResponse> getListMstGroup(Pageable pageable) {
        return new ListBaseResponse<>(new PageImpl<>(mstGroupService.getListMstGroup(pageable)));
    }

    @GetMapping("{groupId}")
    @Operation(summary = "Search department by department code")
    public BaseResponse<MstGroupResponse> getMstGroupByGroupId(@PathVariable String groupId) {
        Integer id;
        try {
            id = Integer.valueOf(groupId);
        } catch (NumberFormatException e) {
            id = 0;
        }
        return mstGroupService.getMstGroupByGroupId(id);
    }
}
