package com.ptit.mybatis.controller;

import com.ptit.mybatis.dto.response.MstGroupResponse;
import com.ptit.mybatis.service.mstGroup.MstGroupService;
import com.ptit.mybatis.utli.ConstantUrl;
import com.ptit.mybatis.utli.ListBaseResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SecurityRequirement(name = "BearerAuth")
@RestController
@RequestMapping(ConstantUrl.API_MST_GROUP)
public class MstGroupController {

    private static final Logger logger = LoggerFactory.getLogger(MstGroupController.class);

    @Autowired
    private MstGroupService mstGroupService;

    @GetMapping("/list")
    @Operation(summary = "Get all the departments")
    public ListBaseResponse<MstGroupResponse> getAllMstGroup(Pageable pageable) {
        return new ListBaseResponse<>(new PageImpl<>(mstGroupService.getAllMstGroup(pageable)));
    }

    @GetMapping("{id}")
    @Operation(summary = "Search department by department code")
    public ResponseEntity<MstGroupResponse> getMstGroupByGroupId(@PathVariable Integer id) {
        return new ResponseEntity<>(mstGroupService.getMstGroupByGroupId(id), HttpStatus.OK);
    }
}