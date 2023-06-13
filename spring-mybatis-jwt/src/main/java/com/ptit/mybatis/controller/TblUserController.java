package com.ptit.mybatis.controller;

import com.ptit.mybatis.dto.request.CreateTblUserRequest;
import com.ptit.mybatis.dto.request.UpdateTblUserRequest;
import com.ptit.mybatis.dto.response.TblUserInforResponse;
import com.ptit.mybatis.service.tblUser.TblUserService;
import com.ptit.mybatis.utli.BaseResponse;
import com.ptit.mybatis.utli.ListBaseResponse;
import com.ptit.mybatis.utli.Meta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/rest/api/tbl-user")
public class TblUserController {

    private TblUserService tblUserService;

    @Autowired
    public void setTblUserService(TblUserService tblUserService) {
        this.tblUserService = tblUserService;
    }

    @GetMapping("/list")
    public ListBaseResponse<TblUserInforResponse> getListUsers(@RequestParam(name = "groupId", required = false) Integer groupId, @RequestParam(name = "fullName", required = false) String fullName, Pageable pageable) {
        return new ListBaseResponse<TblUserInforResponse>(new PageImpl<>(tblUserService.getListlUsers(groupId, fullName, pageable)));
    }

    @PutMapping("/update")
    public BaseResponse getTblUserByLoginName(@RequestBody UpdateTblUserRequest updateTblUserRequest) {
        return tblUserService.updateTblUser(updateTblUserRequest);
    }

    @PostMapping("/create")
    public BaseResponse createTblUser(@RequestBody CreateTblUserRequest TblUserRequest) {
        return tblUserService.createTblUser(TblUserRequest);
    }

    @DeleteMapping("/delete/{userId}")
    public BaseResponse deleteTblUser(@PathVariable String userId) {
        Integer id = null;
        try {
            id = Integer.valueOf(userId);
        } catch (NumberFormatException e) {
            return new BaseResponse(new Meta("200", "Requires passing in the correct format of the real number type"), userId);
        }
        return tblUserService.deleteTblUser(id);
    }
}
