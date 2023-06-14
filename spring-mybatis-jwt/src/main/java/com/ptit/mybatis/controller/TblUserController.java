package com.ptit.mybatis.controller;

import com.ptit.mybatis.dto.request.CreateTblUserRequest;
import com.ptit.mybatis.dto.request.UpdateTblUserRequest;
import com.ptit.mybatis.dto.response.TblUserInforResponse;
import com.ptit.mybatis.service.tblUser.TblUserService;
import com.ptit.mybatis.utli.BaseResponse;
import com.ptit.mybatis.utli.ConstantUrl;
import com.ptit.mybatis.utli.ListBaseResponse;
import com.ptit.mybatis.utli.Meta;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@SecurityRequirement(name = "BearerAuth")
@RestController
@RequestMapping(ConstantUrl.V1_API + "/tbl-user")
@Scope("prototype")
public class TblUserController {

    private TblUserService tblUserService;

    @Autowired
    public void setTblUserService(TblUserService tblUserService) {
        this.tblUserService = tblUserService;
    }

    @GetMapping("/list")
    @Operation(summary = "Get list infor of user")
    public ListBaseResponse<TblUserInforResponse> getListUsers(@RequestParam(name = "groupId", required = false) Integer groupId, @RequestParam(name = "fullName", required = false) String fullName, Pageable pageable) {
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
        Integer id = null;
        try {
            id = Integer.valueOf(userId);
        } catch (NumberFormatException e) {
            return new BaseResponse(new Meta("200", "Requires enter in the correct format of the real number type"), "Userid: " + userId);
        }
        return tblUserService.deleteTblUser(id);
    }
}
