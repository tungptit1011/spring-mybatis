package com.ptit.mybatis.controller;

import com.ptit.mybatis.dto.request.CreateTblUserRequest;
import com.ptit.mybatis.dto.request.UpdateTblUserRequest;
import com.ptit.mybatis.dto.response.TblUserInforResponse;
import com.ptit.mybatis.service.tblUser.TblUserService;
import com.ptit.mybatis.utils.BaseResponse;
import com.ptit.mybatis.utils.ConstantUrl;
import com.ptit.mybatis.utils.ListBaseResponse;
import com.ptit.mybatis.utils.Meta;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(ConstantUrl.V1_API + "/tbl-user")
public class TblUserController {

    private TblUserService tblUserService;

    @Autowired
    public void setTblUserService(TblUserService tblUserService) {
        this.tblUserService = tblUserService;
    }

    @GetMapping("/list")
    @Operation(summary = "Get list infor of user")
    public ListBaseResponse<TblUserInforResponse> getListTblUsers(@RequestParam(name = "groupId", required = false) Integer groupId, @RequestParam(name = "fullName", required = false) String fullName, Pageable pageable) {
        return new ListBaseResponse<TblUserInforResponse>(new PageImpl<>(tblUserService.getListlUsers(groupId, fullName, pageable)));
    }

    @PutMapping("/update")
    @Operation(summary = "Update user")
    public BaseResponse UpdateTblUser(@RequestBody @Valid UpdateTblUserRequest updateTblUserRequest) {
        return tblUserService.updateTblUser(updateTblUserRequest);
    }

    @PostMapping("/create")
    @Operation(summary = "Create user")
    public BaseResponse createTblUser(@RequestBody @Valid CreateTblUserRequest TblUserRequest) {
        return tblUserService.createTblUser(TblUserRequest);
    }

    @DeleteMapping("/delete/{userId}")
    @Operation(summary = "Delete user by id")
    public BaseResponse deleteTblUser(@PathVariable String userId) {
        Integer id;
        try {
            id = Integer.valueOf(userId);
        } catch (NumberFormatException e) {
            id = 0;
        }
        return tblUserService.deleteTblUser(id);
    }
}
